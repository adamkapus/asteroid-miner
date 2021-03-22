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
                Szkeleton.indentDepth++;
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

        Szkeleton.indentDepth++;
        resources.add(a.MinedBy(this));

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

        if(req.isEmpty()){
            Szkeleton.indentDepth++;
            Robot r = new Robot("r1", game, place);
        }

        Szkeleton.indentDepth--;
    }
    public void UseTeleport() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".UseTeleport()");

        Szkeleton.indentDepth++;
        TeleportGate destination = ((TeleportGate) place).GetPair();
        Szkeleton.indentDepth++;
        place.RemoveEntity(this);
        Szkeleton.indentDepth++;
        destination.AcceptEntity(this);

        Szkeleton.indentDepth--;
    }
    public void BuildTeleport() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".BuildTeleport()");
        if (gates.size() != 0) {
            Szkeleton.indentDepth--;
            return;
        }

        ArrayList<Integer> req = new ArrayList<>();
        req.add(11);     //vízjég
        req.add(12);     //vas
        req.add(12);     //vas
        req.add(13);     //urán

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

        if(req.isEmpty()){
            Szkeleton.indentDepth++;
            Map m = this.game.GetMap();

            Szkeleton.indentDepth++;
            TeleportGate gate1 = new TeleportGate("tg1", 1, m);
            Szkeleton.indentDepth++;
            TeleportGate gate2 = new TeleportGate("tg2", 2, m);

            Szkeleton.indentDepth++;
            gate1.SetPair(gate2);
            Szkeleton.indentDepth++;
            gate2.SetPair(gate1);
        }

        Szkeleton.indentDepth--;
    }

    public void PlaceDownTeleport() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".PlaceDownTeleport()");

        Szkeleton.indentDepth++;
        TeleportGate placeable = gates.get(gates.size() - 1);
        place.AddNeighbor(placeable);

        Szkeleton.indentDepth--;
    }
    public void RemoveResource(Resource r) {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".RemoveResource()");
        resources.remove(r);
        Szkeleton.indentDepth--;
    }
    public void Step() {
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Step()");
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
                    Szkeleton.indentDepth++;
                    this.Move(id);
                    break;
                case 2:
                    Szkeleton.indentDepth++;
                    place.Action(this);
                    break;
                case 3:
                    Szkeleton.indentDepth++;
                    this.BuildRobot();
                    break;
                case 4:
                    Szkeleton.indentDepth++;
                    this.BuildTeleport();
                    break;
                case 5:
                    Szkeleton.indentDepth++;
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
