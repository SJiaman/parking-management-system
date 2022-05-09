package fun.shijin.modules.app.service.impl;

import cn.hutool.core.date.BetweenFormater;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.Query;
import fun.shijin.common.utils.R;
import fun.shijin.modules.app.dao.AccessInfoDao;
import fun.shijin.modules.app.entity.AccessInfoEntity;
import fun.shijin.modules.app.entity.OrderEntity;
import fun.shijin.modules.app.entity.PriceEntity;
import fun.shijin.modules.app.entity.SpaceLicenseplateEntity;
import fun.shijin.modules.app.service.AccessInfoService;
import fun.shijin.modules.app.service.OrderService;
import fun.shijin.modules.app.service.PriceService;
import fun.shijin.modules.app.service.SpaceLicenseplateService;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("accessInfoService")
public class AccessInfoServiceImpl extends ServiceImpl<AccessInfoDao, AccessInfoEntity> implements AccessInfoService {
    @Autowired
    AccessInfoDao accessDao;

    @Autowired
    SpaceLicenseplateService spaceLicenseplateService;

    @Autowired
    PriceService priceService;

    @Autowired
    OrderService orderService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AccessInfoEntity> page = this.page(
                new Query<AccessInfoEntity>().getPage(params),
                new QueryWrapper<AccessInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Map<String, Object> entryParking(String licensePlate) {
        HashMap<String, Object> data = new HashMap<>();
        AccessInfoEntity accessInfoEntity = new AccessInfoEntity();
        int record = spaceLicenseplateService.count(
                new LambdaQueryWrapper<SpaceLicenseplateEntity>()
                        .eq(SpaceLicenseplateEntity::getLicensePlate, licensePlate));
        if (record != 0) {
            accessInfoEntity.setCarType(1);
            data.put("carType", "会员车");
        } else {
            accessInfoEntity.setCarType(0);
            data.put("carType", "临时车");
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setLicensePlate(licensePlate);
            //时间戳
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            //生成两位数的随机数
            int random = RandomUtils.nextInt(100) + 10;
            String value =  format.format(new Date()) + random;
            orderEntity.setOrderNumber(value);
            orderEntity.setState(0);
            orderService.save(orderEntity);
        }
        accessInfoEntity.setLicensePlate(licensePlate);
        accessInfoEntity.setEntryTime(DateUtil.parse(DateUtil.now()));
        baseMapper.insert(accessInfoEntity);
        return data;
    }

    @Override
    public Map<String, Object> outParking(String licensePlate) {
        // 出库，计算价格
        //
        HashMap<String, Object> data = new HashMap<>();
        AccessInfoEntity accessInfoEntity = baseMapper.
                selectOne(new LambdaQueryWrapper<AccessInfoEntity>()
                        .eq(AccessInfoEntity::getLicensePlate, licensePlate)
                        .isNull(AccessInfoEntity::getOutTime));

        accessInfoEntity.setOutTime(DateUtil.parse(DateUtil.now()));
        if (accessInfoEntity.getCarType() == 1) {
            accessInfoEntity.setCost(BigDecimal.ZERO);
            baseMapper.updateById(accessInfoEntity);
            data.put("outMsg", "会员车" + licensePlate + "祝你一路平安");
            return data;
        }
        BigDecimal cost = priceService.costCalculate(accessInfoEntity);
        accessInfoEntity.setCost(cost);
        baseMapper.updateById(accessInfoEntity);
        OrderEntity order = orderService.getOne(new LambdaQueryWrapper<OrderEntity>()
                .eq(OrderEntity::getLicensePlate, licensePlate)
                .eq(OrderEntity::getState, 0));
        order.setOrderCost(cost);
        order.setCreateTime(DateUtil.parse(DateUtil.now()));
        long between = DateUtil.between(accessInfoEntity.getEntryTime(), accessInfoEntity.getOutTime(), DateUnit.MINUTE);
        order.setParkTime(DateUtil.formatBetween(between, BetweenFormater.Level.MINUTE));
        orderService.updateById(order);
        data.put("outMsg", "会员车" + licensePlate + cost + "祝你一路平安" );
        return data;
    }

    @Override
    public List<Date> queryRecord(String licenseplate) {
        return accessDao.queryRecord(licenseplate);
    }


}