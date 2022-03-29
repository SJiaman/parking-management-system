package fun.shijin.modules.pms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import lombok.Data;

/**
 * 收费表
 * 
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
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
	 * 用户id
	 */
	private String userId;
	/**
	 * 车牌号
	 */
	private String licencePlate;
	/**
	 * 停车时间
	 */
	private Time time;
	/**
	 * 收费
	 */
	private Double money;

}
