package fun.shijin.modules.app.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fun.shijin.modules.app.entity.SpaceLicenseplateEntity;
import fun.shijin.modules.app.service.SpaceLicenseplateService;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.R;



/**
 * 
 *
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-05-08 20:21:03
 */
@RestController
@RequestMapping("app/spacelicenseplate")
public class SpaceLicenseplateController {
    @Autowired
    private SpaceLicenseplateService spaceLicenseplateService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("app:spacelicenseplate:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = spaceLicenseplateService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("app:spacelicenseplate:info")
    public R info(@PathVariable("id") Integer id){
		SpaceLicenseplateEntity spaceLicenseplate = spaceLicenseplateService.getById(id);

        return R.ok().put("spaceLicenseplate", spaceLicenseplate);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("app:spacelicenseplate:save")
    public R save(@RequestBody SpaceLicenseplateEntity spaceLicenseplate){
		spaceLicenseplateService.save(spaceLicenseplate);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("app:spacelicenseplate:update")
    public R update(@RequestBody SpaceLicenseplateEntity spaceLicenseplate){
		spaceLicenseplateService.updateById(spaceLicenseplate);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("app:spacelicenseplate:delete")
    public R delete(@RequestBody Integer[] ids){
		spaceLicenseplateService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
