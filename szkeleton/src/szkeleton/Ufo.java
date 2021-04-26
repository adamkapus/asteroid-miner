package szkeleton;

import java.util.ArrayList;
import java.util.Random;

public class Ufo extends Entity{

    private ArrayList<Resource> resources = new ArrayList<>();
    private UfoView ufoView;
    /**
     * név szerinti konstruktor a tesztesetekhez
     */
    public Ufo(String name){
        super(name);
    }

    public Ufo(String name, UfoView uv){
        super(name);
        ufoView = uv;
    }
    /**
     * Ufo műveletvégzés
     */
    public void Action() {
        this.place.Action(this);
    }

    /**
     * Meghal az ufo
      */
    public void Die() {
        game.UfoDied(this);
    }

    /**
     * Az ufo felrobban
     */
    public void BlownUp() {
        this.Die();
    }

    /**
     * Ufo egy lépése
     */
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

    public void UseTeleport() { }

    /**
     * Bányászás művelete
     */
    public void Mine() {
        Asteroid a = (Asteroid)place;
        // Csak akkor bányászhatunk, ha kevesebb mint 10 nyersanyagunk van
        if (resources.size() < 10){
            Resource r=a.MinedBy(this);
            if(r!=null) resources.add(r);
        }
    }

}
