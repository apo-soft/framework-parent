package impl;

import java.util.HashMap;
import java.util.Map;

import cn.aposoft.framework.staticize.Model;

/**
 * @author gao
 *
 * @param <T>
 */
public class ModelImpl<T> implements Model {

	private static final long serialVersionUID = 1L;
	Map<String, T> entryMap = new HashMap<String, T>();
	
	/**
	 * 模板名称
	 */
	private String templateId = "";
	
	/**
	 * model id 
	 */
	private String id; 

	public ModelImpl(String id, Map<String, T> entryMap) {
		this.id = id;
		this.entryMap = entryMap;
	}

	public ModelImpl(String id, String templateId, Map<String, T> entryMap) {
		this(id, entryMap);
		this.templateId = templateId;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getTemplateId() {
		return templateId;
	}

	@Override
	public Map<String, T> getContent() {
		return entryMap;
	}

}
