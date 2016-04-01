/**
 * 
 */
package cn.aposoft.framework.staticize;

/**
 * 模板提供接口
 * 
 * @author LiuJian
 * @since 1.0.0.5
 */
public interface TemplateProvider {
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

}
