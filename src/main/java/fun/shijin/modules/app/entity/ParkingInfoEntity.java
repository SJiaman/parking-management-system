package fun.shijin.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 停车场信息表
 * 
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
 */
@Data
@TableName("pms_parking_info")
public class ParkingInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 停车场名
	 */
	private String name;
	/**
	 * 联系人
	 */
	private String contacts;
	/**
	 * 停车场电话
	 */
	private String phone;
	/**
	 * 停车场地址
	 */
	private String address;
	/**
	 * 总车位
	 */
	private Integer totalSpaces;
	/**
	 * 剩余车位
	 */
	private Integer surplusSpaces;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
