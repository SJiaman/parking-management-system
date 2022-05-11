package fun.shijin.modules.app.service;

import fun.shijin.modules.app.entity.AccessInfoEntity;

import java.util.Map;

/**
 * @Author Jiaman
 * @Date 2022/5/10 21:29
 * @Desc 支付宝支付服务
 */

public interface PayService {
    /**
     *支付创建
     * @param accessInfoEntity
     * @return
     */
    String tradeCreate(AccessInfoEntity accessInfoEntity);

    /**
     * 订单处理
     * @param params
     */
    void processOrder(Map<String, String> params);

    /**
     * 账单查询
     * @param billDate
     * @param type
     * @return
     */
    String queryBill(String billDate, String type);
}
