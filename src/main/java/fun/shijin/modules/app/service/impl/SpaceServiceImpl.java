package fun.shijin.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.Query;
import fun.shijin.modules.app.dao.SpaceDao;
import fun.shijin.modules.app.entity.SpaceEntity;
import fun.shijin.modules.app.entity.SpaceLicenseplateEntity;
import fun.shijin.modules.app.service.SpaceLicenseplateService;
import fun.shijin.modules.app.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("spaceService")
public class SpaceServiceImpl extends ServiceImpl<SpaceDao, SpaceEntity> implements SpaceService {
    @Autowired
    SpaceLicenseplateService spaceLicenseplateService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpaceEntity> page = this.page(
                new Query<SpaceEntity>().getPage(params),
                new QueryWrapper<SpaceEntity>()
        );

        page.getRecords().forEach(spaceEntity -> {
            LambdaQueryWrapper<SpaceLicenseplateEntity> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SpaceLicenseplateEntity::getSpaceId,spaceEntity.getId());
            List<SpaceLicenseplateEntity> list = spaceLicenseplateService.list(wrapper);
            spaceEntity.setLicenseplate(list);
        });
        return new PageUtils(page);
    }

}