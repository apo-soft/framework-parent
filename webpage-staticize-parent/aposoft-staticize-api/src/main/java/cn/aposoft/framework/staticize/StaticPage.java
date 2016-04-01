/**
 * 
 */
package cn.aposoft.framework.staticize;

import java.util.Date;

/**
 * @author LiuJian
 *
 */
public interface StaticPage {

	Model getModel();

	/**
	 * 
	 * @return
	 */
	Template getTemplate();

	/**
	 * 静态化结果的过期时间
	 * 
	 * @return 过期时间,当无确定过期时间的情况下返回null;
	 */
	Date expireDate();

	/**
	 * 静态化的最终结果内容
	 * 
	 * @return
	 */
	String getContent();
}
