import java.lang.Math;
import java.util.ArrayList;
import java.util.Scanner;

public class SuperHeroOOP{
	public static void main(String[]args){
		GothamLikeAdvTown g = new GothamLikeAdvTown();
	}
}

class GothamLikeAdvTown{
	GothamLikeAdvTown(){
		ArrayList<Person> DPS = new ArrayList<Person>();//Daily Planet Street (DPS)

		String [] f = {"Scarlett","Chris","Mark","Jeremy","Elizabeth","Tom","Paul","Chadwick","Anthony","Roberto"}; //{"F"}; //List of 10 first names
		String [] l = {"Johansson","Evans","Ruffalo","Renner","Olsen","Hiddleston","Bettany","Boseman","Mackie","Winter"}; //{"L"}; // List of 10 last names
		String [] j = {"Doctor","Surgeon","Carpenter","Engineer","Technician","Cheff","Comedian","Special Ed","Uber Driver","Maid"}; //{"J"}; //List of 10 jobs

		Hero h = new Hero("Tony Stark", "Iron Man", "Physicst", "Super Strength", false, 1, 150, 20, "HULK SMASH!"); //Superhero
		Villain v = new Villain("TexhNine Robot", "Ultron", "Politician", "Aimbot", true, 1, 200, 30, "hAhAHHaHAaH"); //Supervillain

		int x, y;
		x = (int)((Math.random()*50)+0); //Random index placement for Hero in DPS
		y = (int)((Math.random()*50)+0); //Random index placement for Villain in DPS

		if(y == x){ //If villain index = hero index
			while(y == x){ //Loop throuugh until villain index != hero index
				y = (int)(Math.random()*50);
			}
		}

		for(int i = 0; i < 50; i++){ //Add Persons to DPS
			int a = (int)(Math.random()*(f.length-1)); //Random f value
			int b = (int)(Math.random()*(l.length-1)); //Random l value
			int c = (int)(Math.random()*(j.length-1)); //Random j value

			if(i == x) //Add hero at specified index
				DPS.add(h);

			else if(i == y) //Add villain at specified index
				DPS.add(v);

			else if(i != x && i != y){ //Add random person at specified index
				String fullName = f[a]+" "+l[b];
				DPS.add(new Person(fullName, j[c]));
			}
		}

		ArrayList<Person> safeP = new ArrayList<Person>(); //Safe place
		ArrayList<Person> unsafeP = new ArrayList<Person>(); //Unsafe Place

		while(DPS.size() > 2){ //Loop through removing random persons until only hero and villain remain in DPS
			if(DPS.indexOf(v) == 0){ //If Villain at the begining of the array list
				if(DPS.indexOf(v)+1 != DPS.indexOf(h)){
					unsafeP.add(DPS.remove(DPS.indexOf(v)+1));
					System.out.println(v.getEL());
					System.out.println(h.getCP());
				}

				else if(DPS.indexOf(v)+1 == DPS.indexOf(h)){
					int r = (int)((Math.random()*DPS.size())+0);
					while(r == DPS.indexOf(h) || r == DPS.indexOf(v)){
						r = (int)((Math.random()*DPS.size())+0);
					}
					DPS.add(r, DPS.remove(DPS.indexOf(h)));
					unsafeP.add(DPS.remove(DPS.indexOf(v)+1));
					System.out.println(v.getEL());
					System.out.println(h.getCP());
				}

				if(DPS.size() > 3){
					int r2 = (int)((Math.random()*DPS.size())+0);

					while(r2 == DPS.indexOf(h) || r2 == DPS.indexOf(v)){
						r2 = (int)((Math.random()*DPS.size())+0);
					}

					safeP.add(DPS.remove(r2));
					int r3 = (int)((Math.random()*DPS.size())+0);

					if(DPS.size() != 2){
						while(r3 == DPS.indexOf(h) || r3 == DPS.indexOf(v)){
							r3 = (int)((Math.random()*DPS.size())+0);
						}

						DPS.add(r3, DPS.remove(DPS.indexOf(h)));
					}
				}
			}

			else if(DPS.indexOf(v) == (DPS.size()-1)){ //If Villain at the end of the array list
				if(DPS.indexOf(v)-1 != DPS.indexOf(h)){
					unsafeP.add(DPS.remove(DPS.indexOf(v)-1));
					System.out.println(v.getEL());
					System.out.println(h.getCP());
				}

				else if(DPS.indexOf(v)-1 == DPS.indexOf(h)){
					int r = (int)((Math.random()*DPS.size())+0);

					while(r == DPS.indexOf(h) || r == DPS.indexOf(v)){
						r = (int)((Math.random()*DPS.size())+0);
					}

					DPS.add(r, DPS.remove(DPS.indexOf(h)));
					unsafeP.add(DPS.remove(DPS.indexOf(v)-1));
					System.out.println(v.getEL());
					System.out.println(h.getCP());
				}

				if(DPS.size() > 3){
					int r2 = (int)((Math.random()*DPS.size())+0);

					while(r2 == DPS.indexOf(h) || r2 == DPS.indexOf(v)){
						r2 = (int)((Math.random()*DPS.size())+0);
					}

					safeP.add(DPS.remove(r2));
					int r3 = (int)((Math.random()*DPS.size())+0);

					if(DPS.size() != 2){
						while(r3 == DPS.indexOf(h) || r3 == DPS.indexOf(v)){
							r3 = (int)((Math.random()*DPS.size())+0);
						}

						DPS.add(r3, DPS.remove(DPS.indexOf(h)));
					}
				}
			}

			else if(DPS.indexOf(v) != 0 || DPS.indexOf(v) != (DPS.size()-1)){ //If Villain in the middle of the array list (Not at begining or end)
				if(DPS.indexOf(v)-1 != DPS.indexOf(h) && DPS.indexOf(v)+1 != DPS.indexOf(h)){ //If person to left or right of villain is not hero
					int r = (int)((Math.random()*100)+1);

					if(r%2 == 1){ //Get rid of person on left of villain
						unsafeP.add(DPS.remove(DPS.indexOf(v)-1)); //Move random person from DPS to Unsafe Place
						System.out.println(v.getEL());
						System.out.println(h.getCP());
					}

					else if(r%2 == 0){ //Get rid of person on right of villain
						unsafeP.add(DPS.remove(DPS.indexOf(v)+1)); //Move random person from DPS to Unsafe Place
						System.out.println(v.getEL());
						System.out.println(h.getCP());
					}
				}

				else{
					if(DPS.indexOf(v)-1 == DPS.indexOf(h)){ //Get rid of person who is not hero next to Villain
						unsafeP.add(DPS.remove(DPS.indexOf(v)+1)); //Move random person from DPS to Unsafe Place
						System.out.println(v.getEL());
						System.out.println(h.getCP());
					}

					else if(DPS.indexOf(v)+1 == DPS.indexOf(h)){ //Get rid of person who is not hero next to Villain
						unsafeP.add(DPS.remove(DPS.indexOf(v)-1)); //Move random person from DPS to Unsafe Place
						System.out.println(v.getEL());
						System.out.println(h.getCP());
					}

				}
				if(DPS.size() > 3){
					int r2 = (int)((Math.random()*DPS.size())+0);

					while(r2 == DPS.indexOf(h) || r2 == DPS.indexOf(v)){
						r2 = (int)((Math.random()*DPS.size())+0);
					}

					safeP.add(DPS.remove(r2));
					int r3 = (int)((Math.random()*DPS.size())+0);

					if(DPS.size() != 2){
						while(r3 == DPS.indexOf(h) || r3 == DPS.indexOf(v)){
							r3 = (int)((Math.random()*DPS.size())+0);
						}

						DPS.add(r3, DPS.remove(DPS.indexOf(h)));
					}
				}
			}
			System.out.println();
		}

		System.out.println("\n"+DPS+"\nCombat Initiated!\n");
		int hH = h.getHP();
		int vH = v.getHP();
		System.out.println(h.getSN()+" has "+hH+" HP initially.");
		System.out.println(v.getSN()+" has "+vH+" HP initially.\n");

		while(hH >= 0 && vH >= 0){
			int r = (int)((Math.random()*100)+1);

			if(r%2 == 0){
				int a = (int)((Math.random()*h.getMD())+1);
				vH -= a;
				System.out.println(h.getSN()+" attacks "+v.getSN()+" for "+a);

				if(vH > 0){
					int aa = (int)((Math.random()*v.getMD())+1);
					hH-=aa;
					System.out.println(v.getSN()+" attacks "+h.getSN()+" for "+aa);
				}
			}

			else if(r%2 == 1){
				int aa = (int)((Math.random()*v.getMD())+1);
				hH-=aa;
				System.out.println(v.getSN()+" attacks "+h.getSN()+" for "+aa);

				if(hH > 0){
					int a = (int)((Math.random()*h.getMD())+1);
					vH-=a;
					System.out.println(h.getSN()+" attacks "+v.getSN()+" for "+a);
				}
			}
			if(hH <= 0 || vH <= 0)
				break;

			else{
				System.out.println(h.getSN()+" has "+hH+" Hp left.");
				System.out.println(v.getSN()+" has "+vH+" Hp left.\n");
			}
		}
		if(hH <= 0 && vH > 0){
			System.out.println(h.getSN()+" has 0 Hp left.");
			System.out.println(v.getSN()+" has "+vH+" Hp left.\n");
			System.out.println(h.getSN()+" was defeated.");
		}

		else if(vH <= 0 && hH > 0){
			System.out.println(h.getSN()+" has "+hH+" Hp left.\n");
			System.out.println(v.getSN()+" has 0 Hp left.");
			System.out.println(v.getSN()+" was defeated.");
		}
	}
}

class Person{
	private String name, job;

	Person(String name, String job){
		this.name = name;
		this.job = job;
	}

	public String getName(){
		return name;
	}

	public String getJob(){
		return job;
	}

	public String toString(){
		return name;
	}
}

class Soop extends Person{
	private String superPower, soopName;
	private boolean cape;
	private int powerLevel, hP, maxDamage;

	Soop(String name, String soopName, String job, String superPower, boolean cape, int powerLevel, int hP, int maxDamage){
		super(name, job);
		this.superPower = superPower;
		this.cape = cape;
		this.powerLevel = powerLevel;
		this.soopName = soopName;
		this.hP = hP;
		this.maxDamage = maxDamage;
	}

	public String getSP(){
		return superPower;
	}

	public String getSN(){
			return soopName;
	}

	public boolean hasCape(){
		return cape;
	}

	public int getPL(){
		return powerLevel;
	}

	public int getHP(){
		return hP;
	}

	public int getMD(){
		return maxDamage;
	}

	public void powerLevelMod(int newLevel){
		powerLevel = newLevel;
	}

	public void hpReproduction(int damage){
		hP -= damage;
	}
}

class Hero extends Soop{
	private String catchPhrase;

	Hero(String name, String soopName, String job, String superPower, boolean cape, int powerLevel, int hP, int maxDamage, String catchPhrase){
		super(name, soopName, job, superPower, cape, powerLevel, hP, maxDamage);
		this.catchPhrase = catchPhrase;
	}

	public String getCP(){
		return catchPhrase;
	}

	public int getPL(){
		int n = super.getPL() + (int)((Math.random()*10)+1);
		super.powerLevelMod(n);
		return n;
	}
}

class Villain extends Soop{
	private String evilLaugh;

	Villain(String name, String soopName, String job, String superPower, boolean cape, int powerLevel, int hP, int maxDamage, String evilLaugh){
		super(name, soopName, job, superPower, cape, powerLevel, hP, maxDamage);
		this.evilLaugh = evilLaugh;
	}

	public String getEL(){
		return evilLaugh;
	}
}