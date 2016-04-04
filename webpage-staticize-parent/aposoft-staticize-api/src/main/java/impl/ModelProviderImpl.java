package impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.aposoft.framework.staticize.Model;
import cn.aposoft.framework.staticize.ModelProvider;
import cn.aposoft.framework.staticize.StaticizeException;
import cn.aposoft.framework.staticize.Template;

public class ModelProviderImpl implements ModelProvider {
	private HashMap<String, Model> modelMap = new HashMap<String, Model>();

	public ModelProviderImpl(HashMap<String, Model> modelMap) {
		this.modelMap = modelMap;
	}

	@Override
	public Model getModel(String id) throws StaticizeException {
		return modelMap.get(id);
	}

	@Override
	public Model getModel(String id, String templateId) throws StaticizeException {
		Model model = modelMap.get(id);
		if(model != null && templateId.equals(model.getTemplateId())){
			return model;
		}
		return null;
	}

	@Override
	public Model getModel(String id, Template template) throws StaticizeException {
		if (template != null) {
			return getModel(id, template.getType());
		}
		return null;
	}

	@Override
	public List<Model> getModels(String templateId) throws StaticizeException {
		List<Model> mlist = new ArrayList<Model>();
		if (templateId != null && !templateId.isEmpty()) {
			for (Map.Entry<String, Model> model : modelMap.entrySet()) {
				if (templateId.equals(model.getValue().getTemplateId())) {
					mlist.add(model.getValue());
				}
			}
		}
		return mlist;
	}

	@Override
	public List<Model> getModels(Template template) throws StaticizeException {
		return this.getModels(template.getType());
	}

}
