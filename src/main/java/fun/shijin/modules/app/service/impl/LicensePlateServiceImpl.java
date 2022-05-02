package fun.shijin.modules.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baidu.aip.ocr.AipOcr;
import fun.shijin.modules.app.config.BaiduOcrConfig;
import fun.shijin.modules.app.service.LicensePlateService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Author Jiaman
 * @Date 2022/5/2 20:20
 * @Desc
 */
@Service
public class LicensePlateServiceImpl implements LicensePlateService {
    @Autowired
    public AipOcr aipOcr;

    @Override
    public String licensePlateOrc(String imagePath) {
        // 百度车牌识别需要传入一个必须参数multi_detect，用与同时识别多个车牌
        HashMap<String, String> options = new HashMap<>();
        options.put("multi_detect", "true");
        // 传入图片地址和参数
        JSONObject res = aipOcr.plateLicense(imagePath, options);
        // 通过观察返回的数据格式，把数据转换，拿到相应的数据
        Object result = res.get("words_result");
        JSONArray array = JSON.parseArray(result.toString());
        com.alibaba.fastjson.JSONObject object = JSON.parseObject(array.get(0).toString());
        // 得到车牌号
        Object number = object.get("number");
        return number.toString();
    }
}
