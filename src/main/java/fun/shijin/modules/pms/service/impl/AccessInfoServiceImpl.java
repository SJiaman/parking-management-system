package fun.shijin.modules.pms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.Query;

import fun.shijin.modules.pms.dao.AccessInfoDao;
import fun.shijin.modules.pms.entity.AccessInfoEntity;
import fun.shijin.modules.pms.service.AccessInfoService;


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