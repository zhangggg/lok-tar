package com.zhanggg.business.controller;

import com.jfinal.core.Controller;
import com.zhanggg.business.model.Lj;

public class LjController extends Controller{
	
	public void byId(){
		if (!isParaBlank("khzbid")) {
			String sql = "select * from lj where type=1 and  khzbid = " + getPara("khzbid");
			renderJson(Lj.dao.find(sql));
		}else {
			renderJson();
		}
	}
	
}
