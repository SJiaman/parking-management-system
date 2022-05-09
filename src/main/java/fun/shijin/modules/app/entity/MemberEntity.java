package fun.shijin.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 会员表
 * 
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
 */
@Data
@TableName("pms_member")
public class MemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@TableId
	private Integer id;
	/**
	 * 用户名
	 */
	private String name;
	/**
	 * 性别
	 */
	private Integer gender;
	/**
	 * 电话号码
	 */
	private String phone;
	/**
	 * 住址
	 */
	private String address;
	/**
	 * 车位
	 */
	private Integer spaceNumber;
	/**
	 * 购买时间
	 */
	private String createTime;
	/**
	 * 到期时间
	 */
	private String expireTime;

}
