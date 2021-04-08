package test;

public class Person {
	private String name; 
	private Integer age; 
	
	public Person(String name, Integer age) {
		this.name = name; 
		this.age = age; 
	}

	@Override 
	public String toString() {
		return this.name + ", " + this.age; 
	}
	
	@Override
	public boolean equals(Object other) {
		return this.name == ((Person)other).name && this.age == ((Person)other).age; 
	}

	@Override 
	public int hashCode() {
		return 1; 
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}


