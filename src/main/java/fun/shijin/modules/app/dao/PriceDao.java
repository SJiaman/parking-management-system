package fun.shijin.modules.app.dao;

import fun.shijin.modules.app.entity.PriceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 收费表
 * 
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-04-27 13:23:20
 */
@Mapper
@Repository
public interface PriceDao extends BaseMapper<PriceEntity> {
	
}
