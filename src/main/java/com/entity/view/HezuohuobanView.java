package com.entity.view;

import com.entity.HezuohuobanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 合作伙伴
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2022-04-27 17:11:27
 */
@TableName("hezuohuoban")
public class HezuohuobanView  extends HezuohuobanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public HezuohuobanView(){
	}
 
 	public HezuohuobanView(HezuohuobanEntity hezuohuobanEntity){
 	try {
			BeanUtils.copyProperties(this, hezuohuobanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
