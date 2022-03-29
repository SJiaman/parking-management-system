package fun.shijin.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.Query;
import fun.shijin.modules.app.dao.SpaceDao;
import fun.shijin.modules.app.entity.SpaceEntity;
import fun.shijin.modules.app.service.SpaceService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("spaceService")
public class SpaceServiceImpl extends ServiceImpl<SpaceDao, SpaceEntity> implements SpaceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpaceEntity> page = this.page(
                new Query<SpaceEntity>().getPage(params),
                new QueryWrapper<SpaceEntity>()
        );

        return new PageUtils(page);
    }

}