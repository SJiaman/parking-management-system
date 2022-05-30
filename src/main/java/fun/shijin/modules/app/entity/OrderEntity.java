package fun.shijin.modules.app.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 订单表
 * 
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-04-27 13:23:20
 */
@Data
@TableName("pms_order")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 订单号
	 */
	@ExcelProperty("订单号")
	private String orderNumber;
	/**
	 * 收费金额
	 */
	@ExcelProperty("收费金额")
	private BigDecimal orderCost;
	/**
	 * 车牌号
	 */
	@ExcelProperty("车牌号")
	private String licensePlate;
	/**
	 * 创建时间
	 */
	@ExcelProperty("创建时间")
	private Date createTime;
	/**
	 * 停车时间
	 */
	@ExcelProperty("停车时间")
	private String parkTime;
	/**
	 * 支付状态
	 */
	private Integer state;
	/**
	 * 备注
	 */
	private Date remark;

}
