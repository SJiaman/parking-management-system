package fun.shijin.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.modules.app.entity.AccessInfoEntity;
import fun.shijin.modules.app.entity.PriceEntity;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 收费表
 *
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-04-27 13:23:20
 */
public interface PriceService extends IService<PriceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PriceEntity info();

    BigDecimal costCalculate(AccessInfoEntity accessInfo);
}

