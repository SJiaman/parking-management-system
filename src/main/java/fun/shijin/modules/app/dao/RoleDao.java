package fun.shijin.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.shijin.modules.app.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色表
 * 
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
 */
@Mapper
public interface RoleDao extends BaseMapper<RoleEntity> {
	
}
