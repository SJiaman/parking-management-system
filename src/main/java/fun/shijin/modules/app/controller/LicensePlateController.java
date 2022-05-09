package fun.shijin.modules.app.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import fun.shijin.common.utils.CommonUtils;
import fun.shijin.common.utils.Constant;
import fun.shijin.common.utils.R;
import fun.shijin.modules.app.entity.AccessInfoEntity;
import fun.shijin.modules.app.service.AccessInfoService;
import fun.shijin.modules.app.service.LicensePlateService;
import fun.shijin.modules.app.service.OrderService;
import fun.shijin.modules.app.utils.BaiduOrcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.HashMap;

/**
 * @Author Jiaman
 * @Date 2022/4/27 10:11
 * @Desc
 */

@RestController
@RequestMapping("car/manage")
public class LicensePlateController {
    @Value("${file.path}")
    private String filePath;

    @Autowired
    LicensePlateService licensePlateService;

    @Autowired
    AccessInfoService accessInfoService;

    @Autowired
    OrderService orderService;

    @RequestMapping("/recognition")
    public R recognition(@RequestParam("file") MultipartFile file) throws IOException {

//        // 先创建图片存放目录
//        File parentFile = CommonUtils.createParentFile(filePath);
//
//        // 给文件从新命名，把原名改为uuid加后缀
//        String fileName = file.getOriginalFilename();
//        String suffix = fileName.substring(fileName.lastIndexOf("."));
//        String uuid = IdUtil.simpleUUID();
//        fileName = uuid + suffix;
//
//        // 创建文件并把数据写入
//        File imageFile = new File(parentFile, fileName);
//        FileUtil.writeFromStream(file.getInputStream(), imageFile);
//
//        // 获取文件目录
//        String fileDay = DateUtil.thisYear() + "/" + (DateUtil.thisMonth() + 1) + "/" + DateUtil.thisDayOfMonth();
//        String imagePath = Constant.WINPATH + filePath +"/" + fileDay + "/" + fileName;
//
//        // 车牌识别
//        String platelicense = licensePlateService.licensePlateOrc(imagePath);
//
//
//        // 判断车牌号是否为空，为空返回识别失败
//        if (platelicense == null || platelicense.length() == 0) {
//            return R.error("识别失败,请重新扫描车牌");
//        }
//
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("platelicense", platelicense);
//
//
//        AccessInfoEntity accessInfoEntity = new AccessInfoEntity();
//        accessInfoEntity.setCarType(Constant.CAR_TYPE_TEMP);

//        return R.ok().put("platelicense", platelicense).put("imagePath", imagePath);
        return R.ok();
    }

//    @RequestMapping("/upload")
//    public String test(MultipartFile file) {
////        System.out.println(file.getName());
//
//        if (!file.isEmpty()) {
//            try {
//                /*
//                 * 这段代码执行完毕之后，图片上传到了工程的跟路径； 大家自己扩散下思维，如果我们想把图片上传到
//                 * d:/files大家是否能实现呢？ 等等;
//                 * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如： 1、文件路径； 2、文件名；
//                 * 3、文件格式; 4、文件大小的限制;
//                 */
//                BufferedOutputStream out = new BufferedOutputStream(
//                        new FileOutputStream(new File(
//                                file.getOriginalFilename())));
//                System.out.println(file.getName());
//                out.write(file.getBytes());
//                out.flush();
//                out.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//                return "上传失败," + e.getMessage();
//            } catch (IOException e) {
//                e.printStackTrace();
//                return "上传失败," + e.getMessage();
//            }
//
//            return "上传成功";
//
//        } else {
//            return "上传失败，因为文件是空的.";
//        }
//    }
}
