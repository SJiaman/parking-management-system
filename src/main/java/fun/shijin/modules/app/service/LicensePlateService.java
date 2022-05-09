package fun.shijin.modules.app.service;


import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Jiaman
 * @Date 2022/5/2 20:17
 * @Desc 车牌识别服务
 */

public interface LicensePlateService {
    String licensePlateOrc(MultipartFile file);
}
