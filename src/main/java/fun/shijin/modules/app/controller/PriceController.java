package fun.shijin.modules.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import fun.shijin.modules.app.entity.PriceEntity;
import fun.shijin.modules.app.service.PriceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import fun.shijin.common.utils.PageUtils;
import fun.shijin.common.utils.R;



/**
 * 收费表
 *
 * @author tcq
 * @email 2433313140@qq.com
 * @date 2022-04-27 13:23:20
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
    @RequestMapping("/info")
//    @RequiresPermissions("pms:price:info")
    public R info(){
        PriceEntity price = priceService.getOne(new LambdaQueryWrapper<PriceEntity>().last("limit 1"));
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
