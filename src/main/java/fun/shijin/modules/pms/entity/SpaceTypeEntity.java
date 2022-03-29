package fun.shijin.modules.pms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 车位类型表
 * 
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
 */
@Data
@TableName("pms_space_type")
public class SpaceTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 车位id
	 */
	@TableId
	private Integer id;
	/**
	 * 车位类型
	 */
	private String type;

}
