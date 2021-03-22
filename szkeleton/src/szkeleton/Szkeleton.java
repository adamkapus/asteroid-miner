package szkeleton;



import java.util.Scanner;


/**
 * Játék tesztelésére szolgáló osztály.
 * A nyersanyagokhoz tartozó sorszámok:
 * *coal -10
 * *icewater -11
 * *iron -12
 * *uran -13
 */

public class Szkeleton {
	
	private int numberOfScenarios = 29;
	private boolean szkeletonIsRunning = true;
	public static int indentDepth = 0;
	
	private String keregKerdes = "0-e a kereg?";
	private String stb;
	
	public Szkeleton() {}

	public void runSzkeleton() {

		this.printSequenceOptions();
		
		while(szkeletonIsRunning) {
			int sequenceNumber = readSequenceNumberFromUser();
			if(sequenceNumber == 0) {
				System.out.println("Kilepes valasztva\n");
				szkeletonIsRunning = false;
			}
			else {
				System.out.println("Szekvenciaszam beolvasva: " + sequenceNumber + "\n");
				runSequence(sequenceNumber);
			}
		}

	}
	
	private void printSequenceOptions() {
		System.out.println("0 a kilepeshez\n"
				+ "1 -- Asteroid hit by solar storm,  asteroid without resource and layers\n"
				+ "2 -- Asteroid Step Radioactive Asteroid\n"
				+ "3 -- Asteoid Step: IceWater Sublimates\n"
				+ "4 -- IceWater Sublimates\n"
				+ "5 -- New game\n"
				+ "6 -- Normal asteroid step\n"
				+ "7 -- Robot blown up\n"
				+ "8 -- Robot drills\n"
				+ "9 -- Robot hit by Solar Storm, asteroid with resource\n"
				+ "10 -- Robot hit by storm, teleportgate\n"
				+ "11 -- Robot moves\n"
				+ "12 -- Settler blown up\n"
				+ "13 -- Settler build robot with resource\n"
				+ "14 -- Settler build robot without resource\n"
				+ "15 -- Settler Build Teleport With Enough Resources\n"
				+ "16 -- Settler Build Teleport Without Enough Resource\n"
				+ "17 -- Settler Cannot Build Teleport\n"
				+ "18 -- Settler died, game over\n"
				+ "19 -- Settler died, no game over\n"
				+ "20 -- Settler drills\n"
				+ "21 -- Settler hit by solar storm, asteroid with resource\n"
				+ "22 -- Settler hit by storm, teleportgate\n"
				+ "23 -- Settler mine with resourcen"
				+ "24 -- Settler mine without resource\n"
				+ "25 -- Settler move \n"
				+ "26 -- Settler Place resource: Not empty\n"
				+ "27 -- Settler Place resource\n"
				+ "28 -- Settler Place Teleport\n"
				+ "29 -- Settler Use Teleport\n"
				);
				
	}
	
	private int readSequenceNumberFromUser() {
		boolean validNumberread = false;
		int number = -1;
		
		// Using Scanner for Getting Input from User 
		Scanner in = new Scanner(System.in);
		
		while(!validNumberread) {
			boolean numberRead = false;
	        String s = in.nextLine(); 
	        
	        try {
	            number = Integer.parseInt(s);
	            numberRead = true;
	        } catch (NumberFormatException nfe) {
	        	numberRead = false;
	            System.out.println("Kerlek egy szamot adj meg!\n");
	        }
	        
	        if(numberRead) {
	        	if((number <= numberOfScenarios) && (number >= 0)) {
	        		validNumberread = true;
	        	}
	        	else {
	        		validNumberread = false;
	        		System.out.println("Kerlek egy szekvenciahoz tartozo szamot adj meg!\n");
	        	}
	        }
		}
		
		
		return number;
	}
	
	private boolean askQuestionFromUser(String question) {
		
		System.out.println(question + " igen-nel vagy nem-mel valaszolj kerlek!\n");
		
		boolean validAnswerread = false;
		boolean answer = false;
		
		// Using Scanner for Getting Input from User 
		Scanner in = new Scanner(System.in);
		
		while(!validAnswerread) {
	        String s = in.nextLine(); 
	        
	        if(s.equals("igen") || s.equals("nem")) {
	        	validAnswerread = true;
	        	if(s.equals("igen")) {
	        		answer = true;
	        	}
	        	else {
	        		answer = false;
	        	}
	        }
	        else {
	        	validAnswerread = false;
	        	System.out.println("Kerlek jol valaszolj pls!\n");
	        	}
	        
		}

		return answer;
	}
	
	
	private void runSequence(int seq) {
		indentDepth = 0;
		switch(seq) {
		  	case 1:
		    	scenario1();
		    	break;
		  	case 2:
		    	scenario2();
		    	break;
		  	case 3:
				scenario3();
				break;
			case 4:
				scenario4();
				break;
			case 5:
				scenario5();
				break;
			case 6:
				scenario6();
				break;
			case 7:
				scenario7();
				break;
			case 8:
				scenario8();
				break;
			case 9:
				scenario9();
				break;
			case 10:
				scenario10();
				break;
			case 11:
				scenario11();
				break;
			case 12:
				scenario12();
				break;
			case 13:
				scenario13();
				break;
			case 14:
				scenario14();
				break;
			case 15:
				scenario15();
				break;
			case 16:
				scenario16();
				break;
			case 17:
				scenario17();
				break;
			case 18:
				scenario18();
				break;
			case 19:
				scenario19();
				break;
			case 20:
				scenario20();
				break;
			case 21:
				scenario21();
				break;
			case 22:
				scenario22();
				break;
			case 23:
				scenario23();
				break;
			case 24:
				scenario24();
				break;
			case 25:
				scenario25();
				break;
			case 26:
				scenario26();
				break;
			case 27:
				scenario27();
				break;
			case 28:
				scenario28();
				break;

			case 29:
				scenario29();
				break;
		}

	}
	
	public static void writeTabs(int tabs) {
		for (int i = 0; i < tabs; i++)
		System.out.print("\t");
	}
	
	
	//Asteroid hit by solarstorm, without resource and layers
	private void scenario1() {
		indentDepth++;
		Game g = new Game("g1");
		indentDepth++;
		Map m = new Map("map", g);
		indentDepth++;
		Asteroid a1 = new Asteroid("a1", 10, m, null);
		indentDepth++;
		a1.SetLayers(0);
		indentDepth++;
		m.AddPlace(a1);
		indentDepth++;
		m.SolarStorm();
	}
	
	private void scenario2() { // Asteroid step Raioaktive
		indentDepth++;
		Game g = new Game("g1");
		indentDepth++;
		Map m = new Map("map", g);
		indentDepth++;
		Uran u = new Uran("u");
		indentDepth++;
		Asteroid a = new Asteroid("a", 1, m, u);
		indentDepth++;
		Settler s = new Settler("s1", g, a);
		indentDepth++;
		a.AcceptEntity(s);
		indentDepth++;
		a.Step();
	}
	
	private void scenario3() { // Asteroid Step IceWater
		indentDepth++;
		Game g = new Game("g1");
		indentDepth++;
		Map m = new Map("map", g);
		indentDepth++;
		IceWater iw = new IceWater("iw");
		indentDepth++;
		Asteroid a = new Asteroid("a", 1, m, iw);
		indentDepth++;
		Settler s = new Settler("s1", g, a);
		indentDepth++;
		a.AcceptEntity(s);
		indentDepth++;
		a.Step();
	}
	//EZ SZTEM VAGY FÖLÖSLEGES VAGY NEM JÓ
	private void scenario4(){//Icewater Sublimates
		indentDepth++;
		IceWater iw = new IceWater("iw");
		indentDepth++;
		Map m = new Map("m", null);
		indentDepth++;
		Asteroid a = new Asteroid("a", 1,m, iw);
		a.SetLayers(0);
		a.SetStateToClose();
		indentDepth++;
		a.Step();
	}


	//New game
	private void scenario5(){

		indentDepth++;
		Game g = new Game("g1");

		indentDepth++;
		g.NewGame();

	}

	//Normal asteroid step
	private void scenario6(){
		indentDepth++;
		Game g = new Game("g1");
		indentDepth++;
		Map m = new Map("map", g);
		indentDepth++;
		Coal c = new Coal("c");
		indentDepth++;
		Asteroid a = new Asteroid("a", 1, m, c);
		indentDepth++;
		Settler s = new Settler("s1", g, a);
		indentDepth++;
		a.AcceptEntity(s);
		indentDepth++;
		a.Step();

	}

	//Robot blown up
	private void scenario7(){

		indentDepth++;
		Game g = new Game("g1");

		indentDepth++;
		Asteroid a1 = new Asteroid("a1",1, null, null);

		indentDepth++;
		Asteroid a2 = new Asteroid("a2", 2, null, null);

		indentDepth++;
		a1.AddNeighbor(a2);
		a2.AddNeighbor(a1);
		Robot r = new Robot("r1", g, a1);

		indentDepth++;
		a1.AcceptEntity(r);
		r.BlownUp();


	}

	//Robot drills
	private void scenario8(){
		indentDepth++;
		Game g = new Game("g");
		indentDepth++;
		Asteroid a = new Asteroid("a", 1, null, null);
		indentDepth++;
		Robot r = new Robot("robot", g, a);
		indentDepth++;
		a.AcceptEntity(r);
		indentDepth++;
		r.Drill();
	}

	// robot hit by storm, asteroid with resource
	private void scenario9() {
		indentDepth++;
		Game g = new Game("g1");
		indentDepth++;
		Map m = new Map("map", g);
		indentDepth++;
		Iron i = new Iron("i");
		indentDepth++;
		Asteroid a1 = new Asteroid("a1", 10, m, i);
		
		indentDepth++;
		Robot s1 = new Robot("r1",g, a1 );
		
		indentDepth++;
		a1.SetLayers(0);
		indentDepth++;
		m.AddPlace(a1);
		indentDepth++;
		m.SolarStorm();
	}
	
	//robot hit by storm, teleportgate
	private void scenario10() {
		indentDepth++;
		Game g = new Game("g1");
		indentDepth++;
		Map m = new Map("map", g);
		
		indentDepth++;
		TeleportGate t1 = new TeleportGate("t1", 10, m);
		
		indentDepth++;
		Robot s1 = new Robot("r1",g, t1 );
		
		indentDepth++;
		m.AddPlace(t1);
		
		indentDepth++;
		m.SolarStorm();
	}

	private void scenario11() { // Robot Moves
		indentDepth++;
		Game game = new Game("Game");
		indentDepth++;
		Asteroid asteroid1 = new Asteroid("Asteroid1", 1, null, null);
		indentDepth++;
		Asteroid asteroid2 = new Asteroid("Asteroid2", 2, null, null);
		indentDepth++;
		asteroid1.AddNeighbor(asteroid2);
		indentDepth++;
		asteroid2.AddNeighbor(asteroid1);
		indentDepth++;
		Robot robot = new Robot("Robot", game, asteroid1);
		indentDepth++;
		asteroid1.AcceptEntity(robot);
		indentDepth++;
		robot.Move(2);
	}

	//Setler blown up
	private void scenario12() {
		indentDepth++;
		Game g = new Game("g1");
		


		indentDepth++;
		Asteroid a1 = new Asteroid("a1", 1, null, null);


		indentDepth++;
		Settler s1 = new Settler("s1" , g, a1);

		indentDepth++;
		a1.AcceptEntity(s1);

		s1.BlownUp();
		
		
	}
	private void scenario13(){//Build robot with resource
		indentDepth++;
		Asteroid a = new Asteroid("a", 0, null, null);
		indentDepth++;
		Settler s1 = new Settler("s1", null, a);
		indentDepth++;
		Iron i1 = new Iron("i");
		indentDepth++;
		Uran u1= new Uran("u");
		indentDepth++;
		Coal c1 = new Coal("c");
		indentDepth++;
		s1.AddResource(i1);
		indentDepth++;
		s1.AddResource(u1);
		indentDepth++;
		s1.AddResource(c1);
		indentDepth++;
		s1.BuildRobot();
	}

	private void scenario14(){//Build robot without resource
		indentDepth++;
		Asteroid a = new Asteroid("a", 0, null, null);
		indentDepth++;
		Settler s1 = new Settler("s1", null, a);
		indentDepth++;
		Iron i1 = new Iron("i");
		indentDepth++;
		Uran u1= new Uran("u");
		indentDepth++;
		s1.AddResource(i1);
		indentDepth++;
		s1.AddResource(u1);
		indentDepth++;
		s1.BuildRobot();
	}

	// Build teleport with enough resource
	private void scenario15(){
		indentDepth++;
		Game g = new Game("g");
		indentDepth++;
		Map m = new Map("m", g, 0);
		indentDepth++;
		Asteroid a = new Asteroid("a", 0, m, null);
		indentDepth++;
		Settler s1 = new Settler("s1", g, a);
		indentDepth++;
		Iron i1 = new Iron("i");
		indentDepth++;
		Iron i2 = new Iron("i");
		indentDepth++;
		IceWater iw = new IceWater("iw");
		indentDepth++;
		Uran u = new Uran("u");
		indentDepth++;
		s1.AddResource(i1);
		indentDepth++;
		s1.AddResource(i2);
		indentDepth++;
		s1.AddResource(iw);
		indentDepth++;
		s1.AddResource(u);
		indentDepth++;
		s1.BuildTeleport();
	}

	// Build teleport without enough resource
	private void scenario16(){
		indentDepth++;
		Asteroid a = new Asteroid("a", 0, null, null);
		indentDepth++;
		Settler s1 = new Settler("s1", null, a);
		indentDepth++;
		Iron i = new Iron("i");
		indentDepth++;
		IceWater iw = new IceWater("iw");
		indentDepth++;
		s1.AddResource(i);
		indentDepth++;
		s1.AddResource(iw);
		indentDepth++;
		s1.BuildTeleport();
	}

	// Cannot build teleport
	private void scenario17() {
		indentDepth++;
		Asteroid a = new Asteroid("a", 0, null, null);
		indentDepth++;
		Settler s1 = new Settler("s1", null, a);
		indentDepth++;
		TeleportGate tg = new TeleportGate("tg", 0, null);
		indentDepth++;
		s1.AddTeleportGate(tg);
		indentDepth++;
		s1.BuildTeleport();
	}
	
	
	
	//settler died game over
	private void scenario18() {
		indentDepth++;
		Game g = new Game("g1");

		indentDepth++;
		Coal c1 = new Coal("c");
		indentDepth++;
		Asteroid a1 = new Asteroid("a1", 10, null, c1);
		indentDepth++;
		Settler s1 = new Settler("s1,",g,a1);
		 

		indentDepth++;
		g.AddSettler(s1);

		indentDepth++;
		s1.Die();
	}
	
	
	//settler died no game over

	private void scenario19() {
		indentDepth++;
		Game g = new Game("g1");

		indentDepth++;
		Coal c1 = new Coal("c");
		indentDepth++;
		Asteroid a1 = new Asteroid("a1", 10, null, c1);
		indentDepth++;
		Settler s1 = new Settler("s1,",g,a1);
		indentDepth++;
		
		indentDepth++;
		Asteroid a2 = new Asteroid("a1", 11, null, null);
		indentDepth++;
		Settler s2 = new Settler("s2,",g,a2);
		

		indentDepth++;
		g.AddSettler(s1);
		indentDepth++;
		g.AddSettler(s2);

		indentDepth++;
		s1.Die();
	}

	// Settler action vagy itt csak a drill-t kéne meghívni?
	private void scenario20(){
		indentDepth++;
		Game g = new Game("g");
		indentDepth++;
		Asteroid a = new Asteroid("a", 1, null, null);
		indentDepth++;
		Settler s = new Settler("s", g, a);
		indentDepth++;
		//Asteroid.Action() 3-as = nyersanyag lehelyezés nem működik
		s.Action();
	}

	//settler hit by storm, asteroid with resource
	private void scenario21() {
		indentDepth++;
		Game g = new Game("g1");
		indentDepth++;
		Map m = new Map("map", g);
		indentDepth++;
		Iron i = new Iron("i");
		indentDepth++;
		Asteroid a1 = new Asteroid("a1", 10, m, i);
		indentDepth++;
		a1.SetLayers(0);
		indentDepth++;
		
		indentDepth++;
		Settler s1 = new Settler("s1",g, a1 );
		
		m.AddPlace(a1);
		indentDepth++;
		m.SolarStorm();
	}
	
	//settler hit by storm, teleportgate
	private void scenario22() {
		indentDepth++;
		Game g = new Game("g1");
		indentDepth++;
		Map m = new Map("map", g);
		
		indentDepth++;
		TeleportGate t1 = new TeleportGate("t1", 10, m);
		
		indentDepth++;
		Settler s1 = new Settler("s1",g, t1 );
		
		indentDepth++;
		m.AddPlace(t1);
		
		indentDepth++;
		m.SolarStorm();
	}
	private void scenario23() { // Settler Place Resource not empty
		Iron iron = new Iron("i");
		Asteroid asteroid = new Asteroid("Asteroid", 1, null, iron);
		indentDepth++;
		Settler s = new Settler("s1", null, asteroid);
		indentDepth++;
		s.Action();
	}
	private void scenario24() { // Settler Place Resource not empty
		indentDepth++;
		Asteroid asteroid = new Asteroid("Asteroid", 1, null, null);
		indentDepth++;
		Settler s = new Settler("Telepes", null, asteroid);
		indentDepth++;
		s.Action();
	}

	private void scenario25() { // Settler Moves
		indentDepth++;
		Game game = new Game("Game");
		indentDepth++;
		Asteroid asteroid1 = new Asteroid("Asteroid1", 1, null, null);
		indentDepth++;
		Asteroid asteroid2 = new Asteroid("Asteroid2", 2, null, null);
		indentDepth++;
		asteroid1.AddNeighbor(asteroid2);
		indentDepth++;
		asteroid2.AddNeighbor(asteroid1);
		indentDepth++;
		Settler settler = new Settler("Telepes", game, asteroid1);
		indentDepth++;
		asteroid1.AcceptEntity(settler);
		indentDepth++;
		settler.Move(2);
	}

	private void scenario26() { // Settler Place Resource not empty
		Iron iron = new Iron("i");
		Asteroid asteroid = new Asteroid("Asteroid", 1, null, iron);
		Settler settler = new Settler("Telepes", null, asteroid);
		settler.PlaceResource(10);
	}

	private void scenario27() { // Settler place Resource
		Uran u = new Uran("u");
		Asteroid a = new Asteroid("Asteroid", 1, null, null);
		Settler settler = new Settler("Telepes", null, a);
		settler.PlaceResource(13);
	}

	// Place down teleportgate
	private void scenario28(){
		indentDepth++;
		Asteroid a = new Asteroid("a", 0, null, null);
		indentDepth++;
		Settler s = new Settler("s", null, a);
		indentDepth++;
		TeleportGate tg1 = new TeleportGate("tg1", 1, null);
		indentDepth++;
		TeleportGate tg2 = new TeleportGate("tg2", 2, null);
		indentDepth++;
		tg1.SetPair(tg2);
		indentDepth++;
		tg2.SetPair(tg1);
		indentDepth++;
		s.AddTeleportGate(tg1);
		indentDepth++;
		s.PlaceDownTeleport();
	}

	// use teleport
	private void scenario29(){
		indentDepth++;
		TeleportGate tg1 = new TeleportGate("tg1", 0, null);
		indentDepth++;
		TeleportGate tg2 = new TeleportGate("tg2", 1, null);
		indentDepth++;
		tg1.SetPair(tg2);
		indentDepth++;
		tg2.SetPair(tg1);
		indentDepth++;
		tg1.SetPairIsPlaced();
		indentDepth++;
		tg2.SetPairIsPlaced();
		indentDepth++;
		Settler s = new Settler("s", null, tg1);
		indentDepth++;
		s.Action();
	}

}
