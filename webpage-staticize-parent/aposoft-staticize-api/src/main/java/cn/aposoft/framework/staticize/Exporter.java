/**
 * 
 */
package cn.aposoft.framework.staticize;

/**
 * 实现对静态化结果的输出 Exporter的实现类,必须能够提供OutputStream作为byte流的输出目标,并提供关闭输出流的方法
 * 
 * @author LiuJian
 * @since 1.0.0.5
 */
public interface Exporter {
	/**
	 * 返回export的输出对象,当无法打卡输出流时,抛出IOException
	 * 
	 * @param page
	 *            静态化的待输出对象 {@link StaticPage}
	 * @return OutputStream,用于将静态化结果进行输出
	 * @throws StaticizeException
	 *             当输出静态化文件时,抛出此异常
	 */
	void export(StaticPage page) throws StaticizeException;;
}
