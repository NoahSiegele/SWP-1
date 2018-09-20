import java.util.ArrayList;

public class First_Test {

	public static void sort(ArrayList<Integer> l) {
		int zw = 0;

		for (int i = 0; i < l.size(); i++) {
			for (int j = i + 1; j < l.size(); j++) {
				if (l.get(j) < l.get(i)) {
					zw = l.get(i);
					l.set(i, l.get(j));
					l.set(j, zw);
				}
			}
		}
		System.out.println(l.toString());
	}

	public static void sort2(ArrayList<Integer> l) {
		boolean fertig=false;
		ArrayList<Integer> l2= new ArrayList<>();
		int zaehler=1;
		while(!fertig) {
			if(l.contains(zaehler)) {
				l2.add(zaehler);
			}
			zaehler++;
			if(l.size()==l2.size()) {
				fertig=true;
			}
		}
		System.out.println(l2.toString());
	}
	public static void sort3(ArrayList<Integer> l) {
		ArrayList<Integer> l2=new ArrayList<>();
		int smallest;
		while(l.size()!=0) {
			smallest=l.get(0);
			for(int i=0;i<l.size();i++) {
				if(l.get(i)<smallest) {
					smallest=l.get(i);
				}
			}
			l.remove(l.indexOf(smallest));
			l2.add(smallest);
		}
		System.out.println(l2.toString());
	}
	public static void monkeySort(ArrayList<Integer> l) {
		int biggest=0;
		int counter=0;
		ArrayList<Integer> l2= new ArrayList<>();
		for(int i=0;i<l.size();i++) {
			if(l.get(i)>biggest) {
				biggest=l.get(i);
			}
		}
		boolean fertig= false;
		while(!fertig) {
			
			int random;
			while(l.size()!=0) {
				random= (int)(Math.random()*biggest+1);
				if(l.contains(random)) {
					l2.add(random);
					l.remove(l.indexOf(random));
				}
				
			}	
			for(int i=0;i<l2.size();i++) {
				l.add(l2.get(i));
			}
			counter++;
			l2.clear();
			fertig=true;
			for(int i=1;i<l.size();i++) {
				if(l.get(i-1)>l.get(i)) {
					fertig=false;
				}
			}
				
		}
		System.out.println(counter);
		System.out.println(l.toString());
	}
	public static ArrayList<Integer> getRandomList(int length){
		ArrayList<Integer> l=new ArrayList<>();
		for(int i=0;i<length;i++) {
			l.add((int)(Math.random()*100+1));
		}
		return l;
	}
	public static void main(String[] args) {
		ArrayList<Integer> l = new ArrayList<>();
		l=getRandomList(5);
		monkeySort(l);
		for (int i = 1; i <= 100; i++) {
			// System.out.print(i+" : ");
			if (i % 3 == 0) {
				// System.out.print("fizz");
			}
			if (i % 4 == 0) {
				// System.out.print("buzz");
			}

			if (i % 3 != 0 && i % 4 != 0) {
				// System.out.print(i);
			}
			// System.out.println();
		}

	}

}
