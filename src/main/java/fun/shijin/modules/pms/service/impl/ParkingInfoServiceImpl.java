package fun.shijin.modules.pms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.Query;

import fun.shijin.modules.pms.dao.ParkingInfoDao;
import fun.shijin.modules.pms.entity.ParkingInfoEntity;
import fun.shijin.modules.pms.service.ParkingInfoService;


@Service("parkingInfoService")
public class ParkingInfoServiceImpl extends ServiceImpl<ParkingInfoDao, ParkingInfoEntity> implements ParkingInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ParkingInfoEntity> page = this.page(
                new Query<ParkingInfoEntity>().getPage(params),
                new QueryWrapper<ParkingInfoEntity>()
        );

        return new PageUtils(page);
    }

}