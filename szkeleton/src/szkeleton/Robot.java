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
        place.Action(this);
    }
	
    public void Die() {
        game.RobotDied(this);
    }

    public void BlownUp() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".BlownUp()");

        Szkeleton.indentDepth++;
        Place destination = place.GetRandomNeighbor();
        place.RemoveEntity(this);
        destination.AcceptEntity(this);

        Szkeleton.indentDepth--;
    }

    public void Step() {
        Random rand = new Random();

        int rand_int = rand.nextInt(2); // a random szám 0, vagy 1 lehet

        switch (rand_int){
            case 0:
                this.Action();
                break;
            case 1:
                this.Drill();
                break;
            default:
                System.out.println("Nincs ilyen opció");
        }
    }
}
