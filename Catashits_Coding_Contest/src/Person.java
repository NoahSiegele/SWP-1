
public class Person {
	private int gr��e;
	private String name;
	public Person(int gr, String n) {
		gr��e=gr;
		name=n;
	}
	public Person() {}
	
	public int getGr��e() {
		return gr��e;
	}
	public void setGr��e(int gr��e) {
		this.gr��e = gr��e;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void ausgabe() {
		System.out.printf("%s, %d",name,gr��e);
	}
	
	public static void main(String[] args) {
		Person p=null;
		System.out.println(p);
		p.setGr��e(190);
		p.ausgabe();
		
	}

}
