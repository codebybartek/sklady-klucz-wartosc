import java.io.Serializable;

public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private int birthyear;

	public Student(String name, int birthyear) {
		this.name = name;
		this.birthyear = birthyear;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getBirthyear() {
		return birthyear;
	}
	
	public void setBirthyear(int birthyear) {
		this.birthyear = birthyear;
	}
	
	@Override
	public String toString(){
		return "Student " + name + " born in " + birthyear;
	}
}
