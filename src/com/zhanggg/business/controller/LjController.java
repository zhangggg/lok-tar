package com.zhanggg.business.controller;

import java.io.File;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.render.ContentType;
import com.jfinal.upload.UploadFile;
import com.zhanggg.business.model.Lj;

public class LjController extends Controller{
	
	public void byId(){
		//app字段用来存放是否有文件上传
		//如果有，app 字段则设置为ID
		if (!isParaBlank("khzbid")) {
			String sql = "select * from lj where type=1 and  khzbid = " + getPara("khzbid");
			List<Lj> ljs = Lj.dao.find(sql);
			for (int i = 0; i < ljs.size(); i++) {
				ljs.get(i).setApp("");
				String path = PathKit.getWebRootPath() + "/download/" + ljs.get(i).getID() + "/";
				File filePath = new File(path);
				if (filePath.exists()) {
					if (filePath.list().length > 0) {
						ljs.get(i).setApp(ljs.get(i).getID() + "");
					}
				}
			}
			renderJson(ljs);
		}else {
			renderJson();
		}
	}
	
	public void download(){
		String id = getPara("id").trim();
		String path = PathKit.getWebRootPath() + "/download/" + id + "/";
		File pathFile = new File(path);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
			renderJson();
		}else {
			File[] sons = pathFile.listFiles();
			if (sons.length > 0) {
				renderFile(sons[0]);
			}else {
				renderJson();
			}
		}
	}
	
	
	public void upload(){
		//200M
		UploadFile file = super.getFile("uploadFile","/temp",200*1024*1024,"utf-8");
		String id = getPara("id").trim();
		
		String path = PathKit.getWebRootPath() + "/download/" + id + "/";
		File pathFile = new File(path);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}else {
			File[] sons = pathFile.listFiles();
			if (sons.length > 0) {
				for (int i = 0; i < sons.length; i++) {
					sons[i].delete();
				}
			}
		}
		File targetFile = new File(path + file.getOriginalFileName());
		if (targetFile.exists()) {
			targetFile.delete();
		}
		
		boolean result = file.getFile().renameTo(targetFile);
	    if (result) {
	    	renderText("<html>上传成功!</html>", ContentType.HTML);
		}else {
			renderText("<html>上传失败!</html>", ContentType.HTML);
		}
	}
}
