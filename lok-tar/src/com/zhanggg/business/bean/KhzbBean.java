package com.zhanggg.business.bean;

import java.io.Serializable;
import java.util.List;

//考核指标
public class KhzbBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6991838383202781524L;

	private Integer id;
	private String khnr;
	private String shortKhnr;
	private String kfbz;
	private String yszynr;
	private String fl;
	private Integer khzb;
	private Integer fs;
	private String url;
	private String zcqk;
	private Integer px;
	private List<KhzbBean> sons;
	
	public KhzbBean(Integer id, String khnr, String fl, Integer khzb, Integer px) {
		super();
		this.id = id;
		this.khnr = khnr;
		if (khnr != null && khnr.length() > 15) {
			this.shortKhnr = khnr.substring(0,15) + "...";
		}else {
			this.shortKhnr = khnr ; 
		}
		this.fl = fl;
		this.khzb = khzb;
		this.px = px;
	}
	public KhzbBean(Integer id, String khnr, String kfbz, String yszynr, String fl, Integer khzb, Integer fs,
			String url, String zcqk, Integer px) {
		super();
		this.id = id;
		this.khnr = khnr;
		this.kfbz = kfbz;
		this.yszynr = yszynr;
		this.fl = fl;
		this.khzb = khzb;
		this.fs = fs;
		this.url = url;
		this.zcqk = zcqk;
		this.px = px;
	}
	public String getShortKhnr() {
		return shortKhnr;
	}
	public void setShortKhnr(String shortKhnr) {
		this.shortKhnr = shortKhnr;
	}
	public List<KhzbBean> getSons() {
		return sons;
	}
	public void setSons(List<KhzbBean> sons) {
		this.sons = sons;
	}
	public KhzbBean() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKhnr() {
		return khnr;
	}
	public void setKhnr(String khnr) {
		this.khnr = khnr;
	}
	public String getKfbz() {
		return kfbz;
	}
	public void setKfbz(String kfbz) {
		this.kfbz = kfbz;
	}
	public String getYszynr() {
		return yszynr;
	}
	public void setYszynr(String yszynr) {
		this.yszynr = yszynr;
	}
	public String getFl() {
		return fl;
	}
	public void setFl(String fl) {
		this.fl = fl;
	}
	public Integer getKhzb() {
		return khzb;
	}
	public void setKhzb(Integer khzb) {
		this.khzb = khzb;
	}
	public Integer getFs() {
		return fs;
	}
	public void setFs(Integer fs) {
		this.fs = fs;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getZcqk() {
		return zcqk;
	}
	public void setZcqk(String zcqk) {
		this.zcqk = zcqk;
	}
	public Integer getPx() {
		return px;
	}
	public void setPx(Integer px) {
		this.px = px;
	}
	
}
