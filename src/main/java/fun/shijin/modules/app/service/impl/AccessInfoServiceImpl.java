package fun.shijin.modules.app.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.Query;
import fun.shijin.modules.app.dao.AccessInfoDao;
import fun.shijin.modules.app.entity.AccessInfoEntity;
import fun.shijin.modules.app.entity.PriceEntity;
import fun.shijin.modules.app.entity.SpaceLicenseplateEntity;
import fun.shijin.modules.app.service.AccessInfoService;
import fun.shijin.modules.app.service.SpaceLicenseplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("accessInfoService")
public class AccessInfoServiceImpl extends ServiceImpl<AccessInfoDao, AccessInfoEntity> implements AccessInfoService {
    @Autowired
    AccessInfoDao accessDao;

    @Autowired
    SpaceLicenseplateService spaceLicenseplateService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AccessInfoEntity> page = this.page(
                new Query<AccessInfoEntity>().getPage(params),
                new QueryWrapper<AccessInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Map<String, Object> entryParking(String licensePlate) {
        HashMap<String, Object> data = new HashMap<>();
        AccessInfoEntity accessInfoEntity = new AccessInfoEntity();
        int record = spaceLicenseplateService.count(
                new LambdaQueryWrapper<SpaceLicenseplateEntity>()
                        .eq(SpaceLicenseplateEntity::getLicensePlate, licensePlate));
        if (record != 0) {
            accessInfoEntity.setCarType(1);
            data.put("carType", "会员车");
        } else {
            accessInfoEntity.setCarType(0);
            data.put("carType", "临时车");
        }
        accessInfoEntity.setLicensePlate(licensePlate);
        accessInfoEntity.setEntryTime(DateUtil.parse(DateUtil.now()));
        baseMapper.insert(accessInfoEntity);
        return data;
    }

    @Override
    public Map<String, Object> outParking(String platelicense, PriceEntity price) {
        return null;
    }

    @Override
    public List<Date> queryRecord(String licenseplate) {
        return accessDao.queryRecord(licenseplate);
    }


}