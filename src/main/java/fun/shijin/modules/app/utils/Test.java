package fun.shijin.modules.app.utils;

import cn.hutool.core.date.BetweenFormater;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @Author Jiaman
 * @Date 2022/5/10 21:11
 * @Desc
 */

public class Test {
    @org.junit.Test
    public void testDate() {
        String dateStr1 = "2017-04-01 22:33:23";
        Date date1 = DateUtil.parse(dateStr1);

        String dateStr2 = "2017-04-08 23:33:23";
        Date date2 = DateUtil.parse(dateStr2);

//相差一个月，31天
        long betweenDay = DateUtil.between(date1, date2, DateUnit.MINUTE);

        String formatBetween = DateUtil.formatBetween(date1, date2, BetweenFormater.Level.MINUTE);

        System.out.println(betweenDay);
        System.out.println(formatBetween);
    }
}
