package fun.shijin.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.modules.app.entity.SpaceLicenseplateEntity;

import java.util.Map;

/**
 * 
 *
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-05-08 20:21:03
 */
public interface SpaceLicenseplateService extends IService<SpaceLicenseplateEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

