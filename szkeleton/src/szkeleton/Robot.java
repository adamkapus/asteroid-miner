package szkeleton;

import java.util.Random;
import java.util.Scanner;

public class Robot extends Entity{
	
	public Robot(String name, Game g, Place p) {
    	super(name, g, p);
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
    	System.out.println(name +".Robot()");
    	
    	Szkeleton.indentDepth--;
    }

    public void Action(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Action()");
        Szkeleton.indentDepth++;
	    place.Action(this);
	    Szkeleton.indentDepth--;
    }
	
    public void Die() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Die()");
        Szkeleton.indentDepth++;
	    game.RobotDied(this);
	    Szkeleton.indentDepth--;
    }

    public void BlownUp() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".BlownUp()");

        Szkeleton.indentDepth++;
        Place destination = place.GetRandomNeighbor();
        place.RemoveEntity(this);
        Szkeleton.indentDepth++;
        destination.AcceptEntity(this);

        Szkeleton.indentDepth--;
    }

    public void Step() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Step()");
        Random rand1 = new Random();

        int rand_int = rand1.nextInt(2); // a random szám 0, vagy 1 lehet

        try {
            switch (rand_int) {
                case 0:
                    Szkeleton.indentDepth++;
                    this.Action();
                    break;
                case 1:
                    Szkeleton.indentDepth++;
                    this.Move(game.GetMap().GetAstNum());
                    break;
            }
        }catch (Exception e) {
            System.out.println("Nem jó számot adtál meg");
        }

        Szkeleton.indentDepth--;
    }
}
