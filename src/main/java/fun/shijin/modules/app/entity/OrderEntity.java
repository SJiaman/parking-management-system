package fun.shijin.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
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
	private Integer orderId;
	/**
	 * 收费金额
	 */
	private Double money;
	/**
	 * 车牌号
	 */
	private String licencePlate;
	/**
	 * 停车时间
	 */
	private Date createTime;
	/**
	 * 停车时间
	 */
	private Date expirationTime;

}
