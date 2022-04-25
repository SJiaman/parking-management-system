package fun.shijin.modules.app.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author Jiaman
 * @Date 2022/4/25 20:27
 * @Desc
 */

@Data
@ConfigurationProperties(prefix = "baidu")
public class BaiduProperties {
    private String appId;

    private String apiKey;

    private String secretKey;
}
