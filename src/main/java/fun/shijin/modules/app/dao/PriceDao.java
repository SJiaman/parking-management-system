package fun.shijin.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.shijin.modules.app.entity.PriceEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收费表
 * 
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
 */
@Mapper
public interface PriceDao extends BaseMapper<PriceEntity> {
	
}
