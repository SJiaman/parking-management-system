package fun.shijin.modules.app.config;

import com.baidu.aip.ocr.AipOcr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @Author Jiaman
 * @Date 2022/5/2 19:44
 * @Desc 百度车牌识别配置
 */

@Configuration
@PropertySource("classpath:config/baidu-orc.properties")
public class BaiduOcrConfig {
    @Autowired
    private Environment config;

    @Bean
    public AipOcr baiduOrcClient() {
        AipOcr aipOcr = new AipOcr(
                config.getProperty("baidu.appId"),
                config.getProperty("baidu.apiKey"),
                config.getProperty("baidu.secretKey"));
        aipOcr.setConnectionTimeoutInMillis(2000);
        aipOcr.setSocketTimeoutInMillis(60000);
        return aipOcr;
    }
}
