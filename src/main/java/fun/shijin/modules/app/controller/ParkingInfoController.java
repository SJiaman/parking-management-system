package fun.shijin.modules.app.controller;

import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.R;
import fun.shijin.modules.app.entity.ParkingInfoEntity;
import fun.shijin.modules.app.service.ParkingInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 停车场信息表
 *
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
 */
@RestController
@RequestMapping("pms/parkinginfo")
public class ParkingInfoController {
    @Autowired
    private ParkingInfoService parkingInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("pms:parkinginfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = parkingInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("pms:parkinginfo:info")
    public R info(@PathVariable("id") Integer id){
		ParkingInfoEntity parkingInfo = parkingInfoService.getById(id);

        return R.ok().put("parkingInfo", parkingInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("pms:parkinginfo:save")
    public R save(@RequestBody ParkingInfoEntity parkingInfo){
		parkingInfoService.save(parkingInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("pms:parkinginfo:update")
    public R update(@RequestBody ParkingInfoEntity parkingInfo){
		parkingInfoService.updateById(parkingInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("pms:parkinginfo:delete")
    public R delete(@RequestBody Integer[] ids){
		parkingInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
