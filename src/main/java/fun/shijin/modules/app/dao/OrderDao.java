package fun.shijin.modules.app.dao;

import fun.shijin.modules.app.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单表
 * 
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-04-27 13:23:20
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
