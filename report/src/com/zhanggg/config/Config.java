package com.zhanggg.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.Sqlite3Dialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.template.Engine;
import com.zhanggg.business.controller.DictController;
import com.zhanggg.business.controller.HelloController;
import com.zhanggg.business.controller.KhzbController;
import com.zhanggg.business.controller.LjController;
import com.zhanggg.business.controller.ZlmlController;
import com.zhanggg.business.model._MappingKit;

public class Config extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		PropKit.use("config.properties");
		me.setDevMode(PropKit.getBoolean("devMode", false));
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/", HelloController.class);
		me.add("/zlml",ZlmlController.class);
		me.add("/khzb",KhzbController.class);//考核指标
		me.add("/lj",LjController.class);
		me.add("/dict",DictController.class);
		
	}

	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub

	}
	
	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
	}

	@Override
	public void configPlugin(Plugins me) {
		
		me.add(new EhCachePlugin()); 
		
		DruidPlugin druidPlugin = createDruidPlugin();
		druidPlugin.setDriverClass("org.sqlite.JDBC"); // 指定驱动程序
		me.add(druidPlugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		arp.setDialect(new Sqlite3Dialect()); // 指定 Dialect
		
		_MappingKit.mapping(arp);
		me.add(arp); 

	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub

	}

}
