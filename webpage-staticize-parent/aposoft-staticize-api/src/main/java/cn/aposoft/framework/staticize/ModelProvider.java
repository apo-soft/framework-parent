/**
 * 
 */
package cn.aposoft.framework.staticize;

import java.util.List;

/**
 * 数据模型提供接口
 * 
 * @author LiuJian
 * @since 1.0.0.5
 */
public interface ModelProvider {
	/**
	 * 根据模型id获取数据模型对象
	 * 
	 * @param id
	 *            模型id
	 * 
	 * @return template 对应的数据模型对象
	 * 
	 * @throws StaticizeException
	 *             当加载Model时发生内部异常时,抛出此异常
	 */
	Model getModel(String id) throws StaticizeException;

	/**
	 * 
	 * @param id
	 *            模型id
	 * @param templateId
	 *            模板id
	 * @return template 对应的数据模型对象
	 * 
	 * @throws StaticizeException
	 *             当加载Model时发生内部异常时,抛出此异常
	 */
	Model getModel(String id, String templateId) throws StaticizeException;

	/**
	 * 
	 * @param id
	 *            模型id
	 * @param template
	 *            模板对象
	 * @return template 对应的数据模型对象
	 * 
	 * @throws StaticizeException
	 *             当加载Model时发生内部异常时,抛出此异常
	 */
	Model getModel(String id, Template template) throws StaticizeException;

	/**
	 * 
	 * 获取指定模板对应的全部数据模型列表
	 * 
	 * @param templateId
	 *            模板id
	 * @return template 对应的数据模型对象
	 * 
	 * @throws StaticizeException
	 *             当加载Model时发生内部异常时,抛出此异常
	 */
	List<Model> getModels(String templateId) throws StaticizeException;

	/**
	 * 
	 * 获取指定模板对应的全部数据模型
	 * 
	 * @param template
	 *            模板id
	 * @return template 对应的数据模型对象
	 * 
	 * @throws StaticizeException
	 *             当加载Model时发生内部异常时,抛出此异常
	 */
	List<Model> getModels(Template template) throws StaticizeException;
}
