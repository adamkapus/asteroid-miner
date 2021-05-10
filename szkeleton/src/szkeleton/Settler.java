package szkeleton;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

public class Settler extends Entity{
    /**
     * telepesnél llévő teleportkapuk
     */
    private ArrayList<TeleportGate> gates = new ArrayList<>();

    /**
     * telepesnél lévő nyersanyagok
     */
    private ArrayList<Resource> resources = new ArrayList<>();
    private SettlerView settlerView;

    /**
     * Settler konstruktora
     */
    public Settler(String name, Game g, Place p) {
    	super(name, g, p);
    }

    public Settler(String name, Game g, Place p, SettlerView sv) {
        super(name, g, p);
        settlerView = sv;
        sv.updateSettler(this);
    }

	//inventory teszteléséhez
    public Settler(){
        Coal c = new Coal("c1");
        Iron i1 = new Iron("i1");
        Iron i2 = new Iron("i2");
        IceWater iw = new IceWater("iw");
        Uran u = new Uran("u1");
        resources.add(c);
        resources.add(i1);
        resources.add(i2);
        resources.add(iw);
        resources.add(u);
        TeleportGate tg = new TeleportGate();
        gates.add(tg);
    }

    /**
     * Settler műveletvégzés
     */
    public void Action() {
        place.Action(this);
    }

    /**
     * Kapott nyersanyaglista kiegészítése a nála lévő nyersanyagokkal
     */
    @Override
    public ArrayList<Integer> UpdateResourceList(ArrayList<Integer> l){
        // Lista bővítése a nyersanyag feladata
        if(resources.size() > 0) {
            for (Resource r : resources) {
                l = r.AddToList(l);
            }
        }
        return l;
    }

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
        game.finishedTurn();
    }

    /**
     * A settler halála
     */
    public void Die() {
        place.RemoveEntity(this);
        game.SettlerDied(this);
        settlerView.settlerDied(this);
        game.getFrame().repaint();
        System.out.println("settler died");
    }

    /**
     * Felrobbanás aszteroidarobbanás által
     */
    public void BlownUp() {
        this.Die();
    }

    /**
     * Nyersanyag lerakása az aszteroidába id szerint
     */
    public void PlaceResource(int n) {
        if (resources.size() <= n)
            return;
        Asteroid a = (Asteroid) place;
        // csak akkor rakhatjuk le, ha az aszteroida kérge 0
        if(a.GetLayers() == 0){
                //RemoveResource(resources.get(n));
                a.InsertResource(resources.get(n));
                RemoveResource(resources.get(n));
        }
        game.finishedTurn();
    }

    /**
     * nyersanyag átadása a telepesnek
     */
    public void AddResource(Resource r) {
        resources.add(r);
    }

    /**
     * robotépítés
     */
    public void BuildRobot(String nev) {
        // szükséges nyersanyagok
        ArrayList<Integer> req = new ArrayList<>();
        req.add(10);     //szén
        req.add(12);     //vas
        req.add(13);     //urán

        ArrayList<Resource> copy = new ArrayList<Resource>();
        for(int i = 0; i < resources.size(); i++) {
        	copy.add(resources.get(i));
        }
        
        for(int i = 0; i < copy.size(); i++) {
        	req = copy.get(i).RemoveFromList(req, this);
        }

        // Ha minden nyersanyag leszedte magát, létrehozzuk a robotot
        if(req.isEmpty()){
            Robot r = new Robot(nev, game, place, game.getFrame().getView().getRobotView());
            game.AddRobot(r);
            //proto.addRobot(r);
            if (nev == null) {
                r.name = "r1";
            }
        }
        game.finishedTurn();
    }

    /**
     * teleportálás
     */
    public void UseTeleport() {
        // célállomás
        TeleportGate destination = ((TeleportGate) place).GetPair();
        // jelenlegiről le
        place.RemoveEntity(this);
        // újra fel
        destination.AcceptEntity(this);
        place=destination;
        game.finishedTurn();
        settlerView.updateSettler(this);
    }

    /**
     * teleportkapu-építés
     */
    public void BuildTeleport(String nev1, String nev2) {
        // csak akkor építhetünk, ha nincs nálunk már teleportkapu
        if (gates.size() != 0) {
            return;
        }

        // szükséges nyersanyagok
        ArrayList<Integer> req = new ArrayList<>();
        req.add(11);     //vízjég
        req.add(12);     //vas
        req.add(12);     //vas
        req.add(13);     //urán

        ArrayList<Resource> copy = new ArrayList<Resource>();
        for(int i = 0; i < resources.size(); i++) {
        	copy.add(resources.get(i));
        }
        
        for(int i = 0; i < copy.size(); i++) {
        	req = copy.get(i).RemoveFromList(req, this);
        }

        // ha megvan minden nyersanyag, építhetünk
        if(req.isEmpty()){
            Map m = this.game.GetMap();
            Random ran = new Random();
            TeleportGate gate1 = new TeleportGate(nev1, ran.nextInt(20000), m, game.getFrame().getView().getTeleportView());
            TeleportGate gate2 = new TeleportGate(nev2, ran.nextInt(20000), m, game.getFrame().getView().getTeleportView());
            //proto.addTeleportgate(gate1);
            //proto.addTeleportgate(gate2);

            // csak párban tudunk kaput építeni
            if (nev1 == null) {
                gate1.name = "tg1";
            }
            if (nev2 == null) {
                gate2.name = "tg2";
            }

            // a létrehozott kapuk egymás párjai
            gate1.SetPair(gate2);
            gate2.SetPair(gate1);
            
            gates.add(gate1);
            gates.add(gate2);
            
        }
        game.finishedTurn();
    }

    /**
     * teleportkapu lerakása
     */
    public void PlaceDownTeleport() {
        // a teleportkapu szomszédja a place-nek és a place szomszédja a teleportkapunak
    	if(gates.size() ==0) {
    		 System.out.println("Nincs teleportkapu lehelyezesre!");
    	}
    	else {
	        //TeleportGate placeable = gates.get(gates.size() - 1);
    		TeleportGate placeable = gates.get(0);
    		gates.remove(0);
	        place.AddNeighbor(placeable);
	        placeable.AddNeighbor(place);
	        game.GetMap().AddPlace(placeable);
    	}
    	game.finishedTurn();
    }

    /**
     * nyersanyag leszedése a telepesről
     */
    public void RemoveResource(Resource r) {
        resources.remove(r);
    }

    /**
     * telepes köre
     */
    public void Step() {
        System.out.println("1 -- Mozgás\n"
                + "2 -- Interakció az aszteroidával\n"
                + "3 -- Robot építés\n"
                + "4 -- Teleportkapu-pár építés\n"
                + "5 -- Teleportkapu lehelyezés");

        // felhasználótól megkérdezzük, hogy mit szeretne csinálni
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
                    // mozgás
                    this.Move(id);
                    break;
                case 2:
                    // műveletvégzés (akció)
                    this.Action();
                    break;
                case 3:
                    // robotépítés
                    this.BuildRobot("robot");
                    break;
                case 4:
                    // teleportépítés
                    this.BuildTeleport("tg1", "tg2");
                    break;
                case 5:
                    // teleport lerakás
                    this.PlaceDownTeleport();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Nem jó számot adtál meg");
        }
    }

    /**
     * teleportkapu telepeshez adása
     */
    public void AddTeleportGate(TeleportGate tg){
        gates.add(tg);
    }

    public void SetGates(TeleportGate newGate) {gates.add(newGate);}
    public void SetResource(Resource newRes) {resources.add(newRes);}
    public ArrayList<TeleportGate> GetGates() {return gates;}
    public ArrayList<Resource> GetResources() {return resources;}
    public SettlerView GetSettlerView() { return settlerView;}

    /**
     * Szomszédos aszteroidára áthelyezi az entity-t.
     */
    @Override
    public void Move(int asteroidID) {
        Place neighbour = place.GetNeighbor(asteroidID);
        if (neighbour != null) {
            place.RemoveEntity(this);
            neighbour.AcceptEntity(this);
            place = neighbour;
            settlerView.updateSettler(this);
        }
        game.finishedTurn();
    }

    @Override
    public void Drill() {
        super.Drill();
        game.finishedTurn();
    }
}
