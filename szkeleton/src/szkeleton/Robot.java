package szkeleton;

import java.util.Scanner;

public class Robot extends Entity{

    public void Die() {
        game.RobotDied(this);
    }
    public void BlownUp() {
        //Itt csak Asteroid-ra mehetünk?
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
        switch (choice){
            case "1":
                break;
            case "2":
                break;
        }
    }
}
