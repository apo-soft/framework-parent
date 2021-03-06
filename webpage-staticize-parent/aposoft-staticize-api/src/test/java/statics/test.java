package statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.aposoft.framework.staticize.Model;
import cn.aposoft.framework.staticize.ModelProvider;
import cn.aposoft.framework.staticize.OutPutSources;
import cn.aposoft.framework.staticize.OutputProvider;
import cn.aposoft.framework.staticize.StaticPage;
import cn.aposoft.framework.staticize.StaticizeException;
import cn.aposoft.framework.staticize.Template;
import entry.Agent;
import entry.User;
import impl.FileExporter;
import impl.FileUrlOutPutSources;
import impl.FileUrlOutputProvider;
import impl.ModelImpl;
import impl.ModelProviderImpl;
import impl.TimeExcute;
import impl.VelocityTemplateImpl;
import impl.VelocityTemplateProvider;
import impl.velocityEngine;

public class test {
    HashMap<String, Object> umap = new HashMap<String, Object>();
    HashMap<String, Agent> amap = new HashMap<String, Agent>();
    HashMap<String, Model> modelMap = new HashMap<String, Model>();
    Template vtemplate = new VelocityTemplateImpl("user.vm");
    Template vtemplate1 = new VelocityTemplateImpl("user1.vm");
    Template vtemplate2 = new VelocityTemplateImpl("user2.vm");
    HashMap<String, Template> temMap = null;
    velocityEngine engine = new velocityEngine();
    FileExporter exporter = new FileExporter();
    StaticPage page = null;
    ModelProvider provider0 = null;
    VelocityTemplateProvider provider = null;

    public void clean() {
        umap = new HashMap<String, Object>();
        amap = new HashMap<String, Agent>();
        modelMap = new HashMap<String, Model>();
        page = null;
        provider0 = null;
        provider = null;
    }

    public void init() {
        User u0 = new User("100", "gao", 27);
        User u1 = new User("101", "shui", 28);
        User u2 = new User("100", "liu", 32);
        umap.put("u0", u0);
        umap.put("u1", u1);
        umap.put("u2", u2);
        umap.put("a0", new Agent("100", "gao", "it"));
        // Model umodel = new ModelImpl<User>("user", umap);
        Model vumodel = new ModelImpl<Object>("0", "user.vm", umap);
        Model vumodel1 = new ModelImpl<Object>("1", "user.vm1", umap);
        Model vumodel2 = new ModelImpl<Object>("2", "user.vm2", umap);
        // Model amodel = new ModelImpl<Agent>("agent",amap);
        // modelMap.put("agent", amodel);
        modelMap.put("0", vumodel);
        modelMap.put("1", vumodel1);
        modelMap.put("2", vumodel2);
        temMap = new HashMap<String, Template>();
        temMap.put("user.vm", vtemplate);
        temMap.put("user.vm1", vtemplate1);
        temMap.put("user.vm2", vtemplate2);

    }

    @Test
    public void modelTest() throws StaticizeException {
        init();
        List<Model> mlist = new ArrayList<Model>();
        provider0 = new ModelProviderImpl(modelMap);
        Model m = provider0.getModel("user");
        if (m != null)
            print(m);
        System.out.println("1////////////////");
        m = provider0.getModel("user", "velocity");
        if (m != null)
            print(m);
        System.out.println("2////////////////");
        m = provider0.getModel("agent", "velocity");
        if (m != null)
            print(m);
        System.out.println("3////////////////");
        m = provider0.getModel("user", vtemplate);
        if (m != null)
            print(m);
        mlist = provider0.getModels("velocity");
        if (m != null)
            print(mlist);
        System.out.println("4////////////////");
        clean();
    }

    @Test
    public void templateTest() throws StaticizeException {
        init();
        provider = new VelocityTemplateProvider(temMap);
        provider.addTemplate("user.vm", vtemplate);
        provider.addTemplate("user.vm1", vtemplate);
        provider.addTemplate("user.vm2", vtemplate);
        Template template = provider.getTemplate("user.vm");
        // Template template1 = provider.getTemplate("user1.vm");
        // Template template2 = provider.getTemplate("user2.vm");
        print(template);

    }

    @Test
    public void engineTest() throws StaticizeException {
        init();
        provider0 = new ModelProviderImpl(modelMap);

        provider = new VelocityTemplateProvider(temMap);
        provider.addTemplate("user.vm", vtemplate);
        Template template = provider.getTemplate("user.vm");
        Model umodel = provider0.getModel("1", vtemplate);
        page = engine.execute(template, umodel);
    }

    @Test
    public void FileExporterTest() throws StaticizeException, IOException {
        engineTest();
        Map<String, OutPutSources> outmap = new HashMap<String, OutPutSources>();
        OutPutSources outputSources = null;
        for (int i = 0; i < 3; i++) {
            outputSources = new FileUrlOutPutSources("D:\\static\\" + i + "\\page" + i + ".html");
            outmap.put(new Integer(i).toString(), outputSources);
        }
        OutputProvider outputprovider = new FileUrlOutputProvider(outmap);
        /*
         * for(Map.Entry<String, OutPutSources> m:outmap.entrySet()){
         * OutPutSources outPutSources; if(page != null &&
         * page.getModel().getId().equals(m.getKey())){ outPutSources =
         * m.getValue(); exporter.export(page,outPutSources); }ss }
         */
        OutPutSources outPutSources = null;
        Map<String, OutPutSources> map = outputprovider.getOutputSources();
        for (Map.Entry<String, Template> template : provider.getTemplates().entrySet()) {
            for (Model model : provider0.getModels(template.getKey())) {
                for (Map.Entry<String, OutPutSources> m : map.entrySet()) {
                    if (model.getId().equals(m.getKey())) {
                        StaticPage page = engine.execute(template.getValue(), model);
                        outPutSources = m.getValue();
                        exporter.export(page, outPutSources);
                    }
                }

            }
        }
    }

    @Test
    public void ExcutorTest() throws StaticizeException {
        engineTest();
        Map<String, OutPutSources> outmap = new HashMap<String, OutPutSources>();
        OutPutSources outputSources = null;
        for (int i = 0; i < 3; i++) {
            outputSources = new FileUrlOutPutSources("D:\\static\\" + i + "\\page" + i + ".html");
            outmap.put(new Integer(i).toString(), outputSources);
        }
        OutputProvider outputprovider = new FileUrlOutputProvider(outmap);

        TimeExcute timeExcute = new TimeExcute(provider0, provider, engine, exporter, outputprovider);

        timeExcute.execute();

    }

    private void print(Template template) {
        System.out.println(template.getId() + ":" + template.getType() + ":" + template.getContent());

    }

    public void print(List<Model> mlist) {
        for (Model m : mlist) {
            print(m);
        }
    }

    @SuppressWarnings("unchecked")
    public void print(Model m) {
        System.out.println(m.getId() + ":" + m.getTemplateId() + ":" + m.getContent());
        HashMap<String, Object> umap = (HashMap<String, Object>) m.getContent();
        Object temp;
        for (Map.Entry<String, Object> model : umap.entrySet()) {
            temp = model.getValue();
            if (temp != null) {
                if (temp instanceof User) {
                    User u = (User) temp;
                    System.out.println("user::" + u.getId() + ":" + u.getName() + ":" + u.getAge());
                    ;
                } else if (temp instanceof Agent) {
                    Agent a = (Agent) temp;
                    System.out.println("agent::" + a.getId() + ":" + a.getName() + ":" + a.getWorkfor());
                }
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, StaticizeException {
        new test().ExcutorTest();
    }

}
