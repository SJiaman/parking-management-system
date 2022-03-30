package fun.shijin.modules.app.controller;

import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.R;
import fun.shijin.modules.app.entity.SpaceEntity;
import fun.shijin.modules.app.service.SpaceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 车位表
 *
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
 */
@RestController
@RequestMapping("app/space")
public class SpaceController {
    @Autowired
    private SpaceService spaceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("pms:space:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = spaceService.queryPage(params);

//        System.out.println(page);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("pms:space:info")
    public R info(@PathVariable("id") Integer id){
		SpaceEntity space = spaceService.getById(id);

        return R.ok().put("space", space);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("pms:space:save")
    public R save(@RequestBody SpaceEntity space){
		spaceService.save(space);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("pms:space:update")
    public R update(@RequestBody SpaceEntity space){
		spaceService.updateById(space);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("pms:space:delete")
    public R delete(@RequestBody Integer[] ids){
		spaceService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
