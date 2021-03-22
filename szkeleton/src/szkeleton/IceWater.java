package szkeleton;

import java.util.ArrayList;

public class IceWater extends Resource{

    public IceWater(String name){
        super(name);
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".IceWater()");
        Szkeleton.indentDepth--;
    }

    public ArrayList<Integer> RemoveFromList(ArrayList<Integer> I,Settler s){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".RemoveFromList()");
        ArrayList<Integer> newList = new ArrayList<>();
        boolean found = false;
        for (Integer i : I){
            if (found)
                newList.add(i);
            else if (i == 11){
                found = true;
                Szkeleton.indentDepth++;
                s.RemoveResource(this);
            }
            else
                newList.add(i);
        }
        Szkeleton.indentDepth--;
        return newList;
    }
    public ArrayList<Integer> AddToList(ArrayList<Integer> I){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".AddToList()");
        Szkeleton.indentDepth--;
        I.add(11);
        return I;
    }
    public void Sublimation(Asteroid a){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Sublimation()");
        Szkeleton.indentDepth++;
        a.RemoveResource();
        Szkeleton.indentDepth--;
    }
}