package fun.shijin.modules.app.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baidu.aip.ocr.AipOcr;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * @Author Jiaman
 * @Date 2022/4/25 20:04
 * @Desc 百度人工智能 车牌识别
 */

@Component
@Configuration
@EnableConfigurationProperties(BaiduProperties.class)
public class BaiduOrcUtil {
//
//    // 日志打印
//    private final static Logger LOGGER = LoggerFactory.getLogger(BaiduOrcUtil.class);

    private BaiduProperties baiduProperties;

    public BaiduOrcUtil(BaiduProperties baiduProperties) {
        this.baiduProperties = baiduProperties;
    }

    // 百度AI
    private AipOcr client;

    /**
     * 百度ai 初始化， 设置网络连接参数等
     */
    @PostConstruct
    public void init() {
        try {
            client = new AipOcr(baiduProperties.getAppId(), baiduProperties.getApiKey(), baiduProperties.getSecretKey());
            client.setConnectionTimeoutInMillis(2000);
            client.setSocketTimeoutInMillis(60000);
        } catch (Exception e) {
//            LOGGER.error("百度车牌识别初始化失败，{}", e.getMessage());
            e.printStackTrace();
        }
    }


    public String platelicense(String image) {
        try {
            HashMap<String, String> options = new HashMap<>();
            options.put("multi_detect", "false");
            JSONObject res = client.plateLicense(image, options);
            System.out.println(res.toString());
            Object result = res.get("words_result");
            System.out.println(result.toString());
            JSONArray array = JSON.parseArray(result.toString());
            com.alibaba.fastjson.JSONObject object = JSON.parseObject(array.get(0).toString());
            Object number = object.get("number");
            return number.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        try {
            AipOcr client = new AipOcr("26072861", "cvsNByQayjQOdx6KiLRH2GqM", "0QGaRuq41HvbUEkiMWIUiet50YFFL2th");
            client.setConnectionTimeoutInMillis(2000);
            client.setSocketTimeoutInMillis(60000);
            HashMap<String, String> options = new HashMap<>();
            String image = "F:\\graduationproject\\License_plate_recognition\\Test\\豫C66666.jpg";
            /**
             * 是否检测多张车牌，默认为false
             * 当置为true的时候可以对一张图片内的多张车牌进行识别
             */
            options.put("multi_detect", "true");
//            SslUtils.ignoreSsl();
            JSONObject res = client.plateLicense(image, options);
            System.out.println(res.toString());
            Object result = res.get("words_result");
            System.out.println(result.toString());
            JSONArray array = JSON.parseArray(result.toString());
            System.out.println(array.toString());
            com.alibaba.fastjson.JSONObject object = JSON.parseObject(array.get(0).toString());
            System.out.println(object.toString());
            Object number = object.get("number");
            System.out.println("车牌:"+number);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
