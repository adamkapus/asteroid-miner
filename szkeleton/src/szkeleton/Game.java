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
    	System.out.println("Jatek megnyerve\n");
    }
    public void Lose() {
    	System.out.println("Jatek elveszitve\n");
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
    	settlers.remove(settler);


    	///KENE MINDEN OSZTALYNAK (ami komparalhato) equals(object o) FUGGVENY!!


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
}
