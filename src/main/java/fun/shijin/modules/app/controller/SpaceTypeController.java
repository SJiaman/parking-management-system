package fun.shijin.modules.app.controller;

import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.R;
import fun.shijin.modules.app.entity.SpaceTypeEntity;
import fun.shijin.modules.app.service.SpaceTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 车位类型表
 *
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
 */
@RestController
@RequestMapping("pms/spacetype")
public class SpaceTypeController {
    @Autowired
    private SpaceTypeService spaceTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("pms:spacetype:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = spaceTypeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("pms:spacetype:info")
    public R info(@PathVariable("id") Integer id){
		SpaceTypeEntity spaceType = spaceTypeService.getById(id);

        return R.ok().put("spaceType", spaceType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("pms:spacetype:save")
    public R save(@RequestBody SpaceTypeEntity spaceType){
		spaceTypeService.save(spaceType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("pms:spacetype:update")
    public R update(@RequestBody SpaceTypeEntity spaceType){
		spaceTypeService.updateById(spaceType);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("pms:spacetype:delete")
    public R delete(@RequestBody Integer[] ids){
		spaceTypeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
