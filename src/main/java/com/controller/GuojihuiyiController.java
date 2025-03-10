package com.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.GuojihuiyiEntity;
import com.entity.view.GuojihuiyiView;

import com.service.GuojihuiyiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 国际会议
 * 后端接口
 * @author 
 * @email 
 * @date 2022-04-27 17:11:27
 */
@RestController
@RequestMapping("/guojihuiyi")
public class GuojihuiyiController {
    @Autowired
    private GuojihuiyiService guojihuiyiService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,GuojihuiyiEntity guojihuiyi, 
		HttpServletRequest request){

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			guojihuiyi.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<GuojihuiyiEntity> ew = new EntityWrapper<GuojihuiyiEntity>();
    	PageUtils page = guojihuiyiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, guojihuiyi), params), params));
		request.setAttribute("data", page);
        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,GuojihuiyiEntity guojihuiyi, 
		HttpServletRequest request){
        EntityWrapper<GuojihuiyiEntity> ew = new EntityWrapper<GuojihuiyiEntity>();
    	PageUtils page = guojihuiyiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, guojihuiyi), params), params));
		request.setAttribute("data", page);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( GuojihuiyiEntity guojihuiyi){
       	EntityWrapper<GuojihuiyiEntity> ew = new EntityWrapper<GuojihuiyiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( guojihuiyi, "guojihuiyi")); 
        return R.ok().put("data", guojihuiyiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(GuojihuiyiEntity guojihuiyi){
        EntityWrapper< GuojihuiyiEntity> ew = new EntityWrapper< GuojihuiyiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( guojihuiyi, "guojihuiyi")); 
		GuojihuiyiView guojihuiyiView =  guojihuiyiService.selectView(ew);
		return R.ok("查询国际会议成功").put("data", guojihuiyiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        GuojihuiyiEntity guojihuiyi = guojihuiyiService.selectById(id);
        return R.ok().put("data", guojihuiyi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        GuojihuiyiEntity guojihuiyi = guojihuiyiService.selectById(id);
        return R.ok().put("data", guojihuiyi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GuojihuiyiEntity guojihuiyi, HttpServletRequest request){
    	guojihuiyi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(guojihuiyi);

        guojihuiyiService.insert(guojihuiyi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody GuojihuiyiEntity guojihuiyi, HttpServletRequest request){
    	guojihuiyi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(guojihuiyi);

        guojihuiyiService.insert(guojihuiyi);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody GuojihuiyiEntity guojihuiyi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(guojihuiyi);
        guojihuiyiService.updateById(guojihuiyi);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        guojihuiyiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);
		
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date()); 
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}
		
		Wrapper<GuojihuiyiEntity> wrapper = new EntityWrapper<GuojihuiyiEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			wrapper.eq("jiaoshigonghao", (String)request.getSession().getAttribute("username"));
		}

		int count = guojihuiyiService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	
	





}
