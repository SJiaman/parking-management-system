package fun.shijin.modules.pms.dao;

import fun.shijin.modules.pms.entity.AccessInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
