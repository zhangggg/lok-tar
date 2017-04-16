package com.zhanggg.business.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseLj<M extends BaseLj<M>> extends Model<M> implements IBean {

	public M setID(java.lang.String ID) {
		set("ID", ID);
		return (M)this;
	}

	public java.lang.String getID() {
		return get("ID");
	}

	public M setContext(java.lang.String context) {
		set("context", context);
		return (M)this;
	}

	public java.lang.String getContext() {
		return get("context");
	}

	public M setKhzbid(java.lang.String khzbid) {
		set("khzbid", khzbid);
		return (M)this;
	}

	public java.lang.String getKhzbid() {
		return get("khzbid");
	}

	public M setUsername(java.lang.String username) {
		set("username", username);
		return (M)this;
	}

	public java.lang.String getUsername() {
		return get("username");
	}

	public M setPassword(java.lang.String password) {
		set("password", password);
		return (M)this;
	}

	public java.lang.String getPassword() {
		return get("password");
	}

	public M setApp(java.lang.String app) {
		set("app", app);
		return (M)this;
	}

	public java.lang.String getApp() {
		return get("app");
	}

	public M setPath(java.lang.String path) {
		set("path", path);
		return (M)this;
	}

	public java.lang.String getPath() {
		return get("path");
	}

	public M setType(java.lang.String type) {
		set("type", type);
		return (M)this;
	}

	public java.lang.String getType() {
		return get("type");
	}

}
