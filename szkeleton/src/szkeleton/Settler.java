package szkeleton;

import java.util.ArrayList;
import java.util.Scanner;

public class Settler extends Entity{
    private ArrayList<TeleportGate> gates = new ArrayList<>();
    private ArrayList<Resource> resources = new ArrayList<>();
    
    public Settler(String name, Game g, Place p) {
    	super(name, g, p);
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
    	System.out.println(name +".Settler()");
    	
    	Szkeleton.indentDepth--;
    }

    public void Action() { place.Action(this); }

    @Override
    public ArrayList<Integer> UpdateResourceList(ArrayList<Integer> l){

        if(resources.size() > 0) {
            for (Resource r : resources) {
                //r.AddToList(l);
            }
        }

        return l;
    }

    public void Mine() {
        Asteroid a = (Asteroid)place;
        a.MinedBy(this);
    }

    public void Die() {

        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Die()");

        Szkeleton.indentDepth++;
        game.SettlerDied(this);

        Szkeleton.indentDepth--;
    }

    public void BlownUp() {
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
    	System.out.println(name +".BlownUp()");
    	
    	Szkeleton.indentDepth++;
        this.Die();
        
        Szkeleton.indentDepth--;
    }

    public void PlaceResource(int n) {
        Asteroid a = (Asteroid) place;
        if(a.GetLayers() == 0){
            a.InsertResource(resources.get(n));
        }
    }

    public void AddResource(Resource r) {
        resources.add(r);
    }

    public void BuildRobot() {
        /*
        for(Resource r : resources){
            r.RemoveFromList(resources, this);
        }
        if(resources.isEmpty()){    //miért kell ez a feltétel? (Build Robot szekvencia) ha nem fogy el az összes nyersanyag, miért ne lehetne építeni? (teleportkapunál is ugyanez
            Robot r = new Robot("r1", game, place);
            place.AcceptEntity(r);
        }
        */

        ArrayList<Integer> req = new ArrayList<>();
        req.add(10);     //szén
        req.add(12);     //vas
        req.add(13);     //urán

        for(Resource r : resources){
            // Resource-ban a RemoveFromList Integer listát kéne, várjon
            // r.RemoveFromList(req, this);      //a szükséges nyersanyagok csekkolása
            req.remove(r);                      //törlés az inventory-ból
        }

        if(req.isEmpty()){
            Robot r = new Robot("r1", game, place);
            place.AcceptEntity(r);
        }
    }
    public void UseTeleport() {
        TeleportGate destination = ((TeleportGate) place).GetPair();
        place.RemoveEntity(this);
        destination.AcceptEntity(this);
    }
    public void BuildTeleport() {
        // MB: amikor változtatjuk a listát nem használhatunk for_each-et. Az csak akkor megy ha nem válzotik a lista hossza!
        for(Resource r : resources){
            r.RemoveFromList(resources, this);
        }

        if(resources.isEmpty()){
            Map m = this.game.GetMap();
            TeleportGate gate1 = new TeleportGate(1, m);
            TeleportGate gate2 = new TeleportGate(2, m);
            TeleportGate gate1 = new TeleportGate("tg1",1, m);
            TeleportGate gate2 = new TeleportGate("tg2",2, m);
            TeleportGate gate1 = new TeleportGate("tg1", 1, m);
            TeleportGate gate2 = new TeleportGate("tg2", 2, m);

            gate1.SetPair(gate2);
            gate2.SetPair(gate1);
        }
    }
    public void PlaceDownTeleport() {
        TeleportGate placeable = gates.get(gates.size() - 1);
        place.AddNeighbor(placeable);
    }
    public void RemoveResource(Resource r) {
        resources.remove(r);
    }
    public void Step() {
        System.out.println("1 -- Mozgás\n"
                + "2 -- Interakció az aszteroidával\n"
                + "3 -- Robot építés\n"
                + "4 -- Teleportkapu-pár építés\n"
                + "5 -- Teleportkapu lehelyezés");

        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int choice = Integer.parseInt(str);
        try {
            switch (choice) {
                case 1:
                    System.out.println("Válassz aszteroidát!");
                    Scanner input = new Scanner(System.in);
                    String strid = in.nextLine();
                    int id = Integer.parseInt(strid);
                    this.Move(id);
                    break;
                case 2:
                    place.Action(this);
                    break;
                case 3:
                    this.BuildRobot();
                    break;
                case 4:
                    this.BuildTeleport();
                    break;
                case 5:
                    this.PlaceDownTeleport();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Nem jó számot adtál meg");
        }
    }
    public void AddTeleportGate(TeleportGate tg){
        gates.add(tg);
    }
}
