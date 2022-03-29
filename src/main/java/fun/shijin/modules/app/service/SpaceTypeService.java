package fun.shijin.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.modules.app.entity.SpaceTypeEntity;


import java.util.Map;

/**
 * 车位类型表
 *
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
 */
public interface SpaceTypeService extends IService<SpaceTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

