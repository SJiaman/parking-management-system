package fun.shijin.modules.app.service.impl;

import cn.hutool.core.date.BetweenFormater;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import fun.shijin.modules.app.entity.AccessInfoEntity;
import fun.shijin.modules.app.entity.OrderEntity;
import fun.shijin.modules.app.service.OrderService;
import fun.shijin.modules.app.service.PayService;
import fun.shijin.modules.app.service.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Jiaman
 * @Date 2022/5/10 21:30
 * @Desc
 */
@Slf4j
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private Environment config;

    @Autowired
    private PriceService priceService;

    @Autowired
    OrderService orderService;

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public String tradeCreate(AccessInfoEntity accessInfo) {
        try {
            //生成订单
            log.info("生成订单");

            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setLicensePlate(accessInfo.getLicensePlate());

            //时间戳
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            //生成两位数的随机数
            int random = RandomUtils.nextInt(100) + 10;
            String value =  format.format(new Date()) + random;

            // 生成订单号
            orderEntity.setOrderNumber(value);

//            // 设置付款状态
//            orderEntity.setState(0);
//            orderService.save(orderEntity);
//
//
//            OrderEntity order = orderService.getOne(new LambdaQueryWrapper<OrderEntity>()
//                    .eq(OrderEntity::getLicensePlate, licensePlate)
//                    .eq(OrderEntity::getState, 0));

            BigDecimal cost = priceService.costCalculate(accessInfo);


            orderEntity.setOrderCost(cost);
            orderEntity.setCreateTime(DateUtil.parse(DateUtil.now()));
//            orderEntity.setState(0);

            // 设置停车时间
            String betweenTime = DateUtil.formatBetween(accessInfo.getEntryTime(), accessInfo.getOutTime(),
                    BetweenFormater.Level.MINUTE);

            orderEntity.setParkTime(betweenTime);



//            OrderInfo orderInfo = orderInfoService.createOrderByProductId(productId, PayType.ALIPAY.getType());

            //调用支付宝接口
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            //配置需要的公共请求参数
            //支付完成后，支付宝向谷粒学院发起异步通知的地址
            request.setNotifyUrl(config.getProperty("alipay.notify-url"));
            //支付完成后，我们想让页面跳转回谷粒学院的页面，配置returnUrl
            request.setReturnUrl(config.getProperty("alipay.return-url"));

            //组装当前业务方法的请求参数
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", orderEntity.getOrderNumber());
            bizContent.put("total_amount", cost);
            bizContent.put("subject", "停车场收费");
            bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");

            request.setBizContent(bizContent.toString());

            //执行请求，调用支付宝接口
            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);

            if(response.isSuccess()){
                log.info("调用成功，返回结果 ===> " + response.getBody());
                orderEntity.setState(1);
                // 增加订单订单
                orderService.save(orderEntity);
                return response.getBody();
            } else {
                log.info("调用失败，返回码 ===> " + response.getCode() + ", 返回描述 ===> " + response.getMsg());
                throw new RuntimeException("创建支付交易失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new RuntimeException("创建支付交易失败");
        }
    }

    @Override
    public void processOrder(Map<String, String> params) {
        log.info("处理订单");

        //获取订单号
        String orderNo = params.get("out_trade_no");

        /*在对业务数据进行状态检查和处理之前，
        要采用数据锁进行并发控制，
        以避免函数重入造成的数据混乱*/
        //尝试获取锁：
        // 成功获取则立即返回true，获取失败则立即返回false。不必一直等待锁的释放
//        if(lock.tryLock()) {
//            try {
//
//                //处理重复通知
//                //接口调用的幂等性：无论接口被调用多少次，以下业务执行一次
//                String orderStatus = orderInfoService.getOrderStatus(orderNo);
//                if (!OrderStatus.NOTPAY.getType().equals(orderStatus)) {
//                    return;
//                }
//
//                //更新订单状态
//                orderInfoService.updateStatusByOrderNo(orderNo, OrderStatus.SUCCESS);
//
//                //记录支付日志
//                paymentInfoService.createPaymentInfoForAliPay(params);
//
//            } finally {
//                //要主动释放锁
//                lock.unlock();
//            }
//        }
    }

    @Override
    public String queryBill(String billDate, String type) {

        try {

            AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
            JSONObject bizContent = new JSONObject();
            bizContent.put("bill_type", type);
            bizContent.put("bill_date", billDate);
            request.setBizContent(bizContent.toString());
            AlipayDataDataserviceBillDownloadurlQueryResponse response = alipayClient.execute(request);

            if(response.isSuccess()){
                log.info("调用成功，返回结果 ===> " + response.getBody());

                //获取账单下载地址
                Gson gson = new Gson();
                HashMap<String, LinkedTreeMap> resultMap = gson.fromJson(response.getBody(), HashMap.class);
                LinkedTreeMap billDownloadurlResponse = resultMap.get("alipay_data_dataservice_bill_downloadurl_query_response");
                String billDownloadUrl = (String)billDownloadurlResponse.get("bill_download_url");

                return billDownloadUrl;
            } else {
                log.info("调用失败，返回码 ===> " + response.getCode() + ", 返回描述 ===> " + response.getMsg());
                throw new RuntimeException("申请账单失败");
            }

        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new RuntimeException("申请账单失败");
        }
    }
}
