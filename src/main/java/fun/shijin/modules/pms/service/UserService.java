package fun.shijin.modules.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.modules.pms.entity.UserEntity;

import java.util.Map;

/**
 * 用户表
 *
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

