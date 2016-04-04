package impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import cn.aposoft.framework.staticize.Exporter;
import cn.aposoft.framework.staticize.OutPutSources;
import cn.aposoft.framework.staticize.StaticPage;
import cn.aposoft.framework.staticize.StaticizeException;

/**
 * des: 静态文件输出到文件
 * @author gao
 * 
 */
public class FileExporter implements Exporter {

	/* (non-Javadoc)
	 * @see cn.aposoft.framework.staticize.Exporter#export(cn.aposoft.framework.staticize.StaticPage, java.lang.String)
	 */
	@Override
	public void export(StaticPage page, OutPutSources url) throws StaticizeException, IOException {
		File dir = new File((String)url.getSources());
		if (!dir.exists()) {
			new File(dir.getParent()).mkdirs();
		}
		dir.createNewFile();
		FileUtils.writeStringToFile(dir, page.getContent());
		System.out.println(page.getContent());
	}

}
