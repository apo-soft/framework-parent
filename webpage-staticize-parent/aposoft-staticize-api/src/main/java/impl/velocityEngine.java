package impl;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import cn.aposoft.framework.staticize.Engine;
import cn.aposoft.framework.staticize.Model;
import cn.aposoft.framework.staticize.StaticPage;
import cn.aposoft.framework.staticize.StaticizeException;
import cn.aposoft.framework.staticize.Template;

public class velocityEngine implements Engine {

	@Override
	public String getType() {
		return "velocity";
	}

	@Override
	public StaticPage execute(Template template, Model model) throws StaticizeException {
		String pageContent = getPageString(template, model);
		System.out.println(pageContent);
		StaticPage staticpage = new StaticPageImpl(model, template, pageContent, new Date());
		return staticpage;

	}

	public String getPageString(Template template, Model model) {
		Velocity.init("velocity.properties");
		VelocityContext context = new VelocityContext();
		putbeans(context,model);
		org.apache.velocity.Template velocitytemplate = null;
		try {
			velocitytemplate = Velocity.getTemplate(template.getId());
		} catch (ResourceNotFoundException rnfe) {
			System.out.println("Example : error : cannot find template " + template.getId());
		} catch (ParseErrorException pee) {
			System.out.println("Example : Syntax error in template " + template.getId() + ":" + pee);
		}
		StringWriter writer = new StringWriter();
		if (velocitytemplate != null)
			velocitytemplate.merge(context, writer);
		writer.flush();
		String result = writer.toString();
		try {
			writer.close();
		} catch (IOException e) {
		}
		return result;
	}
	
	/**
	 * @author :  gao
	 * @date 2016年4月3日 下午2:09:33
	 * @param context
	 * @param model
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private <T> void putbeans(VelocityContext context, Model model) {
		if(model != null){
			ModelImpl mimpl = (ModelImpl) model;
			Map<String,T> entryMap = mimpl.getContent();
			for (Map.Entry<String, T> entry : entryMap.entrySet()) {
				context.put(entry.getKey(), entry.getValue());
			}
			
		}
		
	}

}
