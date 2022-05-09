package fun.shijin.modules.app.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收费表
 * 
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-04-27 13:23:20
 */
@Data
@TableName("pms_price")
public class PriceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 免费时长
	 */
	private Integer freeTime;
	/**
	 * 计时单位
	 */
	private Integer timeUnit;
	/**
	 * 单位费用
	 */
	private BigDecimal unitCost;
	/**
	 * 每天最大收费
	 */
	private BigDecimal dayMaxCost;
	/**
	 * 收费模式
	 */
	private Integer chargeMode;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date createTime;

}
