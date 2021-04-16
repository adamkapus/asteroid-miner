package szkeleton;

import java.util.ArrayList;

public class Game {
	// telepesek
    private ArrayList<Settler> settlers;
    // robotok
    private ArrayList<Robot> robots;
	private ArrayList<Ufo> ufos;
    // térkép
    private Map map;
    // játék neve
    private String name;

    // konstruktor
    public  Game(String n) {
    	name = n;
    	settlers = new ArrayList<Settler>();
    	robots = new ArrayList<Robot>();
    	ufos = new ArrayList<>();
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
    	System.out.println(name +".Game()");

    	Szkeleton.indentDepth--;
    }

    public ArrayList<Settler> getSettlers(){
    	return this.settlers;
	}

	public ArrayList<Robot> getRobots(){
    	return this.robots;
	}

	public ArrayList<Ufo> getUfos() {
		return ufos;
	}

	public void setUfos(ArrayList<Ufo> ufos) {
		this.ufos = ufos;
	}

	public String getName(){ return this.name; }

	public void setSettlers(ArrayList<Settler> list){
    	this.settlers = list;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void setRobots(ArrayList<Robot> robots) {
		this.robots = robots;
	}

	// játék megnyerése
    public void Win() {
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".Win()");
        Szkeleton.indentDepth--;
    }
    // játék elvesztése
    public void Lose() {
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".Lose()");
    	Szkeleton.indentDepth--;
    }
    // új játék kezdése
    public void NewGame() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".NewGame()");

        Szkeleton.indentDepth++;
        // pálya létrehozása
        Map map = new Map("map", this, 2);

        // pálya összekötöttségek létrehozása
        Szkeleton.indentDepth++;
    	map.Connect();

    	//Most ket jatekos letrehozasa random kezdőhelyen
    	Szkeleton.indentDepth++;
    	Place p1 = map.GetRandomPlace();
    	Szkeleton.indentDepth++;
    	Place p2 = map.GetRandomPlace();
    	Szkeleton.indentDepth++;
    	Settler s1 = new Settler("s1",this,p1);
    	Szkeleton.indentDepth++;
    	Settler s2 = new Settler("s2",this,p2);
    	settlers.add(s1);
    	settlers.add(s2);
        Szkeleton.indentDepth--;
        

    }
    // robot hozzáadása a játékhoz
    public void AddRobot(Robot robot) {
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".AddRobot()");

    	robots.add(robot);
    	Szkeleton.indentDepth--;
    }

    public void addUfo(Ufo u) {
    	ufos.add(u);
	}
    // játékos meghalt
    public void SettlerDied(Settler settler) {
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".SettlerDied()");
    	settlers.remove(settler);

    	// játék elvesztése ha az utolsó telepes is meghalt
		if (settlers.size() == 0) {
			Szkeleton.indentDepth++;
			this.Lose();
		}

    	Szkeleton.indentDepth--;
    }
    // robot meghalt
    public void RobotDied(Robot robot) {
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".RobotDied()");
    	robots.remove(robot);
    	
    	Szkeleton.indentDepth--;
    }

    // ufo meghalt
	public void UfoDied(Ufo ufo){
    	ufos.remove(ufo);
	}

	// térkép lekérése
    public Map GetMap(){
		Szkeleton.writeTabs(Szkeleton.indentDepth);
		System.out.println(name +".GetMap()");

		Szkeleton.indentDepth--;
		return map;
    }

    // egy kör végrehajtása
    public void OneRound() {
    	// először a telepesek lépnek
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".OneRound()");
    	for(int i =0; i < settlers.size(); i++) {
    		Szkeleton.indentDepth++;
    		settlers.get(i).Step();
    	}
		// aztán a robotok lépnek
    	for(int i =0; i < settlers.size(); i++) {
    		Szkeleton.indentDepth++;
    		robots.get(i).Step();
    	}
    	// végül a térkép is lép
    	Szkeleton.indentDepth++;
    	map.Step();
    	
    	Szkeleton.indentDepth--;
    }

    // telepes hozzáadása a játékhoz
    public void AddSettler(Settler s) {
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".AddSettler()");
        Szkeleton.indentDepth--;
        
        settlers.add(s);
    }

	public String ToString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Game ");
		sb.append(name);
		sb.append("\n\tmap ");
		sb.append(map.getName());
		sb.append("\n\trobots ");
		for (Robot r : robots){
			sb.append(r.getName());
			sb.append(' ');
		}
		sb.append("\n\tsettlers ");
		for (Settler s : settlers){
			sb.append(s.getName());
			sb.append(' ');
		}
		sb.append("\n\tufos ");
		/*for (Ufo u : ufos){
			sb.append(u.getName());
			sb.append(' ');
		}*/
		sb.append('\n');

		return sb.toString();
	}
}
