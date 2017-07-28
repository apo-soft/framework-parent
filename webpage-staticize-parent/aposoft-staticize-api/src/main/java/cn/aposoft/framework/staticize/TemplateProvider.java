/**
 * 
 */
package cn.aposoft.framework.staticize;

import java.util.Map;

/**
 * 模板提供接口
 * 
 * @author LiuJian
 * @since 1.0.0.5
 */
public interface TemplateProvider {

	/**
	 * 模板的类型，于Engine支持的模板类型一致
	 * @return 模板类型
	 */
	String getType();

	/**
	 * 加载模板方法
	 * 
	 * @param id
	 *            模板ID
	 * @return 需要加载的模板
	 * @throws StaticizeException
	 *             当加载template失败时时,抛出此异常
	 */
	Template getTemplate(String id) throws StaticizeException;
	
	Map<String, Template> getTemplates() throws StaticizeException;
	
}
