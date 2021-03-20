package szkeleton;

import java.util.ArrayList;

public class Game {
    private ArrayList<Settler> settlers;
    private ArrayList<Robot> robots;
    private Map map;
    //KA: ez nincs rajta a diagramon, szerintem kene, hogyha mondjuk egy kor kozepén van vege a jateknak, akkor a többi entity stepjet mar ne hivja
    private boolean gameIsFinished = false;

    public void Win() {
    	System.out.println("Jatek megnyerve\n");
    }
    public void Lose() {
    	System.out.println("Jatek elveszitve\n");
    }
    public void NewGame() {
    	//konstruktornak masnak kene lennie
    	//map = new Map(this);
    	map.Connect();
    	
    	//Most ket jatekos letrehozasa
    	Place p1 = map.GetRandomPlace();
    	Place p2 = map.GetRandomPlace();
    	
    	//konstrukotr kene
    	//Settler s1 = new Settler(this,p1);
    	//Settler s2 = new Settler(this,p2);
    	//settlers.add(s1);
    	//Settlers.add(s2);
    	
    	
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
