package com.zhanggg.business.controller;

import com.jfinal.core.Controller;

public class HelloController extends Controller {
	
	public void index(){
		render("/page/main.html");
	}
	
	public void fileUpload(){
		setAttr("_id", getPara("id"));
		render("/page/file.html");
	}
}
