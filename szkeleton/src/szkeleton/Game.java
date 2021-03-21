package szkeleton;

import java.util.ArrayList;

public class Game {
    private ArrayList<Settler> settlers;
    private ArrayList<Robot> robots;
    private Map map;
    
    private String name;
    
    public  Game(String n) {
    	name = n;
    	settlers = new ArrayList<Settler>();
    	robots = new ArrayList<Robot>();
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
    	System.out.println(name +".Game()");

    	Szkeleton.indentDepth--;
    }

    public void Win() {
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".Win()");
    	//System.out.println("Jatek megnyerve\n");
        Szkeleton.indentDepth--;
    }
    public void Lose() {
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".Lose()");
    	//System.out.println("Jatek elveszitve\n");
    	Szkeleton.indentDepth--;
    }
    public void NewGame() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".NewGame()");

        Szkeleton.indentDepth++;
    	//konstruktornak masnak kene lennie
    	//map = new Map(this);

        Map map = new Map("map", this);


    	map.Connect();

    	//Most ket jatekos letrehozasa
    	Place p1 = map.GetRandomPlace();
    	Place p2 = map.GetRandomPlace();

    	//konstrukotr kene
    	//Settler s1 = new Settler(this,p1);
    	//Settler s2 = new Settler(this,p2);
    	//settlers.add(s1);
    	//Settlers.add(s2);
        Szkeleton.indentDepth--;

    }
    public void AddRobot(Robot robot) {
    	robots.add(robot);
    }
    public void SettlerDied(Settler settler) {
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".SettlerDied()");
    	settlers.remove(settler);
    	
    	Szkeleton.indentDepth++;
    	this.Lose();

    	
    	///KENE MINDEN OSZTALYNAK (ami komparalhato) equals(object o) FUGGVENY!!

    	 Szkeleton.indentDepth--;
    }
    public void RobotDied(Robot robot) {
    	robots.remove(robot);
    }

    public Map GetMap(){return map;}

    //KA ez sincs rajta a diagramon, de valami hasonlo kene
    public void OneRound() {
    	for(int i =0; i < settlers.size(); i++) {
    		settlers.get(i).Step();
    	}

    	for(int i =0; i < settlers.size(); i++) {
    		robots.get(i).Step();
    	}

    	map.Step();
    }
    
    
    //KA: nincs rajta a diagramon, scenario epiteshez kell
    
    public void AddSettler(Settler s) {
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".AddSettler()");
        Szkeleton.indentDepth--;
        
        settlers.add(s);
    }
}
