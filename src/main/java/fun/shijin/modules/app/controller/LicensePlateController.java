package fun.shijin.modules.app.controller;

import fun.shijin.common.utils.R;
import fun.shijin.modules.app.utils.BaiduOrcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Jiaman
 * @Date 2022/4/27 10:11
 * @Desc
 */

@Controller
@RequestMapping("car/manage")
public class LicensePlateController {

    @Autowired
    BaiduOrcUtil baiduOrcUtil;

    @RequestMapping("/recognition")
    public R recognition(@RequestParam String image) {
        String platelicense = baiduOrcUtil.platelicense(image);
        return R.ok().put("platelicense", platelicense);
    }
}
