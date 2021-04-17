package szkeleton;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
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
	
	//getterek az objektumokra
	
	public Game getGame(String name) {

		for(int i = 0; i < games.size(); i++) {
			if(games.get(i).getName().equals(name)) { return games.get(i);}
		}
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
		
		for(int i = 0; i < robots.size(); i++) {
			if(robots.get(i).getName().equals(name)) { return robots.get(i);}
		}
		System.out.println("Nincs ilyen nevu robot");
		return null;
	}
	
	public Ufo getUfo(String name) {
		
		for(int i = 0; i < ufos.size(); i++) {
			if(ufos.get(i).getName().equals(name)) { return ufos.get(i);}
		}
		System.out.println("Nincs ilyen nevu ufo");
		return null;
	}
	
	public Map getMap(String name) {

		for(int i = 0; i < maps.size(); i++) {
			if(maps.get(i).getName().equals(name)) { return maps.get(i);}
		}
		System.out.println("Nincs ilyen nevu map");
		return null;
	}
	
	public Asteroid getAsteroid(String name) {
		
		for(int i = 0; i < asteroids.size(); i++) {
			if(asteroids.get(i).GetName().equals(name)) { return asteroids.get(i);}
		}
		System.out.println("Nincs ilyen nevu asteroid");
		return null;
	}
	
	public TeleportGate getTeleportgate(String name) {
		
		for(int i = 0; i < teleportgates.size(); i++) {
			if(teleportgates.get(i).GetName().equals(name)) { return teleportgates.get(i);}
		}
		System.out.println("Nincs ilyen nevu tg");
		return null;
	}
	
	public Coal getCoal(String name) {
		
		for(int i = 0; i < coals.size(); i++) {
			if(coals.get(i).getName().equals(name)) { return coals.get(i);}
		}
		System.out.println("Nincs ilyen nevu coal");
		return null;
	}
	
	public IceWater getIcewater(String name) {
		
		for(int i = 0; i < icewaters.size(); i++) {
			if(icewaters.get(i).getName().equals(name)) { return icewaters.get(i);}
		}
		System.out.println("Nincs ilyen nevu iw");
		return null;
	}
	
	public Iron getIron(String name) {
		for(int i = 0; i < irons.size(); i++) {
			if(irons.get(i).getName().equals(name)) { return irons.get(i);}
		}
		System.out.println("Nincs ilyen nevu iron");
		return null;
	}
	
	public Uran getUran(String name) {
		
		for(int i = 0; i < urans.size(); i++) {
			if(urans.get(i).getName().equals(name)) { return urans.get(i);}
		}
		System.out.println("Nincs ilyen nevu uran");
		return null;
	}
	
	public void addRobot(Robot r) {
		robots.add(r);
	}
	
	public void addTeleportgate(TeleportGate tg) {
		teleportgates.add(tg);
	}
	
	public void removeSettler(Settler s) {
		
		for(int i = 0; i < settlers.size(); i++) {
			if(settlers.get(i).getName().equals(s.getName())) {  settlers.remove(i);}
		}
		
	}
	
	public void removeRobot(Robot r) {
		
		for(int i = 0; i < robots.size(); i++) {
			if(robots.get(i).getName().equals(r.getName())) {  robots.remove(i);}
		}
		
	}
	
	public void removeUfo(Ufo u) {
		
		for(int i = 0; i < ufos.size(); i++) {
			if(ufos.get(i).getName().equals(u.getName())) {  ufos.remove(i);}
		}
	}
	
	public void removeResource(Resource r) {
		for(int i = 0; i < coals.size(); i++) {
			if(coals.get(i).getName().equals(r.getName())) { coals.remove(i);}
		}
		for(int i = 0; i < irons.size(); i++) {
			if(irons.get(i).getName().equals(r.getName())) { irons.remove(i);}
		}
		for(int i = 0; i < icewaters.size(); i++) {
			if(icewaters.get(i).getName().equals(r.getName())) { icewaters.remove(i);}
		}
		for(int i = 0; i < urans.size(); i++) {
			if(urans.get(i).getName().equals(r.getName())) { urans.remove(i);}
		}
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
			case "build":
				buildCommand(command);
				break;
			case "add":
				addCommand(command);
				break;
			case "drill":
				drillCommand(command);
				break;
			case "mine":
				mineCommand(command);
				break;
			case "blow":
				blowCommand(command); break;
			case "set":
				setCommand(command); break;
			case "move":
				moveCommand(command); break;
			case "place":
				placeCommand(command); break;
		}
		
	}
	
	public void createCommand(ArrayList<String> command) {
		System.out.println("create beolvasva");
		String newObjectName = command.get(2);
		switch(command.get(1)) {
		case "game":
	    	games.add(new Game(newObjectName,this));
	    	break;
		case "settler":
			settlers.add(new Settler(newObjectName, this));
	    	break;
		case "robot":
			robots.add(new Robot(newObjectName));
	    	break;
		case "ufo":
	    	ufos.add(new Ufo(newObjectName));
	    	break;
		case "map":
	    	maps.add(new Map(newObjectName));
	    	break;
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

	public void saveCommand(ArrayList<String> command){
		StringBuilder sb = new StringBuilder();
		for (Game g : games)
			sb.append(g.ToString());
		for (Settler s : settlers)
			sb.append(s.ToString());
		for (Robot r : robots)
			sb.append(r.ToString());
		for (Ufo u : ufos)
			sb.append(u.ToString());
		for (Map m : maps)
			sb.append(m.ToString());
		for (Asteroid a : asteroids)
			sb.append(a.ToString());
		for (TeleportGate tg : teleportgates)
			sb.append(tg.ToString());
		for (Coal c : coals)
			sb.append(c.ToString());
		for (IceWater i : icewaters)
			sb.append(i.ToString());
		for (Iron i : irons)
			sb.append(i.ToString());
		for (Uran u : urans)
			sb.append(u.ToString());

		if (command.size() == 1)
			System.out.println(sb.toString());
		else {
			try {
				FileWriter fw = new FileWriter(command.get(1));
				fw.write(sb.toString());
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
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

	public void buildCommand(ArrayList<String> command) {
		System.out.println("build beolvasva");
		if(command.get(1).equals("robot")){
			Settler s = getSettler(command.get(4));
			s.BuildRobot(command.get(2));

		} else if (command.get(1).equals("teleportgate")) {
			Settler s = getSettler(command.get(5));
			s.BuildTeleport(command.get(2), command.get(3));
		}
	}

	public void blowCommand(ArrayList<String> command){
		System.out.println("blow beolvasva");
		String objTipus = command.get(1);
		switch (objTipus) {
			case "settler" :
				Settler s = getSettler(command.get(2));
				s.Die(); break;
			case "robot":
				Robot r = getRobot(command.get(2));
				r.Die(); break;
			case "ufo":
				Ufo u = getUfo(command.get(2));
				u.Die(); break;
			default:
				System.out.println("Helytelen objektomtious! (2. parameter)"); break;
		}
	}

	// Összekapcsolja a kapott objektumokat.
	public void addCommand(ArrayList<String> command){
		System.out.println("add beolvasva");
		String objName1 = command.get(2);
		String objName2 = command.get(4);
		String objType1 = command.get(1);
		String objType2 = command.get(3);

		switch (objType1){
			case "asteroid":
				switch (objType2){
					case "asteroid":
						getAsteroid(objName1).AddNeighbor(getAsteroid(objName2));
						getAsteroid(objName2).AddNeighbor(getAsteroid(objName1));
						break;
					case "coal":
						getAsteroid(objName1).setResource(getCoal(objName2));
						break;
					case "iron":
						getAsteroid(objName1).setResource(getIron(objName2));
						break;
					case "uran":
						getAsteroid(objName1).setResource(getUran(objName2));
						break;
					case "icewater":
						getAsteroid(objName1).setResource(getIcewater(objName2));
						break;
					case "settler":
						Settler a = getSettler(objName2);
						getAsteroid(objName1).AcceptEntity(a);
						getSettler(objName2).SetPlace(getAsteroid(objName1));
						break;
					case "robot":
						getAsteroid(objName1).AcceptEntity(getRobot(objName2));
						getRobot(objName2).SetPlace(getAsteroid(objName1));
						break;
					case "ufo":
						getAsteroid(objName1).AcceptEntity(getUfo(objName2));
						getUfo(objName2).SetPlace(getAsteroid(objName1));
						break;
					case "map":
						getAsteroid(objName1).SetMap(getMap(objName2));
						getMap(objName2).AddPlace(getAsteroid(objName1));
						break;
				}
				break;
			case "coal":
				switch (objType2){
					case "asteroid":
						getAsteroid(objName2).setResource(getCoal(objName1));
						break;
					case  "settler":
						getSettler(objName2).AddResource(getCoal(objName1));
						break;
				}
				break;
			case "iron":
				switch (objType2){
					case "asteroid":
						getAsteroid(objName2).setResource(getIron(objName1));
						break;
					case  "settler":
						getSettler(objName2).AddResource(getIron(objName1));
						break;
				}
				break;
			case "uran":
				switch (objType2){
					case "asteroid":
						getAsteroid(objName2).setResource(getUran(objName1));
						break;
					case  "settler":
						getSettler(objName2).AddResource(getUran(objName1));
						break;
				}
				break;
			case "icewater":
				switch (objType2){
					case "asteroid":
						getAsteroid(objName2).setResource(getIcewater(objName1));
						break;
					case  "settler":
						getSettler(objName2).AddResource(getIcewater(objName1));
						break;
				}
				break;
			case "game":
				switch (objType2){
					case "settler":
						getSettler(objName2).SetGame(getGame(objName1));
						getGame(objName1).AddSettler(getSettler(objName2));
						break;
					case "robot":
						getRobot(objName2).SetGame(getGame(objName1));
						getGame(objName1).AddRobot(getRobot(objName2));
						break;
					case "ufo":
						getUfo(objName2).SetGame(getGame(objName1));
						getGame(objName1).addUfo(getUfo(objName2));
						break;
					case "map":
						getMap(objName2).setGame(getGame(objName1));
						getGame(objName1).setMap(getMap(objName2));
						break;
				}
			case "map":
				switch (objType2) {
					case "asteroid":
						getMap(objName1).AddPlace(getAsteroid(objName2));
						getAsteroid(objName1).SetMap(getMap(objName1));
						break;
					case "game":
						getMap(objName1).setGame(getGame(objName2));
						getGame(objName2).setMap(getMap(objName1));
						break;
				}
				break;
			case "robot":
				switch (objType2){
					case "asteroid":
						getRobot(objName1).SetPlace(getAsteroid(objName2));
						getAsteroid(objName2).AcceptEntity(getRobot(objName1));
						break;
					case "teleportgate":
						getRobot(objName1).SetPlace(getTeleportgate(objName2));
						getTeleportgate(objName2).AcceptEntity(getRobot(objName1));
						break;
					case "game":
						getRobot(objName1).SetGame(getGame(objName2));
						getGame(objName2).AddRobot(getRobot(objName1));
						break;
				}
				break;
			case "settler":
				switch (objType2){
					case "asteroid":
						getSettler(objName1).SetPlace(getAsteroid(objName2));
						getAsteroid(objName2).AcceptEntity(getSettler(objName1));
						break;
					case "teleportgate":
						getSettler(objName1).SetPlace(getTeleportgate(objName2));
						getTeleportgate(objName2).AcceptEntity(getSettler(objName1));
						break;
					case "game":
						getSettler(objName1).SetGame(getGame(objName2));
						getGame(objName2).AddSettler(getSettler(objName1));
						break;
				}
				break;
			case "ufo":
				switch (objType2){
					case "asteroid":
						getUfo(objName1).SetPlace(getAsteroid(objName2));
						getAsteroid(objName2).AcceptEntity(getUfo(objName1));
						break;
					case "teleportgate":
						getUfo(objName1).SetPlace(getTeleportgate(objName2));
						getTeleportgate(objName2).AcceptEntity(getUfo(objName1));
						break;
					case "game":
						getUfo(objName1).SetGame(getGame(objName2));
						getGame(objName2).addUfo(getUfo(objName1));
						break;
				}
			case "teleportgate":
				switch (objType2){
					case "settler":
						getTeleportgate(objName1).AcceptEntity(getSettler(objName2));
						getSettler(objName2).SetPlace(getTeleportgate(objName1));
						break;
					case "robot":
						getTeleportgate(objName1).AcceptEntity(getRobot(objName2));
						getRobot(objName2).SetPlace(getTeleportgate(objName1));
						break;
					case "ufo":
						getTeleportgate(objName1).AcceptEntity(getUfo(objName2));
						getUfo(objName2).SetPlace(getTeleportgate(objName1));
						break;
					case "teleportgate":
						getTeleportgate(objName1).SetPair(getTeleportgate(objName2));
						getTeleportgate(objName2).SetPair(getTeleportgate(objName1));
						break;
					case "map":
						getTeleportgate(objName1).SetMap(getMap(objName2));
						getMap(objName2).AddPlace(getTeleportgate(objName1));
						break;
				}
				break;
		}
	}

	// Végrehajtja a fúrás parancsot
	public void drillCommand(ArrayList<String> command){
		System.out.println("drill beolvasva");
		String entityType = command.get(1);
		String entityName = command.get(2);

		switch (entityType){
			case "settler":
				Settler s = getSettler(entityName);
				s.Drill();
				break;
			case "robot":
				Robot r = getRobot(entityName);
				r.Drill();
				break;
		}
	}

	// Végrehajtja a bányászás parancsot
	public void mineCommand(ArrayList<String> command){
		System.out.println("mine beolvasva");
		String entityType = command.get(1);
		String entityName = command.get(2);

		switch (entityType){
			case "settler":
				Settler s = getSettler(entityName);
				s.Mine();
				break;
			case "ufo":
				Ufo u = getUfo(entityName);
				u.Mine();
				break;
		}
	}

	public void setCommand(ArrayList<String> command){
		System.out.println("set beolvasva");
		String objType = command.get(1);
		String objName = command.get(2);
		String attrName = command.get(3);
		String attrVal = command.get(4);

		switch (objType) {
			case "asteroid":
				switch (attrName) {
					case "timeLimit":
						getAsteroid(objName).setTimeLimit(Integer.valueOf(attrVal));
						break;
					case "timeCurrent":
						getAsteroid(objName).setTimeCurrent(Integer.valueOf(attrVal));
						break;
					case "layers":
						getAsteroid(objName).SetLayers(Integer.valueOf(attrVal));
						break;
					case "state":
						if (attrVal == "close") getAsteroid(objName).SetStateToClose();
						else getAsteroid(objName).SetStateToFar();
						break;
				}
				break;
			case "uran":
				switch (attrName) {
					case "closeToSunTicks":
						getUran(objName).setCloseToSunTicks(Integer.valueOf(attrVal));
						break;
				}
				break;
			case "teleportgate":
				switch (attrName) {
					case "pair":
						getTeleportgate(objName).SetPair(getTeleportgate(attrVal));
						break;
					case "pairIsPlaced":
						getTeleportgate(objName).SetPairIsPlaced();
						break;
						//ezt ide beleírtam ezt is lehessen
					case "setCrazy":
						getTeleportgate(objName).SetIsCrazy();
						break;
				}
				break;

		}
	}

	public void moveCommand(ArrayList<String> command){
		System.out.println("move beolvasva");
		String objType1 = command.get(1);
		String objName1 = command.get(2);
		String objType2 = command.get(3);
		String objName2 = command.get(4);
		switch (objType1) {
			case "settler":
				switch (objType2) {
					case "asteroid":
						getSettler(objName1).Move(getAsteroid(objName2).GetPlaceID());
						break;
					case "teleportgate":
						getSettler(objName1).Move(getTeleportgate(objName2).GetPlaceID());
						getSettler(objName1).UseTeleport();
						break;
				}
				break;
			case "ufo":
				switch (objType2) {
					case "asteroid":
						getUfo(objName1).Move(getAsteroid(objName2).GetPlaceID());
						break;
					case "teleportgate":
						getUfo(objName1).Move(getTeleportgate(objName2).GetPlaceID());
						getUfo(objName1).UseTeleport();
						break;
				}
				break;
			case "robot":
				switch (objType2) {
					case "asteroid":
						getRobot(objName1).Move(getAsteroid(objName2).GetPlaceID());
						break;
				}
				break;

		}
	}

	public void placeCommand(ArrayList<String> command){
		System.out.println("move beolvasva");
		String objType1 = command.get(1);
		String objName1 = command.get(2);
		String objType2 = command.get(3);
		String objName2 = command.get(4);
		switch (objType1) {
			case "uran":
				getSettler(objName2).PlaceResource(13);
				break;
			case "iron":
				getSettler(objName2).PlaceResource(12);
				break;
			case "icewater":
				getSettler(objName2).PlaceResource(11);
				break;
			case "coal":
				getSettler(objName2).PlaceResource(10);
				break;

			case "teleportgate":
				getSettler(objName2).PlaceDownTeleport();
				break;


		}


	}
}
