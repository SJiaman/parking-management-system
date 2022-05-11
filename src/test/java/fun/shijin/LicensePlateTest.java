package fun.shijin;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import fun.shijin.modules.app.entity.AccessInfoEntity;
import fun.shijin.modules.app.service.MemberService;
import fun.shijin.modules.app.service.PriceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Jiaman
 * @Date 2022/5/8 16:41
 * @Desc
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class LicensePlateTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PriceService priceService;

    @Test
    public void testLicensePlate() {
        AccessInfoEntity accessInfoEntity = new AccessInfoEntity();
        accessInfoEntity.setEntryTime(DateUtil.parse("2022-05-10 19:42:03"));
        BigDecimal bigDecimal = priceService.costCalculate(accessInfoEntity);
        System.out.println(bigDecimal);

//        DateTime outTime = DateUtil.parse(DateUtil.now());
//
//        DateTime yesterday = DateUtil.yesterday();
//        String date1 = DateUtil.formatDate(DateUtil.yesterday());
//        String date2 = DateUtil.formatDate(accessInfoEntity.getEntryTime());
//
//        long between = DateUtil.between(accessInfoEntity.getEntryTime(), outTime, DateUnit.MINUTE);
//        long betweenDay = DateUtil.between(accessInfoEntity.getEntryTime(), outTime, DateUnit.DAY);
//        System.out.println(between);
//        System.out.println(betweenDay);
//
//
//
//        System.out.println(between - (60 * 24 * betweenDay));
//
////        if (date1.equals(date2)) {
////            System.out.println(DateUtil.endOfDay(accessInfoEntity.getEntryTime()));
////        }
//
//
//        DateTime date = DateUtil.date(yesterday);
//        System.out.println(date1);
    }

    @Test
    public void testDate() {
        String dateStr1 = "2017-04-01 22:33:23";
        Date date1 = DateUtil.parse(dateStr1);

        String dateStr2 = "2017-04-08 23:33:23";
        Date date2 = DateUtil.parse(dateStr2);

//相差一个月，31天
        long betweenDay = DateUtil.between(date1, date2, DateUnit.DAY);
    }
}
