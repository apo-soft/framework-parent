package impl;


import org.apache.velocity.app.Velocity;

import cn.aposoft.framework.staticize.Calculate;
import cn.aposoft.framework.staticize.Template;

public class VelocityTemplateImpl implements Template{
	private String type ="velocity";
	private String id;
	public VelocityTemplateImpl(String id){
		this.id = id; 
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
	public String getContent() {
		return id;
	}
	
}
