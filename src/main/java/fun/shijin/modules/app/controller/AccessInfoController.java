package fun.shijin.modules.app.controller;

import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.R;
import fun.shijin.modules.app.entity.AccessInfoEntity;
import fun.shijin.modules.app.service.AccessInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 车辆进出表
 *
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
 */
@RestController
@RequestMapping("pms/accessinfo")
public class AccessInfoController {
    @Autowired
    private AccessInfoService accessInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("pms:accessinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = accessInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("pms:accessinfo:info")
    public R info(@PathVariable("id") Integer id){
		AccessInfoEntity accessInfo = accessInfoService.getById(id);

        return R.ok().put("accessInfo", accessInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("pms:accessinfo:save")
    public R save(@RequestBody AccessInfoEntity accessInfo){
		accessInfoService.save(accessInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("pms:accessinfo:update")
    public R update(@RequestBody AccessInfoEntity accessInfo){
		accessInfoService.updateById(accessInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("pms:accessinfo:delete")
    public R delete(@RequestBody Integer[] ids){
		accessInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
