package fun.shijin.modules.app.utils;

import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @Author Jiaman
 * @Date 2022/4/25 22:13
 * @Desc base64图片生成
 */

public class GenerateImageUtil {
//    public static void main(String[] args) {
//
//        String path = "F:\\graduationproject";
//        String name = "car";
//        boolean b = GenerateImage(img, path, name);
//        System.out.println("图片生成成功？" + b);
//    }


    public static boolean GenerateImage(String imgStr, String filePath, String fileName) {
        try {
            if (imgStr == null) {
                return false;
            }
            BASE64Decoder decoder = new BASE64Decoder();
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            //如果目录不存在，则创建
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            //生成图片
            OutputStream out = new FileOutputStream(filePath + fileName);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
