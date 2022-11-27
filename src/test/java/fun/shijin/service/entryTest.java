package fun.shijin.service;

import fun.shijin.modules.app.service.AccessInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @Author Jiaman
 * @Date 2022/6/6 20:23
 * @Desc
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class entryTest {
    @Autowired
    AccessInfoService accessInfoService;

    @Test
    public void entryTest1() {
        String number = "贵DB9876";
        Map<String, Object> stringObjectMap = accessInfoService.entryParking(number);
        stringObjectMap.forEach((k, v) -> {
            System.out.println(k + v);
        });
    }

    @Test
    public void outTest1() {
        String number = "贵DB9876";
        Map<String, Object> stringObjectMap = accessInfoService.outParking(number);
        stringObjectMap.forEach((k, v) -> {
            System.out.println(k + v);
        });
    }
}
