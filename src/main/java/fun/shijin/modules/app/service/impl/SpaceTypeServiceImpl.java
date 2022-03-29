package fun.shijin.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.Query;
import fun.shijin.modules.app.dao.SpaceTypeDao;
import fun.shijin.modules.app.entity.SpaceTypeEntity;
import fun.shijin.modules.app.service.SpaceTypeService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("spaceTypeService")
public class SpaceTypeServiceImpl extends ServiceImpl<SpaceTypeDao, SpaceTypeEntity> implements SpaceTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpaceTypeEntity> page = this.page(
                new Query<SpaceTypeEntity>().getPage(params),
                new QueryWrapper<SpaceTypeEntity>()
        );

        return new PageUtils(page);
    }

}