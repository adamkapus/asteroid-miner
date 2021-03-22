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

        Map map = new Map("map", this, 2);

        Szkeleton.indentDepth++;
    	map.Connect();

    	//Most ket jatekos letrehozasa
    	Szkeleton.indentDepth++;
    	Place p1 = map.GetRandomPlace();
    	Szkeleton.indentDepth++;
    	Place p2 = map.GetRandomPlace();

    	//konstrukotr kene
    	Szkeleton.indentDepth++;
    	Settler s1 = new Settler("s1",this,p1);
    	Szkeleton.indentDepth++;
    	Settler s2 = new Settler("s2",this,p2);
    	settlers.add(s1);
    	settlers.add(s2);
        Szkeleton.indentDepth--;

    }
    public void AddRobot(Robot robot) {
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".AddRobot()");

    	robots.add(robot);
    	Szkeleton.indentDepth--;
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
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".RemoveRobot()");
    	robots.remove(robot);
    	
    	Szkeleton.indentDepth--;
    }

    public Map GetMap(){
		Szkeleton.writeTabs(Szkeleton.indentDepth);
		System.out.println(name +".RemoveRobot()");

		Szkeleton.indentDepth--;
		return map;
    }

    //KA ez sincs rajta a diagramon, de valami hasonlo kene
    public void OneRound() {
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".OneRound()");
    	for(int i =0; i < settlers.size(); i++) {
    		Szkeleton.indentDepth++;
    		settlers.get(i).Step();
    	}

    	for(int i =0; i < settlers.size(); i++) {
    		Szkeleton.indentDepth++;
    		robots.get(i).Step();
    	}
    	Szkeleton.indentDepth++;
    	map.Step();
    	
    	Szkeleton.indentDepth--;
    }
    
    
    //KA: nincs rajta a diagramon, scenario epiteshez kell
    
    public void AddSettler(Settler s) {
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name + ".AddSettler()");
        Szkeleton.indentDepth--;
        
        settlers.add(s);
    }
}
