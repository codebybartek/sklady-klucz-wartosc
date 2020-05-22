
import java.io.Serializable;

public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fullname;
	private int birthyear;
	private long PESEL;
	private String address;


	public Client(String fullname, int birthyear, long PESEL, String address) {
		this.fullname = fullname;
		this.birthyear = birthyear;
		this.PESEL = PESEL;
		this.address = address;
	}

	public String getFullname() { return fullname; }
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getBirthyear() {
		return birthyear;
	}
	
	public void setBirthyear(int birthyear) {
		this.birthyear = birthyear;
	}

	public long getPESEL() { return PESEL; }

	public void setPESEL(long PESEL) { this.PESEL = PESEL; }

	public String getAddress() { return address; }

	public void setAddress(String address) { this.address = address; }

	
	@Override
	public String toString(){
		return "Client [Fullname: " + fullname + ", Born in: " + birthyear + ", PESEL: " + PESEL + ", Address: "  + address + "]";
	}
}
