/**
 * 
 */
package cn.aposoft.framework.staticize;

import java.io.Serializable;

/**
 * @author LiuJian
 *
 */
public interface Model extends Serializable {

	/**
	 * 用于定义数据模型的唯一业务标识
	 * 
	 * @return 模板的唯一ID
	 */
	String getId();

	/**
	 * 数据模型兼容的模板ID
	 * 
	 * @return 模板ID
	 */
	String getTemplateId();

	/**
	 * 数据模型的业务内容
	 * 
	 * @return 返回模板的内容对象
	 */
	Object getContent();

}
