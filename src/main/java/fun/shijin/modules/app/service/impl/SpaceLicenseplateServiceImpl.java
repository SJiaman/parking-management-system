package fun.shijin.modules.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.Query;

import fun.shijin.modules.app.dao.SpaceLicenseplateDao;
import fun.shijin.modules.app.entity.SpaceLicenseplateEntity;
import fun.shijin.modules.app.service.SpaceLicenseplateService;


@Service("spaceLicenseplateService")
public class SpaceLicenseplateServiceImpl extends ServiceImpl<SpaceLicenseplateDao, SpaceLicenseplateEntity> implements SpaceLicenseplateService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpaceLicenseplateEntity> page = this.page(
                new Query<SpaceLicenseplateEntity>().getPage(params),
                new QueryWrapper<SpaceLicenseplateEntity>()
        );

        return new PageUtils(page);
    }

}