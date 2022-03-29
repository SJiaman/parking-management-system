package fun.shijin.modules.app.controller;

import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.R;
import fun.shijin.modules.app.entity.PriceEntity;
import fun.shijin.modules.app.service.PriceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 收费表
 *
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-03-29 12:51:27
 */
@RestController
@RequestMapping("pms/price")
public class PriceController {
    @Autowired
    private PriceService priceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("pms:price:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = priceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("pms:price:info")
    public R info(@PathVariable("id") Integer id){
		PriceEntity price = priceService.getById(id);

        return R.ok().put("price", price);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("pms:price:save")
    public R save(@RequestBody PriceEntity price){
		priceService.save(price);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("pms:price:update")
    public R update(@RequestBody PriceEntity price){
		priceService.updateById(price);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("pms:price:delete")
    public R delete(@RequestBody Integer[] ids){
		priceService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
