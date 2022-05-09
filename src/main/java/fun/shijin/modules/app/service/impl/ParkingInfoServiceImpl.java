package fun.shijin.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.Query;
import fun.shijin.modules.app.dao.ParkingInfoDao;
import fun.shijin.modules.app.entity.ParkingInfoEntity;
import fun.shijin.modules.app.service.ParkingInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;


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

    @Override
    public ParkingInfoEntity info() {
        ParkingInfoEntity parkingInfo = baseMapper.selectOne(new LambdaQueryWrapper<ParkingInfoEntity>().last("limit 1"));
        return parkingInfo;
    }

}