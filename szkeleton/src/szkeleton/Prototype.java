package szkeleton;

import java.io.File;
import java.nio.file.FileSystems;
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
		// amúgy game-ből meg map-ből nem csak 1 lehet?
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
	
	//getterek az objektumokra
	
	public Game getGame(String name) {
		/*
		for(int i = 0; i < games.size(); i++) {
			if(games.get(i).getName().equals(name)) { return games.get(i);}
		}*/
		System.out.println("Nincs ilyen nevu game");
		return null;
	}
	public Settler getSettler(String name) {
		
		for(int i = 0; i < settlers.size(); i++) {
			if(settlers.get(i).getName().equals(name)) { return settlers.get(i);}
		}
		System.out.println("Nincs ilyen nevu settler");
		return null;
	}
	
	public Robot getRobot(String name) {
		
		for(int i = 0; i < settlers.size(); i++) {
			if(robots.get(i).getName().equals(name)) { return robots.get(i);}
		}
		System.out.println("Nincs ilyen nevu robot");
		return null;
	}
	
	public Ufo getUfo(String name) {
		
		for(int i = 0; i < settlers.size(); i++) {
			if(ufos.get(i).getName().equals(name)) { return ufos.get(i);}
		}
		System.out.println("Nincs ilyen nevu ufo");
		return null;
	}
	
	public Map getMap(String name) {
		/*
		for(int i = 0; i < settlers.size(); i++) {
			if(maps.get(i).getName().equals(name)) { return maps.get(i);}
		}
		System.out.println("Nincs ilyen nevu map");*/
		return null;
	}
	
	public Asteroid getAsteroid(String name) {
		
		for(int i = 0; i < settlers.size(); i++) {
			if(asteroids.get(i).GetName().equals(name)) { return asteroids.get(i);}
		}
		System.out.println("Nincs ilyen nevu asteroid");
		return null;
	}
	
	public TeleportGate getTeleportgate(String name) {
		
		for(int i = 0; i < settlers.size(); i++) {
			if(teleportgates.get(i).GetName().equals(name)) { return teleportgates.get(i);}
		}
		System.out.println("Nincs ilyen nevu tg");
		return null;
	}
	
	public Coal getCoal(String name) {
		
		for(int i = 0; i < settlers.size(); i++) {
			if(coals.get(i).getName().equals(name)) { return coals.get(i);}
		}
		System.out.println("Nincs ilyen nevu coal");
		return null;
	}
	
	public IceWater getIcewater(String name) {
		
		for(int i = 0; i < settlers.size(); i++) {
			if(icewaters.get(i).getName().equals(name)) { return icewaters.get(i);}
		}
		System.out.println("Nincs ilyen nevu iw");
		return null;
	}
	
	public Iron getIron(String name) {
		for(int i = 0; i < settlers.size(); i++) {
			if(irons.get(i).getName().equals(name)) { return irons.get(i);}
		}
		System.out.println("Nincs ilyen nevu iron");
		return null;
	}
	
	public Uran getUran(String name) {
		
		for(int i = 0; i < settlers.size(); i++) {
			if(urans.get(i).getName().equals(name)) { return urans.get(i);}
		}
		System.out.println("Nincs ilyen nevu uran");
		return null;
	}
	
	
	//getterek meg.....
	
	//beolvas egy parancsot a konzolrol
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
		case "load":
	    	loadCommand(command);
	    	break;
		case "step":
			stepCommand(command);
			break;
		case "save":
			saveCommand(command);
			break;
		case "teleport":
	    	teleportCommand(command);
	    	break;
		case "solarstorm":
	    	solarstormCommand(command);
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
	    	
		/*case "map":
	    	maps.add(new Map(newObjectName));
	    	break;*/
		case "asteroid":
	    	asteroids.add(new Asteroid(newObjectName));
	    	break;
		case "teleportgate":
	    	teleportgates.add(new TeleportGate(newObjectName));
	    	break;
	    	
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
			Robot r = getRobot(objectName);
			r.Die();
	    	break;
		case "ufo":
	    	Ufo u = getUfo(objectName);
	    	u.Die();
	    	break;
		}
	}
	
	public void loadCommand(ArrayList<String> command) {
		String filename = command.get(1);
		try {
		      File myObj = new File(filename);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        //System.out.println(data);
		        StringTokenizer st = new StringTokenizer(data," ");
			    ArrayList<String> c = new ArrayList<String>();
			    while (st.hasMoreTokens()) {  
			        c.add(st.nextToken());
			    }  
				parseCommand(c);
		        
		      }
		      myReader.close();
		    }
		catch (Exception e) {
		      System.out.println("Hiba a fajlolvasasnal");
		      e.printStackTrace();
		    }
	}

	public void stepCommand(ArrayList<String> command){
		switch (command.get(1)){
			case "game":
				getGame(command.get(2)).OneRound();
				break;
			case "map":
				getMap(command.get(2)).Step();
				break;
			case "asteroid":
				getAsteroid(command.get(2)).Step();
				break;
			case "settler":
				getSettler(command.get(2)).Step();
				break;
			case "robot":
				getRobot(command.get(2)).Step();
				break;
			case "ufo":
				getUfo(command.get(2)).Step();
				break;
		}
	}

	// minden osztálynak kéne valami print vagy ToString függvény ami egy Stringben visszaadja az adott példány
	// statisztikáját
	public void saveCommand(ArrayList<String> command){
		UpdateInventory();
		if (command.size() == 1)
			saveToCommand();
		else
			saveToFile(command.get(1));
	}

	private void saveToCommand(){

	}

	private void saveToFile(String filename){

	}

	private void UpdateInventory(){
		maps.clear();
		for (Game g : games){
			maps.add(g.GetMap());
		}
		/// hiányoznak a map függvényei!!!
	}
	
	public void teleportCommand(ArrayList<String> command) {
		System.out.println("teleport beolvasva");
		String objectName = command.get(2);
		switch(command.get(1)) {
		case "settler":
			Settler s = getSettler(objectName);
			s.UseTeleport();
	    	break;
		case "ufo":
	    	Ufo u = getUfo(objectName);
	    	u.UseTeleport();
	    	break;
		}
	}
	
	public void solarstormCommand(ArrayList<String> command) {
		System.out.println("solarstorm beolvasva");
		String objectName = command.get(2);
		switch(command.get(1)) {
		case "asteroid":
			Asteroid a = getAsteroid(objectName);
			a.HitByStorm();
	    	break;
		case "teleportgate":
	    	TeleportGate tg = getTeleportgate(objectName);
	    	tg.HitByStorm();
	    	break;
		}
	}
}
