package szkeleton;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Settler extends Entity{
    // teleportkapu
    private ArrayList<TeleportGate> gates = new ArrayList<>();
    // nyersanyagok
    private ArrayList<Resource> resources = new ArrayList<>();

    //név szerinti konstruktor a tesztesetekhez
    public Settler(String name){
        super(name);
    }

    // Settler konstruktora
    public Settler(String name, Game g, Place p) {
    	super(name, g, p);
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
    	System.out.println(name +".Settler()");
    	
    	Szkeleton.indentDepth--;
    }
    // Settler műveletvégzés
    public void Action() {
        place.Action(this);

        Szkeleton.indentDepth--;
    }
    // Kapott nyersanyaglista kiegészítése a nála lévő nyersanyagokkal
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
    // Bányászás művelete
    public void Mine() {
        Asteroid a = (Asteroid)place;
        // Csak akkor bányászhatunk, ha kevesebb mint 10 nyersanyagunk van
        if (resources.size() < 10)
            resources.add(a.MinedBy(this));
    }
    // A settler halála
    public void Die() {
        game.SettlerDied(this);
    }
    // Felrobbanás aszteroidarobbanás által
    public void BlownUp() {
        this.Die();
    }
    // Nyersanyag lerakása az aszteroidába
    public void PlaceResource(int n) {
        Asteroid a = (Asteroid) place;
        // csak akkor rakhatjuk le, ha az aszteroida kérge 0
        if(a.GetLayers() == 0){
                a.InsertResource(resources.get(n));
        }
    }
    // nyersanyag átadása a telepesnek
    public void AddResource(Resource r) {
        resources.add(r);
    }
    // robotépítés
    public void BuildRobot() {
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
                req = rIter.next().RemoveFromList(req, this);
                if (req.size() == 1)
                    iterate = true;
                if (resources.size() == 0)
                    break;
            }
            catch (Exception e){
                rIter = resources.listIterator();
                if (req.size() == 1)
                    iterate = false;
            }
        }
        // Ha minden nyersanyag leszedte magát, létrehozzuk a robotot
        if(req.isEmpty()){
            Robot r = new Robot(name, game, place);
        }
    }
    // teleportálás
    public void UseTeleport() {
        // célállomás
        TeleportGate destination = ((TeleportGate) place).GetPair();
        // jelenlegiről le
        place.RemoveEntity(this);
        // újra fel
        destination.AcceptEntity(this);
    }
    // teleportkapu-építés
    public void BuildTeleport() {
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

        // minden nyersanyag leszedi magát a listáról
        ListIterator<Resource> rIter = resources.listIterator();
        boolean iterate = true;
        while (rIter.hasNext() || (iterate)){
            try {
                req = rIter.next().RemoveFromList(req, this);
                if (req.size() == 1)
                    iterate = true;
                if (resources.size() == 0)
                    break;
            }
            catch (Exception e){
                rIter = resources.listIterator();
                if (req.size() == 1)
                    iterate = false;
            }
        }
        // ha megvan minden nyersanyag, építhetünk
        if(req.isEmpty()){
            Map m = this.game.GetMap();

            // csak párban tudunk kaput építeni
            TeleportGate gate1 = new TeleportGate("tg1", 1, m);
            TeleportGate gate2 = new TeleportGate("tg2", 2, m);

            // a létrehozott kapuk egymás párjai
            gate1.SetPair(gate2);
            gate2.SetPair(gate1);
        }
    }

    // teleportkapu lerakása
    public void PlaceDownTeleport() {
        // a teleportkapu szomszédja a place-nek és a place szomszédja a teleportkapunak
        TeleportGate placeable = gates.get(gates.size() - 1);
        place.AddNeighbor(placeable);
        placeable.AddNeighbor(place);
    }
    // nyersanyag leszedése a telepesről
    public void RemoveResource(Resource r) {
        resources.remove(r);
    }
    // telepes köre
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
                    this.BuildRobot();
                    break;
                case 4:
                    // teleportépítés
                    this.BuildTeleport();
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
    // teleportkapu telepeshez adása
    public void AddTeleportGate(TeleportGate tg){
        gates.add(tg);
    }

    public void SetGates(TeleportGate newGate) {gates.add(newGate);}
    public void SetResource(Resource newRes) {resources.add(newRes);}
    public ArrayList<TeleportGate> GetGates() {return gates;}
    public ArrayList<Resource> GetResources() {return resources;}

    public String ToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Settler ");
        sb.append(name);
        sb.append("\n\tgame ");
        sb.append(game.getName());
        sb.append("\n\tgates ");
        for (TeleportGate tg : gates){
            sb.append(tg.GetName());
            sb.append(' ');
        }
        sb.append("\n\tplace ");
        sb.append(place.GetName());
        sb.append("\n\tresources ");
        for (Resource r : resources){
            sb.append(r.getName());
            sb.append(' ');
        }
        sb.append('\n');

        return sb.toString();
    }
}
