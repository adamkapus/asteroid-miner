package szkeleton;

import java.util.Random;
import java.util.Scanner;

public class Robot extends Entity{

    /**
     * Név szerinti konstruktor a tesztesetekhez
     */
    public Robot(String name){
        super(name);
    }

    /**
     * Konstruktor
     */
	public Robot(String name, Game g, Place p) {
    	super(name, g, p);
    }

    /**
     * Műveletvégrehajtás
     */
    public void Action(){
	    place.Action(this);
    }

    /**
     * Robot halála
     */
    public void Die() {
	    game.RobotDied(this);
    }

    /**
     * Robotot aszteroidarobbanás éri
     */
    public void BlownUp() {
        // random szomszédra átrepül
        Place destination = place.GetRandomNeighbor();
        place.RemoveEntity(this);
        destination.AcceptEntity(this);
        place = destination;
    }

    /**
     * Robot lép
     */
    public void Step() {
        Random rand1 = new Random();

        /**
         * random vagy műveletet hajt végre vagy mozog
         * a random szám 0, vagy 1 lehet
         */
        int rand_int = rand1.nextInt(2);

        try {
            switch (rand_int) {
                case 0:
                    /**
                     * műveletvégzés: aszteroidán fúrás, teleportkapun semmi
                     */
                    this.Action();
                    break;
                case 1:
                    /**
                     * mozgás
                     */
                    this.Move(game.GetMap().GetAstNum());
                    break;
            }
        }catch (Exception e) {
            System.out.println("Nem jó számot adtál meg");
        }
    }

    /**
     * Objektum string-gé alakítása a save parancshoz
     */
    public String ToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Robot ");
        sb.append(name);
        sb.append("\n\tgame ");
        if(game != null) {
        	sb.append(game.getName());
        }
        else {
        	sb.append("null");
        }
        sb.append("\n\tplace ");
        if(place != null) {
        	sb.append(place.GetName());
        }
        else {
        	sb.append("null");
        }
        sb.append('\n');

        return sb.toString();
    }
}
