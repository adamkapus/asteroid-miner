package szkeleton;

import java.util.ArrayList;
import java.util.Scanner;

public class Settler extends Entity{
    private ArrayList<TeleportGate> gates = new ArrayList<>();
    private ArrayList<Resource> resources = new ArrayList<>();

    @Override
    public ArrayList<Resource> UpdateResourceList(ArrayList<Resource> l){

        ArrayList<Resource> updated = new ArrayList<>();
        if(resources.size() > 0) {
            for (Resource r : resources) {
                updated.addAll(r.AddToList(l));
            }
        }

        return updated;
    }

    public void Mine() {
        Asteroid a = (Asteroid)place;
        a.MinedBy(this);
    }
    public void Die() {
        game.SettlerDied(this);
    }
    public void BlownUp() {
        this.Die();
    }

    //ide nem Resource-t kéne átadni? mert azt kéne az Insetrnek továbbadni
    public void PlaceResource(int n) {
        Asteroid a = (Asteroid) place;
        if(a.GetLayers() == 0 /*&& ((Asteroid)place).isEmpty*/){  //kéne az aszteroidának egy isEmpty változó
            //a.InsertResource(n);
        }
    }
    public void AddResource(Resource r) {
        resources.add(r);
    }
    public void BuildRobot() {
        for(Resource r : resources){
            r.RemoveFromList(resources, this);
        }
        if(resources.isEmpty()){    //miért kell ez a feltétel? (Build Robot szekvencia) ha nem fogy el az összes nyersanyag, miért ne lehetne építeni? (teleportkapunál is ugyanez
            Robot r = new Robot();
            place.AcceptEntity(r);
        }
    }
    public void UseTeleport() {
        TeleportGate destination = ((TeleportGate) place).GetPair();
        place.RemoveEntity(this);
        destination.AcceptEntity(this);
    }
    public void BuildTeleport() {
        for(Resource r : resources){
            r.RemoveFromList(resources, this);
        }
        if(resources.isEmpty()){

            // párban építjük, vagy sehogy?
            Map m = this.game.GetMap();
            TeleportGate gate1 = new TeleportGate(1, m);
            TeleportGate gate2 = new TeleportGate(2, m);

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
        System.out.println("1 -- Mozgás\n" + "2 -- Fúrás\n" + "3 -- Bányászás\n" + "4 -- Robot építés\n" + "5 -- Teleportkapu-pár építés\n" + "6 -- Teleportkapu lehelyezés\n" + "7 -- Nyersanyag lehelyezés aszteroidába");

        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();

        //itt mit kéne hívni? gondolom, nem közvetlen a Move(), Drill(), stb fv-eket
        switch (choice){
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                break;
            case "7":
                break;
        }
    }
}
