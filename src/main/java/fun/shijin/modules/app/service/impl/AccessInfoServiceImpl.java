package fun.shijin.modules.app.service.impl;

import cn.hutool.core.date.BetweenFormater;
import cn.hutool.core.date.DateTime;
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
import fun.shijin.modules.app.service.*;
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
    PayService payService;

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

    /**
     * 入库服务
     *
     * @param licensePlate
     * @return
     */
    @Override
    public Map<String, Object> entryParking(String licensePlate) {
        HashMap<String, Object> data = new HashMap<>();
        // 创建进出记录
        AccessInfoEntity accessInfoEntity = new AccessInfoEntity();
        int record = spaceLicenseplateService.count(
                new LambdaQueryWrapper<SpaceLicenseplateEntity>()
                        .eq(SpaceLicenseplateEntity::getLicensePlate, licensePlate));

        // 设置停车类型
        if (record != 0) {
            accessInfoEntity.setCarType(1);
            data.put("carType", "会员车");
            data.put("entryMsg", "会员车"+ licensePlate + "欢迎入库");
        } else {
            accessInfoEntity.setCarType(0);
            data.put("carType", "临时车");
            data.put("entryMsg", "临时车"+ licensePlate + "欢迎入库");
        }

        // 设置车牌
        accessInfoEntity.setLicensePlate(licensePlate);

        // 设置进库时间
        accessInfoEntity.setEntryTime(DateUtil.parse(DateUtil.now()));
        baseMapper.insert(accessInfoEntity);
        return data;
    }

    /**
     * 出库服务
     *
     * @param licensePlate
     * @return
     */
    @Override
    public Map<String, Object> outParking(String licensePlate) {
        HashMap<String, Object> data = new HashMap<>();
        // 查询停车记录
        AccessInfoEntity accessInfoEntity = baseMapper.
                selectOne(new LambdaQueryWrapper<AccessInfoEntity>()
                        .eq(AccessInfoEntity::getLicensePlate, licensePlate)
                        .isNull(AccessInfoEntity::getOutTime));

        DateTime outTime = DateUtil.parse(DateUtil.now());
        // 设置出库时间
        accessInfoEntity.setOutTime(outTime);

        // 设置停车时间
        String betweenTime = DateUtil.formatBetween(accessInfoEntity.getEntryTime(), accessInfoEntity.getOutTime(),
                BetweenFormater.Level.MINUTE);
        accessInfoEntity.setParkTime(betweenTime);

        //  更新记录
        baseMapper.updateById(accessInfoEntity);

        // 会员车直接出库
        if (accessInfoEntity.getCarType() == 1) {
            data.put("carType", accessInfoEntity.getCarType());
            data.put("outMsg", "会员车" + licensePlate + "祝你一路平安");
            return data;
        }

        // 非会员车，付停车费
        data.put("outMsg", "临时车" + licensePlate + "祝你一路平安" );
        data.put("accessInfoEntity", accessInfoEntity);
        return data;
    }

    @Override
    public List<Date> queryRecord(String licenseplate) {
        return accessDao.queryRecord(licenseplate);
    }
}