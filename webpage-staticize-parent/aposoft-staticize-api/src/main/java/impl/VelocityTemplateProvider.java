package impl;

import java.util.HashMap;
import java.util.Map;

import cn.aposoft.framework.staticize.StaticizeException;
import cn.aposoft.framework.staticize.Template;
import cn.aposoft.framework.staticize.TemplateProvider;

public  class VelocityTemplateProvider implements TemplateProvider{
	Map<String,Template> templateMap = new HashMap<String,Template>();
	@Override
	public  String getType(){
		return "velocity";
	}

	@Override
	public Template getTemplate(String id) throws StaticizeException {
		return templateMap.get(id);
	}
	
	public void  addTemplate(String id ,Template template){
		templateMap.put(id,template);
	}
	
}
