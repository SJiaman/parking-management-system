package fun.shijin.modules.app.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baidu.aip.ocr.AipOcr;
import fun.shijin.common.utils.CommonUtils;
import fun.shijin.common.utils.Constant;
import fun.shijin.modules.app.config.BaiduOcrConfig;
import fun.shijin.modules.app.service.LicensePlateService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * @Author Jiaman
 * @Date 2022/5/2 20:20
 * @Desc
 */
@Service
public class LicensePlateServiceImpl implements LicensePlateService {
    @Value("${file.path}")
    private String filePath;

    @Autowired
    public AipOcr aipOcr;

    @Override
    public String licensePlateOrc(MultipartFile file) {
        File parentFile = CommonUtils.createParentFile(filePath);

        // 给文件从新命名，把原名改为uuid加后缀
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String uuid = IdUtil.simpleUUID();
        fileName = uuid + suffix;

        // 创建文件并把数据写入
        File imageFile = new File(parentFile, fileName);
        try {
            FileUtil.writeFromStream(file.getInputStream(), imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 获取文件目录
        String fileDay = DateUtil.thisYear() + "/" + (DateUtil.thisMonth() + 1) + "/" + DateUtil.thisDayOfMonth();
        String imagePath = Constant.WINPATH + filePath +"/" + fileDay + "/" + fileName;

        // 百度车牌识别需要传入一个必须参数multi_detect，用与同时识别多个车牌
        HashMap<String, String> options = new HashMap<>();
        options.put("multi_detect", "true");
        // 传入图片地址和参数
        JSONObject res = aipOcr.plateLicense(imagePath, options);
        if (res.has("words_result")) {
            // 通过观察返回的数据格式，把数据转换，拿到相应的数据
            Object result = res.get("words_result");
            JSONArray array = JSON.parseArray(result.toString());
            com.alibaba.fastjson.JSONObject object = JSON.parseObject(array.get(0).toString());
            // 得到车牌号
            Object number = object.get("number");
            return number.toString();
        } else {
            return "未识别到车牌！";
        }
    }

    public static void main(String[] args) {
        String  path = "F:\\graduationproject\\pms\\parking-management-system\\src\\main\\java\\fun\\shijin\\modules\\lpr\\data\\f5ab7489e5dd4494b905f0b3ca5f9180.png";
        try {
            String[] arg = new String[] { "python", "F:\\graduationproject\\pms\\parking-management-system\\src\\main\\java\\fun\\shijin\\modules\\lpr\\lpr_demo.py", String.valueOf(path) };
            // 执行py文件
            Process proc = Runtime.getRuntime().exec(arg);

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public  LPR() {
//        String  path = "F:\\graduationproject\\pms\\parking-management-system\\src\\main\\java\\fun\\shijin\\modules\\lpr\\data\\f5ab7489e5dd4494b905f0b3ca5f9180.png"
//        try {
//            String[] args = new String[] { "python", "fun/shijin/modules/lpr/lpr_demo.py", String.valueOf(path) };
//            // 执行py文件
//            Process proc = Runtime.getRuntime().exec(args[1]);
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//            String line = null;
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
//            in.close();
//            proc.waitFor();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
