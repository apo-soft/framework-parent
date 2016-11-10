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
    protected ModelProvider modelProvider;
    protected TemplateProvider templateProvider;
    protected Engine engine;
    protected Exporter exporter;
    protected OutputProvider outputprovider;

    public AbstractExecutor(ModelProvider modelProvider, TemplateProvider templateProvider, Engine engine, Exporter exporter,
            OutputProvider outputprovider) {
        this.modelProvider = modelProvider;
        this.templateProvider = templateProvider;
        this.engine = engine;
        this.exporter = exporter;
        this.outputprovider = outputprovider;
    }

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

    public abstract void execute() throws StaticizeException;

    protected void executeInternal(String templateName, String modelName) throws StaticizeException, IOException {
        // Model model = modelProvider.getModel(modelName);
        // Template template = templateProvider.getTemplate(templateName);
        // StaticPage page = engine.execute(template, model);

    }

}
