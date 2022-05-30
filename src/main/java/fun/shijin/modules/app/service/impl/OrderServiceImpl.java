package fun.shijin.modules.app.service.impl;

import fun.shijin.modules.app.dao.OrderDao;
import fun.shijin.modules.app.entity.OrderEntity;
import fun.shijin.modules.app.service.OrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.Query;




@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String licensePlate = (String)params.get("key");
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
                new QueryWrapper<OrderEntity>()
                        .like(StringUtils.isNotBlank(licensePlate),"license_plate", licensePlate)
        );

        return new PageUtils(page);
    }

}