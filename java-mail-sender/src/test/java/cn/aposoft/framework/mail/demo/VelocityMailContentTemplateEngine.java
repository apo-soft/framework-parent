/**
 * 
 */
package cn.aposoft.framework.mail.demo;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * @author LiuJian
 *
 */
public class VelocityMailContentTemplateEngine {

	public String getContent(String templateFile, Object contentBean) {

		/*
		 * setup
		 */
		Velocity.init("velocity.properties");

		/*
		 * Make a context object and populate with the data. This is where the
		 * Velocity engine gets the data to resolve the references (ex. $list)
		 * in the template
		 */
		VelocityContext context = new VelocityContext();
		context.put("bean", contentBean);
		Template template = null;
		try {
			template = Velocity.getTemplate(templateFile);
		} catch (ResourceNotFoundException rnfe) {
			System.out.println("Example : error : cannot find template " + templateFile);
		} catch (ParseErrorException pee) {
			System.out.println("Example : Syntax error in template " + templateFile + ":" + pee);
		}

		/*
		 * Now have the template engine process your template using the data
		 * placed into the context. Think of it as a 'merge' of the template and
		 * the data to produce the output stream.
		 */
		StringWriter writer = new StringWriter();

		if (template != null)
			template.merge(context, writer);
		writer.flush();
		String result = writer.toString();
		try {
			writer.close();
		} catch (IOException e) {
		}
		return result;
	}
}
