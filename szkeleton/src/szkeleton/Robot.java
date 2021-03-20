package szkeleton;

import java.util.Scanner;

public class Robot extends Entity{
	
	public Robot(String name, Game g, Place p) {
    	super(name, g, p);
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
    	System.out.println(name +".Robot()");
    	
    	Szkeleton.indentDepth--;

    }
	
    public void Die() {
        game.RobotDied(this);
    }
    public void BlownUp() {
        //Itt csak Asteroid-ra mehetünk?
        // MB: nem, azt nem tudjuk, hogy a random szomszéd aszteroida-e. Legyen csak Place
        Asteroid destination = (Asteroid)place.GetRandomNeighbor();
        place.RemoveEntity(this);
        destination.AcceptEntity(this);
    }

    public void Step() {
        //most itt is haszonló kéne, mint a Settlernél?

        System.out.println("1 -- Mozgás\n" + "2 -- Fúrás");

        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();

        //itt mit kéne hívni? gondolom, nem közvetlen a Move(), Drill(), stb fv-eket
        // MB: random vagy move vagy action(this)
        switch (choice){
            case "1":
                if(this.place.placeID == 1)
                    this.Move(2);
                else this.Move(1);
                break;
            case "2":
                this.Drill();
                break;
            default:
                System.out.println("Nincs ilyen opció");
        }
    }
}
