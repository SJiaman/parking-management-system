package fun.shijin.modules.pms.dao;

import fun.shijin.modules.pms.entity.ParkingInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 停车场信息表
 * 
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
 */
@Mapper
public interface ParkingInfoDao extends BaseMapper<ParkingInfoEntity> {
	
}
