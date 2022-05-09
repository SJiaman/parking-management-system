package fun.shijin.modules.app.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import fun.shijin.modules.app.entity.AccessInfoEntity;
import fun.shijin.modules.app.entity.PriceEntity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @Author Jiaman
 * @Date 2022/5/6 15:15
 * @Desc 费用计算工具
 */

public class CostUtil {
    /**
     * 费用计算方法
     * @param accessInfo
     * @return
     */
    public BigDecimal costCalculate(AccessInfoEntity accessInfo, PriceEntity price) {
        // 第一步先获取时间间隔
        Long betweenMinute = DateUtil.between(accessInfo.getEntryTime(), DateUtil.parse(DateUtil.now()), DateUnit.MINUTE);

        Long payTime = betweenMinute - price.getFreeTime();

        if (payTime > 0) {
            BigDecimal count = NumberUtil.div(payTime, price.getTimeUnit());
            count = NumberUtil.round(count, 0);

            BigDecimal cost = NumberUtil.mul(count, price.getUnitCost());
            cost = NumberUtil.round(cost, 0);

            if (cost.compareTo(price.getDayMaxCost()) > 1) {
                return price.getDayMaxCost();
            } else {
                return cost;
            }
        } else {
            return new BigDecimal(BigInteger.ZERO);
        }

    }

    public static void main(String[] args) {
        String now = DateUtil.now();
        System.out.println(now);
        System.out.println(DateUtil.parse(now));

    }
}
