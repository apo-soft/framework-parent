package impl;


import java.util.HashMap;

import cn.aposoft.framework.staticize.Template;

public class VelocityTemplateImpl implements Template{
	HashMap<String , Template> map = new HashMap<String , Template>();
	private String type ="velocity";
	private String id;
	public VelocityTemplateImpl(String id){
		this.id = id; 
		map.put(id, this);
	}
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public HashMap<String, Template> getContent() {
		return map;
	}
	
}
