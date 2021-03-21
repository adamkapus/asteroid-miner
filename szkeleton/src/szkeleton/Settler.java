package szkeleton;

import java.util.ArrayList;
import java.util.ListIterator;
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

    public void Action() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Action()");

        Szkeleton.indentDepth++;
        place.Action(this);

        Szkeleton.indentDepth--;
    }

    @Override
    public ArrayList<Integer> UpdateResourceList(ArrayList<Integer> l){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".UpdateResourceList()");

        if(resources.size() > 0) {
            for (Resource r : resources) {
                r.AddToList(l);
            }
        }

        Szkeleton.indentDepth--;
        return l;
    }

    public void Mine() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Mine()");
        Asteroid a = (Asteroid)place;
        a.MinedBy(this);

        Szkeleton.indentDepth--;
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
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".PlaceResource()");

        Szkeleton.indentDepth++;
        Asteroid a = (Asteroid) place;
        if(a.GetLayers() == 0){
            Szkeleton.indentDepth++;
            a.InsertResource(resources.get(n));
        }

        Szkeleton.indentDepth--;
    }

    public void AddResource(Resource r) {

        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".AddResource()");

        Szkeleton.indentDepth++;
        resources.add(r);

        Szkeleton.indentDepth--;
    }

    public void BuildRobot() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".BuildRobot()");

        Szkeleton.indentDepth++;
        ArrayList<Integer> req = new ArrayList<>();
        req.add(10);     //szén
        req.add(12);     //vas
        req.add(13);     //urán

        ListIterator<Resource> rIter = resources.listIterator();
        while (rIter.hasNext() || req.size() == 1){
            try {
                req = rIter.next().RemoveFromList(req, this);
            }
            catch (Exception e){
                rIter = resources.listIterator();
            }
        }

        if(req.isEmpty()){
            Robot r = new Robot("r1", game, place);
            place.AcceptEntity(r);
        }

        Szkeleton.indentDepth--;
    }
    public void UseTeleport() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".UseTeleport()");

        Szkeleton.indentDepth++;
        TeleportGate destination = ((TeleportGate) place).GetPair();
        place.RemoveEntity(this);
        destination.AcceptEntity(this);

        Szkeleton.indentDepth--;
    }
    public void BuildTeleport() {
        if (gates.size() != 0)
            return;

        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".BuildTeleport()");

        Szkeleton.indentDepth++;
        ArrayList<Integer> req = new ArrayList<>();
        req.add(11);     //vízjég
        req.add(12);     //vas
        req.add(12);     //vas
        req.add(13);     //urán

        ListIterator<Resource> rIter = resources.listIterator();
        while (rIter.hasNext() || req.size() == 1){
            try {
                req = rIter.next().RemoveFromList(req, this);
            }
            catch (Exception e){
                rIter = resources.listIterator();
            }
        }

        if(resources.isEmpty()){
            Map m = this.game.GetMap();


            TeleportGate gate1 = new TeleportGate("tg1", 1, m);
            TeleportGate gate2 = new TeleportGate("tg2", 2, m);

            gate1.SetPair(gate2);
            gate2.SetPair(gate1);
        }

        Szkeleton.indentDepth--;
    }

    public void PlaceDownTeleport() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".BuildTeleport()");

        Szkeleton.indentDepth++;
        TeleportGate placeable = gates.get(gates.size() - 1);
        place.AddNeighbor(placeable);

        Szkeleton.indentDepth--;
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
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".AddTeleportGate()");

        Szkeleton.indentDepth++;
        gates.add(tg);

        Szkeleton.indentDepth--;
    }
}
