package cn.aposoft.framework.staticize;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gao
 *
 */
public class Calculate {
	
	private static AtomicInteger modelId = new AtomicInteger(0); 
	private static AtomicInteger templateId = new AtomicInteger(0); 
	
	/**
	 * @author :  gao
	 * @date 2016年4月2日 下午1:40:08
	 * @return 
	 */
	public static int  getNextModelId(){
		return modelId.incrementAndGet();
	} 
	
	/**
	 * @author :  gao
	 * @date 2016年4月2日 下午1:40:32
	 * @return
	 */
	public static int getNextTemplateId(){
		return templateId.incrementAndGet();
	}
}
