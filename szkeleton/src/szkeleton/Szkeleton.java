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
			case 12:
				scenario12();
				break;
		   
		}

	}
	
	public static void writeTabs(int tabs) {
		System.out.print(tabs);
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
	

}
