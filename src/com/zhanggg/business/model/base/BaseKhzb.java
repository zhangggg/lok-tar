package com.zhanggg.business.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseKhzb<M extends BaseKhzb<M>> extends Model<M> implements IBean {

	public M setID(Integer ID) {
		set("id", ID);
		return (M)this;
	}

	public Integer getID() {
		return get("id");
	}

	public M setKhnr(java.lang.String khnr) {
		set("khnr", khnr);
		return (M)this;
	}

	public java.lang.String getKhnr() {
		return get("khnr");
	}

	public M setKfbz(java.lang.String kfbz) {
		set("kfbz", kfbz);
		return (M)this;
	}

	public java.lang.String getKfbz() {
		return get("kfbz");
	}

	public M setYszynr(java.lang.String yszynr) {
		set("yszynr", yszynr);
		return (M)this;
	}

	public java.lang.String getYszynr() {
		return get("yszynr");
	}

	public M setFl(java.lang.String fl) {
		set("fl", fl);
		return (M)this;
	}

	public java.lang.String getFl() {
		return get("fl");
	}

	public M setKhzb(Integer khzb) {
		set("khzb", khzb);
		return (M)this;
	}

	public Integer getKhzb() {
		return get("khzb");
	}

	public M setFs(Integer fs) {
		set("fs", fs);
		return (M)this;
	}

	public Integer getFs() {
		return get("fs");
	}

	public M setUrl(java.lang.String url) {
		set("url", url);
		return (M)this;
	}

	public java.lang.String getUrl() {
		return get("url");
	}

	public M setZcqk(java.lang.String zcqk) {
		set("zcqk", zcqk);
		return (M)this;
	}

	public java.lang.String getZcqk() {
		return get("zcqk");
	}

	public M setPx(Integer px) {
		set("px", px);
		return (M)this;
	}

	public Integer getPx() {
		return get("px");
	}

}
