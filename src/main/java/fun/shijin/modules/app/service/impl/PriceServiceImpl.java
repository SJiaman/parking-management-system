package fun.shijin.modules.app.service.impl;

import cn.hutool.core.date.DateTime;
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
        return baseMapper.selectOne(new LambdaQueryWrapper<PriceEntity>().last("limit 1"));
    }

    @Override
    public BigDecimal costCalculate(AccessInfoEntity accessInfo) {
        DateTime outTime = DateUtil.parse(DateUtil.now());
        long betweenDay = DateUtil.between(accessInfo.getEntryTime(), outTime, DateUnit.DAY);
        long betweenMinute = DateUtil.between(accessInfo.getEntryTime(), outTime, DateUnit.MINUTE);
        if (betweenDay < 0) {
            return dayCostCalculate(betweenMinute, betweenDay);
        }
        betweenMinute = betweenMinute - (60 * 24 * betweenDay);
        return dayCostCalculate(betweenMinute, betweenDay);
    }


    private BigDecimal dayCostCalculate(Long time, Long day) {
        PriceEntity price = baseMapper.selectOne(new LambdaQueryWrapper<PriceEntity>().last("limit 1"));
        // 第一步先获取时间间隔
        BigDecimal totalCost = new BigDecimal(0);
        if (day > 0) {
            totalCost = NumberUtil.mul(day, price.getDayMaxCost());
        }

        Long payTime = time - price.getFreeTime();

        if (payTime <= 0) {
            return NumberUtil.add(totalCost, new BigDecimal(BigInteger.ZERO));
        }


        BigDecimal count = NumberUtil.div(payTime, price.getTimeUnit());
        count = NumberUtil.round(count, 0);

        BigDecimal cost = NumberUtil.mul(count, price.getUnitCost());
        cost = NumberUtil.round(cost, 0);

        if (cost.compareTo(price.getDayMaxCost()) < 0) {
            return NumberUtil.add(totalCost, cost);
        } else {
            return NumberUtil.add(totalCost, price.getDayMaxCost());
        }
    }
}