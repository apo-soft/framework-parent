/**
 * 
 */
package cn.aposoft.framework.staticize;

import java.io.IOException;

/**
 * @author gao
 *
 */
public abstract class AbstractExecutor {
	private ModelProvider modelProvider;
	private TemplateProvider templateProvider;
	private Engine engine;
	private Exporter exporter;

	void setModelProvider(ModelProvider modelProvider) {
		this.modelProvider = modelProvider;
	}

	void setTemplateProvider(TemplateProvider templateProvider) {
		this.templateProvider = templateProvider;
	}

	void setEngine(Engine engine) {
		this.engine = engine;
	}

	void setExporter(Exporter exporter) {
		this.exporter = exporter;
	}

	public void execute() throws StaticizeException, IOException {
		String templateName = "index1";
		String[] modelNames = new String[] { "name1", "name2" };
		for (String modelName : modelNames) {
			executeInternal(templateName, modelName);
		}
	}

	private void executeInternal(String templateName, String modelName) throws StaticizeException, IOException {
		Model model = modelProvider.getModel(modelName);
		Template template = templateProvider.getTemplate(templateName);
		StaticPage page = engine.execute(template, model);
		exporter.export(page,"");
	}

}
