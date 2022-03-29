package fun.shijin.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.shijin.modules.app.entity.AccessInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车辆进出表
 * 
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
 */
@Mapper
public interface AccessInfoDao extends BaseMapper<AccessInfoEntity> {
	
}
