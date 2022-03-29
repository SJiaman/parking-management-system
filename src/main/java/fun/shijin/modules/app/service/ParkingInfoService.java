package fun.shijin.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.modules.app.entity.ParkingInfoEntity;


import java.util.Map;

/**
 * 停车场信息表
 *
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
 */
public interface ParkingInfoService extends IService<ParkingInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

