package fun.shijin.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.shijin.modules.app.entity.ParkingInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 停车场信息表
 * 
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
 */
@Mapper
@Repository
public interface ParkingInfoDao extends BaseMapper<ParkingInfoEntity> {

}
