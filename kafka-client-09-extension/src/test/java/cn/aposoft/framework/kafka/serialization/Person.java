/**
 * 
 */
package cn.aposoft.framework.kafka.serialization;

import java.io.Serializable;

/**
 * @author Jann Liu
 *
 */
public class Person implements Serializable {
	private static final long serialVersionUID = -8412215730895795602L;
	private String name;
	private int age;
	private String sex;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return String.format("[%s 今年 %d 岁了,他的性别是 %s.]", name, age, sex);
	}

}
