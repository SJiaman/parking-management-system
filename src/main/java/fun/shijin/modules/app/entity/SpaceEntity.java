package fun.shijin.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 车位表
 * 
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
 */
@Data
@TableName("pms_space")
public class SpaceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 车位id
	 */
	@TableId
	private Integer id;
	/**
	 * 车位编号
	 */
	private String number;
	/**
	 * 车位位置
	 */
	private String section;
	/**
	 * 会员
	 */
	private String member;
	/**
	 * 车位状态(0:已售，1:在售)
	 */
	private Integer state;
	/**
	 * 车位类型(0:固定车位，1:出租车位，2:临时车位)
	 */
	private Integer type;

	@TableField(exist = false)
	private  List<SpaceLicenseplateEntity> licenseplate;
}
