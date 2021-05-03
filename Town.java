
public class Town implements Comparable<Town>{

	private String name;

	public Town (String name) {
		this.name = name;
	}
	
	public Town(Town templateTown) {
		this.name = templateTown.name;
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public int compareTo(Town o) {
		if(o.name.contentEquals(this.name))
			return 0;
		return -1;
	}
	
	public String toString() {
		return name;
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public boolean equals(Object obj) {
		Town t = (Town) obj;
		if(t.name.equals(this.name))
			return true;
		return false;
	}
}
