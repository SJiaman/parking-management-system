package fun.shijin.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.R;
import fun.shijin.modules.app.entity.AccessInfoEntity;
import fun.shijin.modules.app.entity.ParkingInfoEntity;
import fun.shijin.modules.app.entity.PriceEntity;
import fun.shijin.modules.app.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;


/**
 * 车辆进出
 *
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
 */
@RestController
@RequestMapping("pms/access")
public class AccessController {
    @Autowired
    private AccessInfoService accessInfoService;

    @Autowired
    private LicensePlateService licensePlateService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private ParkingInfoService parkingInfoService;


    /**
     * 入库
     * 有无空位，识别车牌，增加记录
     */
    @RequestMapping("/entry")
//    @RequiresPermissions("pms:accessinfo:list")
    public R entryParking(@RequestParam("file") MultipartFile file) {
        ParkingInfoEntity parkingInfo = parkingInfoService.info();

        if (parkingInfo.getTotalSpaces() <= parkingInfo.getSurplusSpaces()) {
            return R.error().put("data", "车位已停满，请重新寻找车场");
        }

        String licensePlate = licensePlateService.licensePlateOrc(file);
        // 判断车牌号是否为空，为空返回识别失败
        if (licensePlate == null || licensePlate.length() == 0) {
            return R.error().put("data", "识别失败,请重新扫描车牌");
        }

        Map<String, Object> map = accessInfoService.entryParking(licensePlate);

        return R.ok(map).put("licensePlate", licensePlate);
    }

    /**
     * 出库
     */
    @RequestMapping("/out")
//    @RequiresPermissions("pms:accessinfo:list")
    public R outParking(@RequestParam("file") MultipartFile file) {
        String licensePlate = licensePlateService.licensePlateOrc(file);
        // 判断车牌号是否为空，为空返回识别失败
        if (licensePlate == null || licensePlate.length() == 0) {
            return R.error().put("data", "识别失败,请重新扫描车牌");
        }
        AccessInfoEntity accessInfoEntity = accessInfoService.
                getOne(new LambdaQueryWrapper<AccessInfoEntity>()
                        .eq(AccessInfoEntity::getLicensePlate, licensePlate)
                        .isNull(AccessInfoEntity::getOutTime));

        if (accessInfoEntity.getCarType() == 1) {
            return R.ok().put("outMsg", "会员车" + licensePlate + "祝你一路平安");
        }

        BigDecimal cost = priceService.costCalculate(accessInfoEntity);

        return R.ok().put("outMsg", "临时车" + licensePlate + "祝你一路平安");
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("pms:accessinfo:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = accessInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("pms:accessinfo:info")
    public R info(@PathVariable("id") Integer id) {
        AccessInfoEntity accessInfo = accessInfoService.getById(id);

        return R.ok().put("accessInfo", accessInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("pms:accessinfo:save")
    public R save(@RequestBody AccessInfoEntity accessInfo) {
        accessInfoService.save(accessInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("pms:accessinfo:update")
    public R update(@RequestBody AccessInfoEntity accessInfo) {
        accessInfoService.updateById(accessInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("pms:accessinfo:delete")
    public R delete(@RequestBody Integer[] ids) {
        accessInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
