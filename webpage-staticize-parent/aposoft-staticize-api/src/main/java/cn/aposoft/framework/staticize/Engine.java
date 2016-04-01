/**
 * 
 */
package cn.aposoft.framework.staticize;

/**
 * 用于合并模板和数据Model的引擎 实现将模板和Model合并执行成输出数组
 * 本实例考虑生成的静态化页面实际上都是足够小的,一般不会大于1GB,因此并未使用Writer进行流式输出.
 * 
 * @author LiuJian
 * @since 1.0.0.5
 */
public interface Engine {

	/**
	 * 完成由模板和数据模型合并的过程
	 * 
	 * @param template
	 *            页面模板
	 * @param model
	 *            数据对象
	 * @return 生成的最终结果
	 */
	StaticPage execute(Template template, Model model);

}
