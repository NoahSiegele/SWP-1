package Weedhart.jpa_example;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void addKunde(EntityManager manager, String titelv, String vorname, String nachname, String titeln) {
		Kunde k=new Kunde(titelv, vorname,nachname, titeln);
		manager.getTransaction().begin();
		manager.persist(k);
		manager.getTransaction().commit();
	}
	public static void addAdresse(EntityManager manager, String stadt, String strasse, int plz, String hnr) {
		Adresse a= new Adresse(stadt, strasse, plz, hnr);
		manager.getTransaction().begin();
		manager.persist(a);
		manager.getTransaction().commit();
	}
	public static void addBestellung(EntityManager manager, int kundeid, int addressrech, int addresslief) {
		Bestellung b= new Bestellung(manager.find(Kunde.class, (long)kundeid), manager.find(Adresse.class,(long)addressrech), manager.find(Adresse.class, (long)addresslief));
		manager.getTransaction().begin();
		manager.persist(b);
		manager.getTransaction().commit();
	}
	public static void addArtikel(EntityManager manager, String name, double preis) {
		Artikel a= new Artikel(name,preis);
		manager.getTransaction().begin();
		manager.persist(a);
		manager.getTransaction().commit();
	}
	public static void addBestArt(EntityManager manager, int bestid, int artid, int menge) {
		Bestellung_Artikel ba=new Bestellung_Artikel(manager.find(Bestellung.class, (long)bestid),manager.find(Artikel.class,(long)artid), menge);
		manager.getTransaction().begin();
		manager.persist(ba);
		manager.getTransaction().commit();
	}
	@SuppressWarnings("unchecked")
	public static void deleteKunde(EntityManager manager, int id) {
		Query query = manager.createNativeQuery("Select * FROM Bestellung;",Bestellung.class);
		List<Bestellung> result=query.getResultList();
		for(Bestellung b : result) {
			if(b.getKunde().getId()==(long)id) {
				query = manager.createNativeQuery("Select * FROM Bestellung_Artikel;",Bestellung_Artikel.class);
				List<Bestellung_Artikel> resultba=query.getResultList();
				for(Bestellung_Artikel ba : resultba) {
					if(ba.getBestellung().getId()==b.getId()) {
						manager.getTransaction().begin();
						manager.remove(ba);
						manager.getTransaction().commit();
					}
				}
				manager.getTransaction().begin();
				manager.remove(b);
				manager.getTransaction().commit();
			}
		}
		Kunde k=manager.find(Kunde.class, (long)id);
		manager.getTransaction().begin();
		manager.remove(k);
		manager.getTransaction().commit();
	}
	@SuppressWarnings("unchecked")
	public static void deleteAdresse(EntityManager manager, int id) {
		Query query = manager.createNativeQuery("Select * FROM Bestellung;",Bestellung.class);
		List<Bestellung> result=query.getResultList();
		for(Bestellung b : result) {
			if(b.getAddresslief().getId()==(long)id || b.getAddressrech().getId()==(long)id) {
				query = manager.createNativeQuery("Select * FROM Bestellung_Artikel;",Bestellung_Artikel.class);
				List<Bestellung_Artikel> resultba=query.getResultList();
				for(Bestellung_Artikel ba : resultba) {
					if(ba.getBestellung().getId()==b.getId()) {
						manager.getTransaction().begin();
						manager.remove(ba);
						manager.getTransaction().commit();
					}
				}
				manager.getTransaction().begin();
				manager.remove(b);
				manager.getTransaction().commit();
			}
			
		}
		Adresse a=manager.find(Adresse.class, (long)id);
		manager.getTransaction().begin();
		manager.remove(a);
		manager.getTransaction().commit();
	}
	@SuppressWarnings("unchecked")
	public static void deleteBestellung(EntityManager manager, int id) {
		Query query = manager.createNativeQuery("Select * FROM Bestellung_Artikel;",Bestellung_Artikel.class);
		List<Bestellung_Artikel> result=query.getResultList();
		for(Bestellung_Artikel ba : result) {
			if(ba.getBestellung().getId()==(long)id) {
				manager.getTransaction().begin();
				manager.remove(ba);
				manager.getTransaction().commit();
			}
		}
		Bestellung b=manager.find(Bestellung.class, (long)id);
		manager.getTransaction().begin();
		manager.remove(b);
		manager.getTransaction().commit();
	}
	@SuppressWarnings("unchecked")
	public static void deleteArtikel(EntityManager manager, int id) {
		Query query = manager.createNativeQuery("Select * FROM Bestellung_Artikel;",Bestellung_Artikel.class);
		List<Bestellung_Artikel> result=query.getResultList();
		for(Bestellung_Artikel ba : result) {
			if(ba.getArtikel().getId()==(long)id) {
				manager.getTransaction().begin();
				manager.remove(ba);
				manager.getTransaction().commit();
			}
		}
		Artikel a=manager.find(Artikel.class, (long)id);
		manager.getTransaction().begin();
		manager.remove(a);
		manager.getTransaction().commit();
	}
	@SuppressWarnings("unchecked")
	public static void deleteBestArt(EntityManager manager, int bestid, int artid) {
		Query query = manager.createNativeQuery("Select * FROM Bestellung_Artikel;",Bestellung_Artikel.class);
		List<Bestellung_Artikel> result=query.getResultList();
		for(Bestellung_Artikel ba : result) {
			if(ba.getBestellung().getId()==(long)bestid && ba.getArtikel().getId()==(long)artid) {
				manager.getTransaction().begin();
				manager.remove(ba);
				manager.getTransaction().commit();
			}
		}
	}
	public static void selectKunde(EntityManager manager, int id) {
		Kunde k=manager.find(Kunde.class, (long)id);
		System.out.println(k.toString());
	}
	@SuppressWarnings("unchecked")
	public static void selectKundeAll(EntityManager manager) {
		Query query = manager.createNativeQuery("Select * FROM Kunde;",Kunde.class);
		List<Kunde> result=query.getResultList();
		for(Kunde k : result) {
			System.out.println(k.toString());
		}
	}
	public static void selectAdresse(EntityManager manager, int id) {
		Adresse a=manager.find(Adresse.class, (long)id);
		System.out.println(a.toString());
	}
	@SuppressWarnings("unchecked")
	public static void selectAdresseAll(EntityManager manager) {
		Query query = manager.createNativeQuery("Select * FROM Adresse;",Adresse.class);
		List<Adresse> result=query.getResultList();
		for(Adresse a : result) {
			System.out.println(a.toString());
		}
	}
	public static void selectBestellung(EntityManager manager, int id) {
		Bestellung b=manager.find(Bestellung.class, (long)id);
		System.out.println(b.toString());
	}
	@SuppressWarnings("unchecked")
	public static void selectBestellungAll(EntityManager manager) {
		Query query = manager.createNativeQuery("Select * FROM Bestellung;",Bestellung.class);
		List<Bestellung> result=query.getResultList();
		for(Bestellung b : result) {
			System.out.println(b.toString());
		}
	}
	public static void selectArtikel(EntityManager manager, int id) {
		Artikel a=manager.find(Artikel.class, (long)id);
		System.out.println(a.toString());
	}
	@SuppressWarnings("unchecked")
	public static void selectArtikelAll(EntityManager manager) {
		Query query = manager.createNativeQuery("Select * FROM Artikel;",Artikel.class);
		List<Artikel> result=query.getResultList();
		for(Artikel a : result) {
			System.out.println(a.toString());
		}
	}
	public static void selectBestArt(EntityManager manager, int bestid, int artid) {
		Query query = manager.createNativeQuery("Select * FROM Bestellung_Artikel;",Bestellung_Artikel.class);
		@SuppressWarnings("unchecked")
		List<Bestellung_Artikel> result=query.getResultList();
		for(Bestellung_Artikel ba : result) {
			if(ba.getBestellung().getId()==(long)bestid && ba.getArtikel().getId()==(long)artid) {
				System.out.println(ba.toString());
			}
		}
	}
	@SuppressWarnings("unchecked")
	public static void selectBestellungArtikelAll(EntityManager manager) {
		Query query = manager.createNativeQuery("Select * FROM Bestellung_Artikel;",Bestellung_Artikel.class);
		List<Bestellung_Artikel> result=query.getResultList();
		for(Bestellung_Artikel ba : result) {
			System.out.println(ba.toString());
		}
	}
	public static Kunde selectKundeK(EntityManager manager, int id) {
		return manager.find(Kunde.class, (long)id);
	}
	public static void updateKunde(EntityManager manager, int id, String titelv, String vorname, String nachname, String titeln) {
		Kunde k=manager.find(Kunde.class, (long)id);
		manager.getTransaction().begin();
		k.setTitelv(titelv);
		k.setVorname(vorname);
		k.setNachname(nachname);
		k.setTiteln(titeln);
		manager.getTransaction().commit();
	}
	public static Bestellung selectBestellungB(EntityManager manager, int id) {
		return manager.find(Bestellung.class, (long)id);
	}
	public static void updateBestellung(EntityManager manager,int id, int kid, int arid, int alid) {
		Bestellung b=manager.find(Bestellung.class, (long)id);
		manager.getTransaction().begin();
		b.setKunde(manager.find(Kunde.class, (long)kid));
		b.setAddressrech(manager.find(Adresse.class, (long)arid));
		b.setAddresslief(manager.find(Adresse.class, (long)alid));
	}
	public static Adresse selectAdresseA(EntityManager manager, int id) {
		return manager.find(Adresse.class, (long)id);
	}
	public static void updateAdresse(EntityManager manager, int id, String stadt, String strasse, int plz, String hnr) {
		Adresse a=manager.find(Adresse.class, (long)id);
		manager.getTransaction().begin();
		a.setStadt(stadt);
		a.setStrasse(strasse);
		a.setPlz(plz);
		a.setHnr(hnr);
		manager.getTransaction().commit();
	}
	public static Bestellung_Artikel selectBestArtBA(EntityManager manager, int bestid, int artid) {
		Query query = manager.createNativeQuery("Select * FROM Bestellung_Artikel;",Bestellung_Artikel.class);
		@SuppressWarnings("unchecked")
		List<Bestellung_Artikel> result=query.getResultList();
		for(Bestellung_Artikel ba : result) {
			if(ba.getBestellung().getId()==(long)bestid && ba.getArtikel().getId()==(long)artid) {
				return ba;
			}
		}
		return null;
	}
	public static void updateBestArt(EntityManager manager, int bestid, int artid, int menge) {
		Bestellung_Artikel ba=selectBestArtBA(manager,bestid,artid);
		manager.getTransaction().begin();
		ba.setMenge(menge);
		manager.getTransaction().commit();
	}
	public static Artikel selectArtikelA(EntityManager manager, int id) {
		return manager.find(Artikel.class, (long)id);
	}
	public static void updateArtikel(EntityManager manager,  int id, String name, double preis) {
		Artikel a=manager.find(Artikel.class, (long)id);
		manager.getTransaction().begin();
		a.setName(name);
		a.setPreis(preis);
		manager.getTransaction().commit();
	}
    public static void main( String[] args )
    {
    	 EntityManagerFactory f=Persistence.createEntityManagerFactory("Blog");
         EntityManager manager=f.createEntityManager();
       /*create
        Person p=new Person(25,"Simon","Kuttner");
        manager.getTransaction().begin();
        manager.persist(p);
        manager.getTransaction().commit();
        
        update
        Person p=manager.find(Person.class, 1L);
        System.out.println(p.toString());
        manager.getTransaction().begin();
        p.setfirstname("Batschkahn");
        manager.getTransaction().commit();
        
        remove
        Person p=manager.find(Person.class, 1L);
        manager.getTransaction().begin();
        manager.remove(p);
        manager.getTransaction().commit();
        */
         //Kunde p=manager.find(Kunde.class, 1L);
         //System.out.println(p);
         /*
         Hobby h=new Hobby("Fußball",4,12.40);
         p.setHobby(h);
         System.out.println("New Hobby-before Persist");
         System.out.println(h);
         manager.getTransaction().begin();
         manager.persist(h);
         manager.getTransaction().commit();
         System.out.println("New Hobby-after persist");
         System.out.println(h);
         */
         System.out.println("*");
         System.out.println("*");
         System.out.println("*");
         System.out.println("*");
         System.out.println("*");
         System.out.println("*");
         Scanner s = new Scanner(System.in);
 		System.out.println(
 				"Hello, when asked for a Table, type in Kunde, Bestellung, Adresse, BestellungArtikel or Artikel");
 		System.out.println(
 				"May i remind you to keep the database scheme by your side, in order to insert the correct attributes for each table.");
 		boolean finished = false;
 		while (!finished) {
 			System.out.println("Type L for Table-List, R for read, C for create, U for update or D for delete:");
 			String input = s.nextLine();
 			if (input.equals("R")) {
 				System.out.println("Type in the table-name and ID (2IDs for BestArt) of the entry seperated by one space:");
 				input = s.nextLine();
 				String in[] = input.split(" ");
 				if (in[0].equals("Kunde")) {
 					try {
 						selectKunde(manager, Integer.parseInt((in[1])));
 					} catch (Exception e) {
 						System.out.println("Looks like this Kunde doesn't exist or you typed shit!");
 					}
 				} else if (in[0].equals("Bestellung")) {
 					try {
 						selectBestellung(manager, Integer.parseInt((in[1])));
 					} catch (Exception e) {
 						System.out.println("Looks like this Bestellung doesn't exist or you typed shit!");
 					}
 				} else if (in[0].equals("Adresse")) {
 					try {
 						selectAdresse(manager, Integer.parseInt((in[1])));
 					} catch (Exception e) {
 						System.out.println("Looks like this Adresse doesn't exist or you typed shit!");
 					}
 				} else if (in[0].equals("BestellungArtikel")) {
 					try {
 						selectBestArt(manager, Integer.parseInt((in[1])), Integer.parseInt(in[2]));
 					} catch (Exception e) {
 						System.out.println("Looks like this BestellungArtikel doesn't exist or you typed shit!");
 						e.printStackTrace();
 					}
 				} else if (in[0].equals("Artikel")) {
 					try {
 						selectArtikel(manager, Integer.parseInt((in[1])));
 					} catch (Exception e) {
 						System.out.println("Looks like this Artikel doesn't exist or you typed shit!");
 					}
 				} else {
 					System.out.println("Looks like you failed, try again!");
 				}
 			} else if (input.equals("C")) {
 				System.out.println("Type in the table-name and attributes in the correct order, seperated by one space:");
 				input = s.nextLine();
 				String in[] = input.split(" ");
 				if (in[0].equals("Kunde")) {
 					addKunde(manager,in[1],in[2],in[3],in[4]);
 				} else if (in[0].equals("Bestellung")) {
 					addBestellung(manager,Integer.parseInt(in[1]),Integer.parseInt(in[2]),Integer.parseInt(in[3]));
 				} else if (in[0].equals("Adresse")) {
 					addAdresse(manager,in[1],in[2],Integer.parseInt(in[3]),in[4]);
 				} else if (in[0].equals("BestellungArtikel")) {
 					addBestArt(manager,Integer.parseInt(in[1]),Integer.parseInt(in[2]),Integer.parseInt(in[3]));
 				} else if (in[0].equals("Artikel")) {
 					addArtikel(manager,in[1], Double.parseDouble(in[2]));
 				} else {
 					System.out.println("Looks like you failed, try again!");
 				}
 			} else if (input.equals("U")) {
 				System.out
 						.println("Type in the table-name and attributes in the correct order, seperated by one space:");
 				System.out.println("Attributes you don't want to change must be given the character ':'");
 				input = s.nextLine();
 				String in[] = input.split(" ");
 				if (in[0].equals("Kunde")) {
 					for(int i=2;i<in.length;i++) {
 						if(in[i].equals(":")) {
 							Kunde k=selectKundeK(manager,Integer.parseInt(in[1]));
 							in[i]=k.getString(i);
 						}
 					}
 					updateKunde(manager,Integer.parseInt(in[1]),in[2],in[3],in[4],in[5]);
 				} else if (in[0].equals("Bestellung")) {
 					for(int i=2;i<in.length;i++) {
 						if(in[i].equals(":")) {
 							Bestellung b=selectBestellungB(manager,Integer.parseInt(in[1]));
 							in[i]=b.getString(i);
 						}
 					}
 					updateBestellung(manager,Integer.parseInt(in[1]),Integer.parseInt(in[2]),Integer.parseInt(in[3]),Integer.parseInt(in[4]));
 				} else if (in[0].equals("Adresse")) {
 					for(int i=2;i<in.length;i++) {
 						if(in[i].equals(":")) {
 							Adresse a=selectAdresseA(manager,Integer.parseInt(in[1]));
 							in[i]=a.getString(i);
 						}
 					}
 					updateAdresse(manager,Integer.parseInt(in[1]),in[2],in[3],Integer.parseInt(in[4]),in[5]);
 				} else if (in[0].equals("BestellungArtikel")) {
 					for(int i=3;i<in.length;i++) {
 						if(in[i].equals(":")) {
 							Bestellung_Artikel bar=selectBestArtBA(manager,Integer.parseInt(in[1]), Integer.parseInt(in[2]));
 							in[i]=bar.getString(i);
 						}
 					}
 					updateBestArt(manager,Integer.parseInt(in[1]),Integer.parseInt(in[2]),Integer.parseInt(in[3]));
 				} else if (in[0].equals("Artikel")) {
 					for(int i=2;i<in.length;i++) {
 						if(in[i].equals(":")) {
 							Artikel ar=selectArtikelA(manager,Integer.parseInt(in[1]));
 							in[i]=ar.getString(i);
 						}
 					}
 					updateArtikel(manager,Integer.parseInt(in[1]),in[2],Double.parseDouble(in[3]));
 				} else {
 					System.out.println("Looks like you failed, try again!");
 				}
 			}

 			else if (input.equals("D")) {
 				System.out.println("PLEASE NOTE: Before you delete an Artikel, every corresponding BestellungArtikel table will be deleted.");
 				System.out.println("By deleting a Kunde or Adresse, every Bestellung which contains their IDs will be gone and with that every dependend BestellungArtikel");
 				System.out.println("Type in the table-name and ID (2IDs for BestArt) you want to delete:");
 				input = s.nextLine();
 				String in[] = input.split(" ");
 				if (in[0].equals("Kunde")) {
 					deleteKunde(manager,Integer.parseInt(in[1]));
 				} else if (in[0].equals("Bestellung")) {
 					deleteBestellung(manager,Integer.parseInt(in[1]));
 				} else if (in[0].equals("Adresse")) {
 					deleteAdresse(manager,Integer.parseInt(in[1]));
 				} else if (in[0].equals("BestellungArtikel")) {
 					deleteBestArt(manager,Integer.parseInt(in[1]),Integer.parseInt(in[2]));
 				} else if (in[0].equals("Artikel")) {
 					deleteArtikel(manager,Integer.parseInt(in[1]));
 				} else {
 					System.out.println("Looks like you failed, try again!");
 				}
 			} 
 			else if (input.equals("L")) {
 				System.out.println("Enter the Table name:");
 				input = s.nextLine();
 				if (input.equals("Kunde")) {
 					selectKundeAll(manager);
 				} else if (input.equals("Bestellung")) {
 					selectBestellungAll(manager);
 				} else if (input.equals("Adresse")) {
 					selectAdresseAll(manager);
 				} else if (input.equals("BestellungArtikel")) {
 					selectBestellungArtikelAll(manager);
 				} else if (input.equals("Artikel")) {
 					selectArtikelAll(manager);
 				} else {
 					System.out.println("Looks like you failed, try again!");
 				}
 			}
 			else {
 				System.out.println("Wow, failed already? Please try again or leave!");
 			}
 			System.out.println("Have you finished? y/n:");
 			input = s.nextLine();
 			if (input.equals("y")) {
 				finished = true;
 			}
 		}
 		System.out.println("See ya!");
 		s.close();
 	}
}

