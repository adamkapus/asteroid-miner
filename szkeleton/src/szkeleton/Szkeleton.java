package szkeleton;



import java.util.Scanner;

//coal -10
//icewater -11
//iron -12
//uran -13



public class Szkeleton {
	
	private int numberOfScenarios = 40; //pl.
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
				+ "1 -- Settler move scenario\n"
				+ "2 -- Robot move scenario\n"
				+ "3 -- stb\n"
				+ "12 -- Settler blown up\n"
				+ "25 -- Settler move scenario\n"
				+ "5 -- New game\n"
				+ "7 -- Robot blown up\n"
				+ "12 -- Settler blown up\n");
				
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
		switch(seq) {
		  	case 1:
		    	scenario1();
		    	break;
		  	case 2:
		    	scenario2();
		    	break;
		  	case 3:
				scenario2();
				break;
			case 5:
				scenario5();
				break;
			case 7:
				scenario7();
				break;
			case 11:
				scenario11(); break;
			case 12:
				scenario12();
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
			case 28:
				scenario28();
				break;
			case 25:
				scenario25(); break;
			case 29:
				scenario29();
				break;
		}

	}
	
	public static void writeTabs(int tabs) {
		for (int i = 0; i < tabs; i++)
		System.out.print("\t");
	}
	
	private void scenario1() {
		boolean test = askQuestionFromUser(keregKerdes);
		if(test) {
			System.out.println("Igent valaszolt");
		}
		else {
			System.out.println("Nemet valaszolt");
		}
	}
	
	private void scenario2() {
		
	}
	
	private void scenario3() {
		
	}

	//New game
	private void scenario5(){

		indentDepth++;
		Game g = new Game("g1");

		indentDepth++;
		g.NewGame();
		// map.Connect-ből probléma:
		// Cannot read field "placeID" because the return value of "szkeleton.Place.GetNeighbor(int)" is null
		// A Game NewGame() fv-e még nincs kész, ebből jonne az egész scenario
		// ...

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
	
	// robot hit by storm, asteroid with resource
	private void scenario9() {
		
	}
	
	//robot hit by storm, teleportgate
	private void scenario10() {
		
	}

	private void scenario11() { // Robot Moves
		Game game = new Game("Game");
		Asteroid asteroid1 = new Asteroid("Asteroid1", 1, null, null);
		Asteroid asteroid2 = new Asteroid("Asteroid2", 2, null, null);
		Robot robot = new Robot("Robot", game, asteroid1);
		asteroid1.AcceptEntity(robot);
		robot.Move(1);
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

	private void scenario15(){
		indentDepth++;
		Asteroid a = new Asteroid("a", 0, null, null);
		indentDepth++;
		Settler s1 = new Settler("s1", null, a);
		indentDepth++;
		Iron i1 = new Iron();
		indentDepth++;
		Iron i2 = new Iron();
		indentDepth++;
		IceWater iw = new IceWater();
		indentDepth++;
		Uran u = new Uran();
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

	private void scenario16(){
		indentDepth++;
		Asteroid a = new Asteroid("a", 0, null, null);
		indentDepth++;
		Settler s1 = new Settler("s1", null, a);
		indentDepth++;
		Iron i = new Iron();
		indentDepth++;
		IceWater iw = new IceWater();
		indentDepth++;
		s1.AddResource(i);
		indentDepth++;
		s1.AddResource(iw);
		indentDepth++;
		s1.BuildTeleport();
	}

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
		Coal c1 = new Coal();
		indentDepth++;
		Asteroid a1 = new Asteroid("a1", 10, null, c1);
		indentDepth++;
		Settler s1 = new Settler("s1,",g,a1);
		indentDepth++;
		a1.AcceptEntity(s1); // VAGY KONSTRUKTORBAN KENE A SETTLERNEK


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
		Coal c1 = new Coal();
		indentDepth++;
		Asteroid a1 = new Asteroid("a1", 10, null, c1);
		indentDepth++;
		Settler s1 = new Settler("s1,",g,a1);
		indentDepth++;
		a1.AcceptEntity(s1); // VAGY KONSTRUKTORBAN KENE A SETTLERNEK

		indentDepth++;
		Asteroid a2 = new Asteroid("a1", 11, null, null);
		indentDepth++;
		Settler s2 = new Settler("s2,",g,a2);
		indentDepth++;
		a2.AcceptEntity(s2); //ITT IS, MINT FELJEBB

		indentDepth++;
		g.AddSettler(s1);
		indentDepth++;
		g.AddSettler(s2);

		indentDepth++;
		s1.Die();
	}
	
	//settler hit by storm, asteroid with resource
	private void scenario21() {
		
	}
	
	//settler hit by storm, teleportgate
	private void scenario22() {
	
	}
		

	private void scenario25() {
		Game game = new Game("Game");
		Asteroid asteroid1 = new Asteroid("Asteroid1", 1, null, null);
		Asteroid asteroid2 = new Asteroid("Asteroid2", 2, null, null);
		Settler settler = new Settler("Telepes", game, asteroid1);
		asteroid1.AcceptEntity(settler);
		settler.Move(1);
	}

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
