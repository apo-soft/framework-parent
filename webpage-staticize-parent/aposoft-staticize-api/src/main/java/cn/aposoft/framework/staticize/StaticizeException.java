/**
 * 
 */
package cn.aposoft.framework.staticize;

/**
 * 静态化过程的默认异常
 * 
 * @author LiuJian
 * @since 1.0.0.5
 * @see Exception
 */
public class StaticizeException extends Exception {
	private static final long serialVersionUID = 4470967348077830438L;

	public StaticizeException() {
		super();
	}

	public StaticizeException(String message) {
		super(message);
	}

	public StaticizeException(String message, Throwable t) {
		super(message, t);
	}
}
