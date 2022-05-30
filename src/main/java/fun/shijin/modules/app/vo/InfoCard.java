package fun.shijin.modules.app.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Jiaman
 * @Date 2022/5/29 16:40
 * @Desc
 */

@Data
public class InfoCard {
    /**
     * 总车位
     */
    private Integer totalSpaces;
    /**
     * 剩余车位
     */
    private Integer surplusSpaces;
    /**
     * 总订单数
     */
    private String totalOrder;
    /**
     * 总停车费
     */
    private String totalOrderCost;
}
