package fun.shijin.modules.app.controller;

import fun.shijin.common.utils.R;
import fun.shijin.modules.app.entity.AccessInfoEntity;
import fun.shijin.modules.app.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Jiaman
 * @Date 2022/5/11 14:59
 * @Desc
 */

@RestController
@RequestMapping("pms/ali-pay")
@Slf4j
public class AlipayController {
    @Autowired
    private PayService aliPayService;

    @PostMapping("/trade/page/pay/")
    public R tradePagePay(@RequestBody AccessInfoEntity accessInfo) {
        log.info("统一收单下单并支付页面接口的调用");
        //支付宝开放平台接受 request 请求对象后
        // 会为开发者生成一个html 形式的 form表单，包含自动提交的脚本
        String formStr = aliPayService.tradeCreate(accessInfo);
        //我们将form表单字符串返回给前端程序，之后前端将会调用自动提交脚本，进行表单的提交
        //此时，表单会自动提交到action属性所指向的支付宝开放平台中，从而为用户展示一个支付页面
        return R.ok().put("formStr", formStr);
    }
}
