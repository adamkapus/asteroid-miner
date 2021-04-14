package szkeleton;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Prototype {
	private boolean prototypeIsRunning = true; //fut-e a tesztelo program, azaz ki lepett-e a felhasznalo
	ArrayList<Game> games;
	ArrayList<Settler> settlers;
	ArrayList<Robot> robots;
	ArrayList<Ufo> ufos;
	ArrayList<Map> maps;
	ArrayList<Asteroid> asteroids;
	ArrayList<TeleportGate> teleportgates;
	ArrayList<Coal> coals;
	ArrayList<IceWater> icewaters;
	ArrayList<Iron> irons;
	ArrayList<Uran> urans;
	
	public Prototype() {
		games = new ArrayList<Game>();
		settlers = new ArrayList<Settler>(); 
		robots = new ArrayList<Robot>();
		ufos = new ArrayList<Ufo>();
		maps = new ArrayList<Map>();
		asteroids = new ArrayList<Asteroid>();
		teleportgates = new ArrayList<TeleportGate>();
		coals = new ArrayList<Coal>();
		icewaters = new ArrayList<IceWater>();
		irons = new ArrayList<Iron>();
		urans = new ArrayList<Uran>();
	}
	
	public void runPrototype() {
		while(prototypeIsRunning) {
			readCommandFromUser();
		}
	}
	
	public Game getGame(String name) {
		/*
		for(int i = 0; i < games.size(); i++) {
			if(games.get(i).getName().EqualsTo(name)) { return games.get(i);}
		}*/
		System.out.println("Nincs ilyen nevu game");
		return null;
	}
	public Settler getSettler(String name) {
		/*
		for(int i = 0; i < settlers.size(); i++) {
			if(settlers.get(i).getName().EqualsTo(name)) { return settlers.get(i);}
		}*/
		System.out.println("Nincs ilyen nevu settler");
		return null;
	}
	
	//get..
	
	//visszaadja, hogy fut-e még a protottípus
	public void readCommandFromUser() {
		Scanner in = new Scanner(System.in);
	    String userInput = in.nextLine(); //beolvasunk egy sort
	    StringTokenizer st = new StringTokenizer(userInput," ");
	    ArrayList<String> command = new ArrayList<String>();
	    while (st.hasMoreTokens()) {  
	        command.add(st.nextToken());
	    }  
		parseCommand(command);
	}
	
	//kap egy parancsot stringekre bontva
	public void parseCommand(ArrayList<String> command) {
		switch(command.get(0)) {
		case "exit":
	    	prototypeIsRunning = false;
	    	break;
		case "create":
	    	createCommand(command);
	    	break;
		case "die":
	    	dieCommand(command);
	    	break;
		}
		
	}
	
	public void createCommand(ArrayList<String> command) {
		System.out.println("create beolvasva");
		String newObjectName = command.get(2);
		switch(command.get(1)) {
		case "game":
	    	games.add(new Game(newObjectName));
	    	break;
		case "settler":
			settlers.add(new Settler(newObjectName));
	    	break;
		case "robot":
			robots.add(new Robot(newObjectName));
	    	break;
		case "ufo":
	    	ufos.add(new Ufo(newObjectName));
	    	break;
	    	/*
		case "map":
	    	maps.add(new Map(newObjectName));
	    	break;
		case "asteroid":
	    	asteroids.add(new Asteroid(newObjectName));
	    	break;
		case "teleportgate":
	    	teleportgates.add(new TeleportGate(newObjectName));
	    	break;
	    	*/
		case "coal":
	    	coals.add(new Coal(newObjectName));
	    	break;
		case "icewater":
	    	icewaters.add(new IceWater(newObjectName));
	    	break;
		case "iron":
	    	irons.add(new Iron(newObjectName));
	    	break;
		case "uran":
	    	urans.add(new Uran(newObjectName));
	    	break;
		}
	}
	
	
	public void dieCommand(ArrayList<String> command) {
		System.out.println("die beolvasva");
		String objectName = command.get(2);
		switch(command.get(1)) {
		case "settler":
			Settler s = getSettler(objectName);
			s.Die();
	    	break;
		case "robot":
			//..
	    	break;
		case "ufo":
	    	//..
	    	break;
		}
	}
}
