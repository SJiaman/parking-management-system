package fun.shijin.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.shijin.modules.app.entity.AccessInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 车辆进出表
 * 
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
 */
@Mapper
@Repository
public interface AccessInfoDao extends BaseMapper<AccessInfoEntity> {

    @Select("select entry_time" +
            "from pms_space_licenseplate a" +
            "left join pms_access_info b" +
            "on a.license_plate = b.license_plate" +
            "WHERE a.space_id = ${licenseplate}" +
            "and b.out_time is NULL;")
    List<Date> queryRecord(@Param("licenseplate") String licenseplate);
	
}
