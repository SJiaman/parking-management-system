package fun.shijin.modules.app.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import fun.shijin.modules.app.dao.PriceDao;
import fun.shijin.modules.app.entity.AccessInfoEntity;
import fun.shijin.modules.app.entity.PriceEntity;
import fun.shijin.modules.app.service.AccessInfoService;
import fun.shijin.modules.app.service.PriceService;;
import fun.shijin.modules.app.service.SpaceLicenseplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.Query;


@Service("priceService")
public class PriceServiceImpl extends ServiceImpl<PriceDao, PriceEntity> implements PriceService {
    @Autowired
    AccessInfoService accessService;

    @Autowired
    SpaceLicenseplateService spaceLicenseplateService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PriceEntity> page = this.page(
                new Query<PriceEntity>().getPage(params),
                new QueryWrapper<PriceEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PriceEntity info() {
        PriceEntity price = baseMapper.selectOne(new LambdaQueryWrapper<PriceEntity>().last("limit 1"));
        return price;
    }

    @Override
    public BigDecimal costCalculate(AccessInfoEntity accessInfo) {
        PriceEntity price = baseMapper.selectOne(new LambdaQueryWrapper<PriceEntity>().last("limit 1"));
//        // 1.检测车牌是否有车位
//        String licensePlate = accessInfo.getLicensePlate();
//        Boolean flag = false;
//
//        Long subTime = 0L;
//        int record = spaceLicenseplateService.count(
//                new LambdaQueryWrapper<SpaceLicenseplateEntity>()
//                        .eq(SpaceLicenseplateEntity::getLicensePlate, licensePlate));

        // 如果有，车位里是否已经停车，
        // 第一种收费模式，后进收费，比较两车进入时间，后进入车辆收费
//        // 第二种收费模式，先出收费，判断两车共同停车时间，收共同停车时长的车费
//        if (record != 0 && price.getChargeMode() == 1) {
//            List<Date> dates = accessService.queryRecord(licensePlate);
//            if (dates.size() > 1) {
//                subTime = DateUtil.between(dates.get(0), dates.get(1), DateUnit.MINUTE);
//            } else {
//
//            }
//        }

//        if (flag) {
//            accessInfo.setCarType(1);
//        }
//
//
//        if (price.getChargeMode() == 0) {
//
//        }

        // 第一步先获取时间间隔
        Long betweenMinute = DateUtil.between(accessInfo.getEntryTime(), DateUtil.parse(DateUtil.now()), DateUnit.MINUTE);

        Long payTime = betweenMinute - price.getFreeTime();
        if (payTime <= 0 ) {
            return new BigDecimal(BigInteger.ZERO);
        }
        
        BigDecimal count = NumberUtil.div(payTime, price.getTimeUnit());
        count = NumberUtil.round(count, 0);

        BigDecimal cost = NumberUtil.mul(count, price.getUnitCost());
        cost = NumberUtil.round(cost, 0);

        if (cost.compareTo(price.getDayMaxCost()) > 1) {
            return price.getDayMaxCost();
        } else {
            return cost;
        }
    
    }


//    private BigDecimal Calculate() {
//
//        // 第一步先获取时间间隔
//        Long betweenMinute = DateUtil.between(accessInfo.getEntryTime(), DateUtil.parse(DateUtil.now()), DateUnit.MINUTE);
//
//        Long payTime = betweenMinute - price.getFreeTime();
//
//        if (payTime > 0) {
//            BigDecimal count = NumberUtil.div(payTime, price.getTimeUnit());
//            count = NumberUtil.round(count, 0);
//
//            BigDecimal cost = NumberUtil.mul(count, price.getUnitCost());
//            cost = NumberUtil.round(cost, 0);
//
//            if (cost.compareTo(price.getDayMaxCost()) > 1) {
//                return price.getDayMaxCost();
//            } else {
//                return cost;
//            }
//        } else {
//            return new BigDecimal(BigInteger.ZERO);
//        }
//    }

}