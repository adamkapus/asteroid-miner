package szkeleton;

import java.util.ArrayList;
import java.util.Random;

public class Ufo extends Entity{
    private ArrayList<Resource> resources = new ArrayList<>();
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
        Asteroid a = (Asteroid)place;
        // Csak akkor bányászhatunk, ha kevesebb mint 10 nyersanyagunk van
        if (resources.size() < 10){
            Resource r=a.MinedBy(this);
            if(r!=null) resources.add(r);
        }
    }

    public String ToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Ufo ");
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
        sb.append("\n\tresources ");
        if(resources.size() != 0) {
            for (Resource r : resources) {
                sb.append(r.getName());
                sb.append(' ');
            }
        } else sb.append(("null"));

        sb.append('\n');

        return sb.toString();
    }
}
