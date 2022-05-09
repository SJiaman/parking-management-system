package fun.shijin.modules.app.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

/**
 * 车辆进出表
 * 
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
 */
@Data
@TableName("pms_access_info")
public class AccessInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 类型
	 */
	private Integer carType;
	/**
	 * 车牌号
	 */
	private String licensePlate;
	/**
	 * 进入时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date entryTime;
	/**
	 * 出去时间
	 */
	private Date outTime;
	/**
	 * 费用（元）
	 */
	private BigDecimal cost;
}
