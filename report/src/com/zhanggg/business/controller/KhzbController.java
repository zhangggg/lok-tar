package com.zhanggg.business.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.ehcache.CacheKit;
import com.zhanggg.business.bean.KhzbBean;
import com.zhanggg.business.model.Khzb;

public class KhzbController extends Controller{
	
	public void getScores(){
		String sql = "select sum(fs) from khzb ";
		if (!isParaBlank("fl")) {
			sql += " where fl like '%" + getPara("fl") + "%'";
		}
		Integer score = Db.queryInt(sql);
		Map<String,Integer> result = new HashMap<String,Integer>();
		result.put("count", score);
		renderJson(result);
	}
	
	public void update(){
		Integer id = getParaToInt("ID");
		String zcqk = getPara("zcqk");
		Khzb.dao.findById(id).set("zcqk", zcqk).update();
		renderJson();
	}
	
	public void marking(){
		Integer id = getParaToInt("ID");
		String fs = getPara("fs");
		Khzb.dao.findById(id).set("fs", fs).update();
		renderJson();
	}
	
	public void byId(){
		if (!isParaBlank("ID")) {
			renderJson(Khzb.dao.findById(getPara("ID")));
		}else {
			renderJson();
		}
	}
	
	public void tree(){
		String sql = "select * from khzb ";
		boolean isFl = !isParaBlank("fl") ; //是否有分类 
		if (isFl) {
			sql += " where fl like '%" + getPara("fl") + "%' or fl is null";
			List<Khzb> khzbs = Khzb.dao.find(sql);
			List<KhzbBean> khzbBeans = new ArrayList<KhzbBean>();
			List<KhzbBean> fathers = new ArrayList<KhzbBean>();
			for (Khzb _khzb : khzbs) {
				KhzbBean _bean = new KhzbBean(
						_khzb.getID(),
						_khzb.getKhnr(),
						_khzb.getFl(),
						_khzb.getKhzb(),
						_khzb.getPx());
				if (_bean.getKhzb().intValue() == 0) {
					fathers.add(_bean);
				}else {
					khzbBeans.add(_bean);
				}
			}
			buildTree(fathers,khzbBeans);
			Iterator<KhzbBean> it = fathers.iterator();
			while (it.hasNext()) {
				KhzbBean _next = it.next();
				if (_next.getSons() == null || _next.getSons().size() < 1) {
					it.remove();
				}
			}
			renderJson(fathers);
		}else {
			KhzbBean result = CacheKit.get("zhanggg", "khzb");
			if (result == null) {
				List<Khzb> khzbs = Khzb.dao.find(sql);
				List<KhzbBean> khzbBeans = new ArrayList<KhzbBean>();
				List<KhzbBean> fathers = new ArrayList<KhzbBean>();
				for (Khzb _khzb : khzbs) {
					KhzbBean _bean = new KhzbBean(
							_khzb.getID(),
							_khzb.getKhnr(),
							_khzb.getFl(),
							_khzb.getKhzb(),
							_khzb.getPx());
					if (_bean.getKhzb().intValue() == 0) {
						fathers.add(_bean);
					}else {
						khzbBeans.add(_bean);
					}
				}
				buildTree(fathers,khzbBeans);
				
				CacheKit.put("khzb", "all", fathers);
				renderJson(fathers);
			}else {
				renderJson(result);
			}
		}
	}
	private void buildTree(List<KhzbBean> fathers,List<KhzbBean> khzbBeans){
		for (KhzbBean _khzbBean : fathers) {
			for (int i = 0; i < khzbBeans.size(); i++) {
				if (_khzbBean.getId().intValue() == khzbBeans.get(i).getKhzb().intValue()) {
					List<KhzbBean> list = _khzbBean.getSons();
					if (list == null ) {
						List<KhzbBean> _list = new ArrayList<KhzbBean>();
						_list.add(khzbBeans.get(i));
						_khzbBean.setSons(_list);
					}else {
						_khzbBean.getSons().add(khzbBeans.get(i));
					}
				}
			}
		}
		Comparator<KhzbBean> comp = new Comparator<KhzbBean>() {
			@Override
			public int compare(KhzbBean o1, KhzbBean o2) {
				if (o1.getPx().intValue() > o2.getPx().intValue()) {
					return 1;
				}else {
					return 0;
				}
			}
		};
		
		for (KhzbBean _khzbBean : fathers) {
			List<KhzbBean> sons = _khzbBean.getSons();
			if (sons != null && sons.size() > 0) {
				Collections.sort(sons, comp);
			}
		}
		
		Collections.sort(fathers, comp);
	}
}
