package szkeleton;

import java.util.Random;

public class Ufo extends Entity{

    //név szerinti konstruktor a tesztesetekhez
    public Ufo(String name){
        super(name);
    }

    public void Action() {
        this.place.Action(this);
    }
    public void Die() {
        game.UfoDied(this);
    }
    public void BlownUp() {
        this.Die();
    }
    public void Step() {
        Random rand1 = new Random();

        // random vagy műveletet hajt végre vagy mozog
        int rand_int = rand1.nextInt(2); // a random szám 0, vagy 1 lehet

        try {
            switch (rand_int) {
                case 0:
                    // műveletvégzés: aszteroidán bányászás, teleportkapun semmi
                    this.Action();
                    break;
                case 1:
                    // mozgás
                    this.Move(game.GetMap().GetAstNum());
                    break;
            }
        }catch (Exception e) {
            System.out.println("Nem jó számot adtál meg");
        }
    }

    public void UseTeleport() {
    }

    public void Mine() {
    }

    public String ToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Ufo ");
        sb.append(name);
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
