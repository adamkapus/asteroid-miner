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
    	Szkeleton.writeTabs(Szkeleton.indentDepth);
    	System.out.println(name +".BlownUp()");
    	
    	Szkeleton.indentDepth++;
        this.Die();
        
        
        Szkeleton.indentDepth--;
    }

    //ide nem Resource-t kéne átadni? mert azt kéne az Insetrnek továbbadni
    public void PlaceResource(int n) {
        Asteroid a = (Asteroid) place;
        if(a.GetLayers() == 0 /*&& ((Asteroid)place).isEmpty*/){  //kéne az aszteroidának egy isEmpty változó
            //a.InsertResource(n);
        }
        // MB: nem kell isEmpty. Ha nem üres az aszteroida eldobódik a nyersanyag. Az int mondja meg hogy hányadik indexű
        // nyersanyagot tesszük bele az aszteroidába. De azt már itt megkapod paraméterül
    }
    public void AddResource(Resource r) {
        resources.add(r);
    }
    public void BuildRobot() {
        for(Resource r : resources){
            r.RemoveFromList(resources, this);
        }
        if(resources.isEmpty()){    //miért kell ez a feltétel? (Build Robot szekvencia) ha nem fogy el az összes nyersanyag, miért ne lehetne építeni? (teleportkapunál is ugyanez
            Robot r = new Robot("r1", game, place);
            place.AcceptEntity(r);
        }
        // MB: Itt csak létre kell hoznod a listát a szükséges nyersanyagtípusok id-jeivel. Minden nyersanyagra meghívod a removeos
        // függvényt, amiről azok vagy leszedik magukat vagy nem. Amikor ezzel végeztek, és ha a lista üres (tehát megvan minden anyag)
        // akkor építhatünk egyébként nem.
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

            // párban építjük, vagy sehogy? MB: aha
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
        System.out.println("1 -- Mozgás\n"
                            + "2 -- Fúrás\n"
                            + "3 -- Bányászás\n"
                            + "4 -- Robot építés\n"
                            + "5 -- Teleportkapu-pár építés\n"
                            + "6 -- Teleportkapu lehelyezés\n"
                            + "7 -- Nyersanyag lehelyezés aszteroidába");

        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();

        switch (choice){
            case "1":
                // vagy valami alapján el kéne dönteni, hova mozgunk (Robotban is)
                // MB: itt a felhasználó dönt, a robotban majd random
                // De egyébként itt nincs olyan hogy drill, mine mag placeresource. Azokat majd az actionben megmondja
                if(this.place.placeID == 1)
                    this.Move(2);
                else this.Move(1);
                break;
            case "2":
                this.Drill();
                break;
            case "3":
                this.Mine();
                break;
            case "4":
                this.BuildRobot();
                break;
            case "5":
                this.BuildTeleport();
                break;
            case "6":
                this.PlaceDownTeleport();
                break;
            case "7":
                //szintén valami alapáján el kéne dönteni, melyiket rakjuk le, kérhetünk egy számot
                // itt még nyilván kell kivételkezelés
                System.out.println("Melyik nyersanyagot?");
                Scanner input = new Scanner(System.in);
                String res = input.nextLine();
                this.PlaceResource(Integer.parseInt(res));
                break;
            default:
                System.out.println("Nincs ilyen opció");
        }
    }
}
