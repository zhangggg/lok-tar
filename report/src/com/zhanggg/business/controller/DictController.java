package com.zhanggg.business.controller;

import com.jfinal.core.Controller;
import com.zhanggg.business.model.Dict;

public class DictController extends Controller{
	
	public void dt(){
		if (!isParaBlank("code")) {
			renderJson(Dict.dao.findFirst("select * from dict where code = ?" ,getPara("code")));
		}else {
			renderJson();
		}
	}
	
	public void uptByCode(){
		String values = getPara("values");
		Dict.dao.findFirst("select * from dict where code = ?" ,getPara("code")).set("val", values).update();
		renderJson();
	}
}
