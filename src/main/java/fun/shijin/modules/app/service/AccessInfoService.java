package fun.shijin.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.modules.app.entity.AccessInfoEntity;
import fun.shijin.modules.app.entity.PriceEntity;


import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 车辆进出服务
 *
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
 */
public interface AccessInfoService extends IService<AccessInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 入库
     * @return
     */
    Map<String, Object> entryParking(String platelicense);

    /**
     * 出库
     * @return
     */
    Map<String, Object> outParking(String platelicense, PriceEntity price);


    /**
     * 查询停车场内的固定车位是否已经停车
     * @param licenseplate
     * @return
     */
    List<Date> queryRecord(String licenseplate);
}

