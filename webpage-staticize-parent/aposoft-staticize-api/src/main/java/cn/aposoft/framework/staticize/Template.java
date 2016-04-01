/**
 * 
 */
package cn.aposoft.framework.staticize;

/**
 * Template 必须和Engine配合使用,指定的Template实现,用于指定的Engine来进行静态化数据生成
 * 
 * @author LiuJian
 *
 */
public interface Template {

	/**
	 * 用于定义模板的唯一标识，同一个业务的模板id一样
	 * 
	 * @return 模板的唯一ID
	 */
	String getId();

	/**
	 * 模板的实现类型：如velocity，free marker等
	 * 
	 * @return Type类型，和EngineType应当保持一致
	 */
	String getType();

	/**
	 * 模板的内容
	 * 
	 * @return 返回模板的内容对象
	 */
	Object getContent();
}
