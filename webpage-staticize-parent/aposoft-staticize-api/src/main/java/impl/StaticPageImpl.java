package impl;

import java.util.Date;

import cn.aposoft.framework.staticize.Model;
import cn.aposoft.framework.staticize.StaticPage;
import cn.aposoft.framework.staticize.Template;

public class StaticPageImpl implements StaticPage {

	private Model model;
	private Template template;
	private String content;
	private Date expireDate ;

	public StaticPageImpl(Model model, Template template, String content, Date expireDate) {
		this.content = content;
		this.expireDate = expireDate;
		this.model = model;
		this.template = template;
	}
	

	@Override
	public Model getModel() {
		return model;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	@Override
	public Template getTemplate() {
		return template;
	}

	@Override
	public Date expireDate() {
		return expireDate;
	}

	@Override
	public String getContent() {
		return content;
	}

}
