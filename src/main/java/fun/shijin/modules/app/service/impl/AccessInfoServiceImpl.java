package fun.shijin.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.Query;
import fun.shijin.modules.app.dao.AccessInfoDao;
import fun.shijin.modules.app.entity.AccessInfoEntity;
import fun.shijin.modules.app.service.AccessInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("accessInfoService")
public class AccessInfoServiceImpl extends ServiceImpl<AccessInfoDao, AccessInfoEntity> implements AccessInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AccessInfoEntity> page = this.page(
                new Query<AccessInfoEntity>().getPage(params),
                new QueryWrapper<AccessInfoEntity>()
        );

        return new PageUtils(page);
    }

}