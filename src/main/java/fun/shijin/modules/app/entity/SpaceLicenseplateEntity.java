package fun.shijin.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-05-08 20:21:03
 */
@Data
@TableName("pms_space_licenseplate")
public class SpaceLicenseplateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 车位id
	 */
	private Integer spaceId;
	/**
	 * 车牌号
	 */
	private String licensePlate;

}
