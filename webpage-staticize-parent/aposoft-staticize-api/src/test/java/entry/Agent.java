package entry;


/**
 * @author gao
 *　
 */
public class Agent {
	private String id;
	private String name;
	private String workfor;
	public Agent(String id, String name, String workfor) {
		this.id=id;
		this.name=name;
		this.workfor=workfor;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWorkfor() {
		return workfor;
	}
	public void setWorkfor(String workfor) {
		this.workfor = workfor;
	}
}
