package com.zhanggg.business.bean;

import java.io.Serializable;
import java.util.List;

public class ZlmlBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6470755771397922925L;

	private Integer id;
	
	private String zj;
	
	private String nr;
	
	private Integer fatherZJ;
	
	private Integer px;
	
	private List<ZlmlBean> sons;
	
	public ZlmlBean() {
		super();
	}

	public ZlmlBean(Integer id, String zj, String nr,Integer fatherZJ, Integer px) {
		super();
		this.id = id;
		this.zj = zj;
		this.nr = nr;
		this.fatherZJ = fatherZJ;
		this.px = px;
	}

	public Integer getFatherZJ() {
		return fatherZJ;
	}

	public void setFatherZJ(Integer fatherZJ) {
		this.fatherZJ = fatherZJ;
	}

	public List<ZlmlBean> getSons() {
		return sons;
	}

	public void setSons(List<ZlmlBean> sons) {
		this.sons = sons;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getZj() {
		return zj;
	}

	public void setZj(String zj) {
		this.zj = zj;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public Integer getPx() {
		return px;
	}

	public void setPx(Integer px) {
		this.px = px;
	}
	
}
