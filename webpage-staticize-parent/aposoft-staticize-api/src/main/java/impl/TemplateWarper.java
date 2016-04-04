package impl;

import cn.aposoft.framework.staticize.Template;

/**des: 未使用
 * @author gao
 *
 * @param <T>
 */
public class TemplateWarper <T> implements Template{
	private T template;
	public TemplateWarper(T template){
		this.template = template;
	}
	
	public T getTemplate(){
		return template;
	}
	@Override
	public String getId() {
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getContent() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
