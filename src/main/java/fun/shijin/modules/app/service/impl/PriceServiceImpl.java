package fun.shijin.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.Query;
import fun.shijin.modules.app.dao.PriceDao;
import fun.shijin.modules.app.entity.PriceEntity;
import fun.shijin.modules.app.service.PriceService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("priceService")
public class PriceServiceImpl extends ServiceImpl<PriceDao, PriceEntity> implements PriceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PriceEntity> page = this.page(
                new Query<PriceEntity>().getPage(params),
                new QueryWrapper<PriceEntity>()
        );

        return new PageUtils(page);
    }

}