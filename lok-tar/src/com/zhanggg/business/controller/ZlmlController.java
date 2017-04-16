package com.zhanggg.business.controller;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.ehcache.CacheKit;
import com.zhanggg.business.bean.ZlmlBean;
import com.zhanggg.business.model.Zlml;
/**
 * 资料目录
 * @author zolox
 *
 */
public class ZlmlController extends Controller{
	
	public void byId(){
		if (!isParaBlank("ID")) {
			renderJson(Zlml.dao.findById(getPara("ID")));
		}else {
			renderJson();
		}
	}
	
	public void tree(){
		String sql = "select * from zlml ";
		if (!isParaBlank("keyword")) {
			sql += " where nr like '%" + getPara("keyword") + "%'";
			List<Zlml> zlmls = Zlml.dao.find(sql);
			List<ZlmlBean> zlmlBeans = new ArrayList<ZlmlBean>();
			for (Zlml _zlml : zlmls) {
				ZlmlBean _bean = new ZlmlBean(
						_zlml.getID(),
						_zlml.getZj(),
						_zlml.getNr(),
						_zlml.getFatherZJ(),
						_zlml.getPx());
				zlmlBeans.add(_bean);
				renderJson(zlmlBeans);
			}
		} else {
			ZlmlBean result = CacheKit.get("zhanggg", "zlml");
			if (result == null) {
				List<Zlml> zlmls = Zlml.dao.find(sql);
				List<ZlmlBean> zlmlBeans = new ArrayList<ZlmlBean>();
				ZlmlBean father = null;
				for (Zlml _zlml : zlmls) {
					ZlmlBean _bean = new ZlmlBean(
							_zlml.getID(),
							_zlml.getZj(),
							_zlml.getNr(),
							_zlml.getFatherZJ(),
							_zlml.getPx());
					
					if (father == null) {
						father = _bean;
					}else {
						if (father.getFatherZJ() > _bean.getFatherZJ()) {
							father = _bean;
						}
					}
					zlmlBeans.add(_bean);
				}
				father.setSons(buildTree(zlmlBeans, father));
				CacheKit.put("zhanggg", "zlml", father);
				renderJson(father);
			}else {
				renderJson(result);
			}
		}
	}
	
	private List<ZlmlBean> buildTree(List<ZlmlBean> list , ZlmlBean father){
		List<ZlmlBean> sons = new ArrayList<ZlmlBean>();
		for (ZlmlBean _bean : list) {
			if (_bean.getFatherZJ().intValue() == father.getId().intValue()) {
				_bean.setSons(buildTree(list,_bean));
				sons.add(_bean);
			}
		}
		return sons;
	}
	
}
