package szkeleton;

import java.util.Random;
import java.util.Scanner;

public class Robot extends Entity{

    // konstruktor
	public Robot(String name, Game g, Place p) {
    	super(name, g, p);
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
    	System.out.println(name +".Robot()");
    	
    	Szkeleton.indentDepth--;
    }
    // műveletvégrehajtás
    public void Action(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Action()");
        Szkeleton.indentDepth++;
	    place.Action(this);
	    Szkeleton.indentDepth--;
	    
	    
    }
	// robot halála
    public void Die() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Die()");
        Szkeleton.indentDepth++;
	    game.RobotDied(this);
	    Szkeleton.indentDepth--;
    }
    // robotot aszteroidarobbanás éri
    public void BlownUp() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".BlownUp()");

        // random szomszédra átrepül
        Szkeleton.indentDepth++;
        Place destination = place.GetRandomNeighbor();
        Szkeleton.indentDepth++;
        place.RemoveEntity(this);
        Szkeleton.indentDepth++;
        destination.AcceptEntity(this);

        Szkeleton.indentDepth--;
    }
    // robot lép
    public void Step() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Step()");
        Random rand1 = new Random();

        // random vagy műveletet hajt végre vagy mozog
        int rand_int = rand1.nextInt(2); // a random szám 0, vagy 1 lehet

        try {
            switch (rand_int) {
                case 0:
                    Szkeleton.indentDepth++;
                    // műveletvégzés: aszteroidán fúrás, teleportkapun semmi
                    this.Action();
                    break;
                case 1:
                    Szkeleton.indentDepth++;
                    // mozgás
                    this.Move(game.GetMap().GetAstNum());
                    break;
            }
        }catch (Exception e) {
            System.out.println("Nem jó számot adtál meg");
        }

        Szkeleton.indentDepth--;
    }
}
