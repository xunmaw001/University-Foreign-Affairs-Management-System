package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 回国总结
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2022-04-27 17:11:27
 */
@TableName("huiguozongjie")
public class HuiguozongjieEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public HuiguozongjieEntity() {
		
	}
	
	public HuiguozongjieEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 出国编号
	 */
					
	private String chuguobianhao;
	
	/**
	 * 证照类型
	 */
					
	private String zhengzhaoleixing;
	
	/**
	 * 证照号码
	 */
					
	private String zhengzhaohaoma;
	
	/**
	 * 回国时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date huiguoshijian;
	
	/**
	 * 回结总结
	 */
					
	private String huijiezongjie;
	
	/**
	 * 教师工号
	 */
					
	private String jiaoshigonghao;
	
	/**
	 * 教师姓名
	 */
					
	private String jiaoshixingming;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：出国编号
	 */
	public void setChuguobianhao(String chuguobianhao) {
		this.chuguobianhao = chuguobianhao;
	}
	/**
	 * 获取：出国编号
	 */
	public String getChuguobianhao() {
		return chuguobianhao;
	}
	/**
	 * 设置：证照类型
	 */
	public void setZhengzhaoleixing(String zhengzhaoleixing) {
		this.zhengzhaoleixing = zhengzhaoleixing;
	}
	/**
	 * 获取：证照类型
	 */
	public String getZhengzhaoleixing() {
		return zhengzhaoleixing;
	}
	/**
	 * 设置：证照号码
	 */
	public void setZhengzhaohaoma(String zhengzhaohaoma) {
		this.zhengzhaohaoma = zhengzhaohaoma;
	}
	/**
	 * 获取：证照号码
	 */
	public String getZhengzhaohaoma() {
		return zhengzhaohaoma;
	}
	/**
	 * 设置：回国时间
	 */
	public void setHuiguoshijian(Date huiguoshijian) {
		this.huiguoshijian = huiguoshijian;
	}
	/**
	 * 获取：回国时间
	 */
	public Date getHuiguoshijian() {
		return huiguoshijian;
	}
	/**
	 * 设置：回结总结
	 */
	public void setHuijiezongjie(String huijiezongjie) {
		this.huijiezongjie = huijiezongjie;
	}
	/**
	 * 获取：回结总结
	 */
	public String getHuijiezongjie() {
		return huijiezongjie;
	}
	/**
	 * 设置：教师工号
	 */
	public void setJiaoshigonghao(String jiaoshigonghao) {
		this.jiaoshigonghao = jiaoshigonghao;
	}
	/**
	 * 获取：教师工号
	 */
	public String getJiaoshigonghao() {
		return jiaoshigonghao;
	}
	/**
	 * 设置：教师姓名
	 */
	public void setJiaoshixingming(String jiaoshixingming) {
		this.jiaoshixingming = jiaoshixingming;
	}
	/**
	 * 获取：教师姓名
	 */
	public String getJiaoshixingming() {
		return jiaoshixingming;
	}

}
