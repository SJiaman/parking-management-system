package fun.shijin.modules.pms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.Query;

import fun.shijin.modules.pms.dao.PriceDao;
import fun.shijin.modules.pms.entity.PriceEntity;
import fun.shijin.modules.pms.service.PriceService;


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