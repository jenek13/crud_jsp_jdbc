package javaguides.usermanagement.model;

/**
 * User.java
 * This is a model class represents a User entity
 * @author Ramesh Fadatare
 *
 */
public class User {
	protected int id;
	protected String name;
	protected int age;

	public User() {
	}

	public User(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public User(String name, int age) {
		this.name = name;//тут было пусто
		this.age = age;//тут было пусто
	}



	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
