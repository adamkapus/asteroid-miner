package szkeleton;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Settler extends Entity{
    // teleportkapu
    private ArrayList<TeleportGate> gates = new ArrayList<>();
    // nyersanyagok
    private ArrayList<Resource> resources = new ArrayList<>();

    // Settler konstruktora
    public Settler(String name, Game g, Place p) {
    	super(name, g, p);
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
    	System.out.println(name +".Settler()");
    	
    	Szkeleton.indentDepth--;
    }
    // Settler műveletvégzés
    public void Action() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Action()");

        Szkeleton.indentDepth++;
        place.Action(this);

        Szkeleton.indentDepth--;
    }
    // Kapott nyersanyaglista kiegészítése a nála lévő nyersanyagokkal
    @Override
    public ArrayList<Integer> UpdateResourceList(ArrayList<Integer> l){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".UpdateResourceList()");

        // Lista bővítése a nyersanyag feladata
        if(resources.size() > 0) {
            for (Resource r : resources) {
                Szkeleton.indentDepth++;
                l = r.AddToList(l);
            }
        }

        Szkeleton.indentDepth--;
        return l;
    }
    // Bányászás művelete
    public void Mine() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Mine()");
        Asteroid a = (Asteroid)place;

        Szkeleton.indentDepth++;
        // Csak akkor bányászhatunk, ha kevesebb mint 10 nyersanyagunk van
        if (resources.size() < 10)
            resources.add(a.MinedBy(this));

        Szkeleton.indentDepth--;
    }
    // A settler halála
    public void Die() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Die()");

        Szkeleton.indentDepth++;
        game.SettlerDied(this);

        Szkeleton.indentDepth--;
    }
    // Felrobbanás aszteroidarobbanás által
    public void BlownUp() {
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
    	System.out.println(name +".BlownUp()");
    	
    	Szkeleton.indentDepth++;
        this.Die();
        
        Szkeleton.indentDepth--;
    }
    // Nyersanyag lerakása az aszteroidába
    public void PlaceResource(int n) {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".PlaceResource()");

        Szkeleton.indentDepth++;
        Asteroid a = (Asteroid) place;
        // csak akkor rakhatjuk le, ha az aszteroida kérge 0
        if(a.GetLayers() == 0){
            Szkeleton.indentDepth++;
            a.InsertResource(resources.get(n));
        }

        
        Szkeleton.indentDepth--;
    }
    // nyersanyag átadása a telepesnek
    public void AddResource(Resource r) {

        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".AddResource()");

        resources.add(r);

        Szkeleton.indentDepth--;
    }
    // robotépítés
    public void BuildRobot() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".BuildRobot()");

        // szükséges nyersanyagok
        ArrayList<Integer> req = new ArrayList<>();
        req.add(10);     //szén
        req.add(12);     //vas
        req.add(13);     //urán

        // minden nyersanyag leszedi saját magát a telepesről ha rajta van a listán
        ListIterator<Resource> rIter = resources.listIterator();
        boolean iterate = true;
        while (rIter.hasNext() || (iterate)){
            try {
                Szkeleton.indentDepth++;
                req = rIter.next().RemoveFromList(req, this);
                if (req.size() == 1)
                    iterate = true;
                if (resources.size() == 0)
                    break;
            }
            catch (Exception e){
                Szkeleton.indentDepth--;
                rIter = resources.listIterator();
                if (req.size() == 1)
                    iterate = false;
            }
        }
        // Ha minden nyersanyag leszedte magát, létrehozzuk a robotot
        if(req.isEmpty()){
            Szkeleton.indentDepth++;
            Robot r = new Robot("r1", game, place);
        }

        Szkeleton.indentDepth--;
    }
    // teleportálás
    public void UseTeleport() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".UseTeleport()");
        // célállomás
        Szkeleton.indentDepth++;
        TeleportGate destination = ((TeleportGate) place).GetPair();
        // jelenlegiről le
        Szkeleton.indentDepth++;
        place.RemoveEntity(this);
        // újra fel
        Szkeleton.indentDepth++;
        destination.AcceptEntity(this);

        Szkeleton.indentDepth--;
    }
    // teleportkapu-építés
    public void BuildTeleport() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".BuildTeleport()");
        // csak akkor építhetünk, ha nincs nálunk már teleportkapu
        if (gates.size() != 0) {
            Szkeleton.indentDepth--;
            return;
        }

        // szükséges nyersanyagok
        ArrayList<Integer> req = new ArrayList<>();
        req.add(11);     //vízjég
        req.add(12);     //vas
        req.add(12);     //vas
        req.add(13);     //urán

        // minden nyersanyag leszedi magát a listáról
        ListIterator<Resource> rIter = resources.listIterator();
        boolean iterate = true;
        while (rIter.hasNext() || (iterate)){
            try {
                Szkeleton.indentDepth++;
                req = rIter.next().RemoveFromList(req, this);
                if (req.size() == 1)
                    iterate = true;
                if (resources.size() == 0)
                    break;
            }
            catch (Exception e){
                Szkeleton.indentDepth--;
                rIter = resources.listIterator();
                if (req.size() == 1)
                    iterate = false;
            }
        }
        // ha megvan minden nyersanyag, építhetünk
        if(req.isEmpty()){
            Szkeleton.indentDepth++;
            Map m = this.game.GetMap();

            // csak párban tudunk kaput építeni
            Szkeleton.indentDepth++;
            TeleportGate gate1 = new TeleportGate("tg1", 1, m);
            Szkeleton.indentDepth++;
            TeleportGate gate2 = new TeleportGate("tg2", 2, m);

            // a létrehozott kapuk egymás párjai
            Szkeleton.indentDepth++;
            gate1.SetPair(gate2);
            Szkeleton.indentDepth++;
            gate2.SetPair(gate1);
        }

        Szkeleton.indentDepth--;
    }

    // teleportkapu lerakása
    public void PlaceDownTeleport() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".PlaceDownTeleport()");

        // a teleportkapu szomszédja a place-nek és a place szomszédja a teleportkapunak
        Szkeleton.indentDepth++;
        TeleportGate placeable = gates.get(gates.size() - 1);
        place.AddNeighbor(placeable);
        Szkeleton.indentDepth++;
        placeable.AddNeighbor(place);

        Szkeleton.indentDepth--;
    }
    // nyersanyag leszedése a telepesről
    public void RemoveResource(Resource r) {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".RemoveResource()");
        resources.remove(r);
        Szkeleton.indentDepth--;
    }
    // telepes köre
    public void Step() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Step()");
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
                    Szkeleton.indentDepth++;
                    // mozgás
                    this.Move(id);
                    break;
                case 2:
                    Szkeleton.indentDepth++;
                    // műveletvégzés (akció)
                    this.Action();
                    break;
                case 3:
                    Szkeleton.indentDepth++;
                    // robotépítés
                    this.BuildRobot();
                    break;
                case 4:
                    Szkeleton.indentDepth++;
                    // teleportépítés
                    this.BuildTeleport();
                    break;
                case 5:
                    Szkeleton.indentDepth++;
                    // teleport lerakás
                    this.PlaceDownTeleport();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Nem jó számot adtál meg");
        }
    }
    // teleportkapu telepeshez adása
    public void AddTeleportGate(TeleportGate tg){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".AddTeleportGate()");

        gates.add(tg);

        Szkeleton.indentDepth--;
    }
}
