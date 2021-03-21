package szkeleton;

import java.util.ArrayList;

public class IceWater extends Resource{


    public boolean IsRadioactive(){
        return this.radioactive;
    }

    public ArrayList<Integer> RemoveFromList(ArrayList<Integer> I,Settler s){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println("RemoveFromList(IceWater)");
        ArrayList<Integer> newList = new ArrayList<>();
        boolean found = false;
        for (Integer i : I){
            if (found)
                newList.add(i);
            else if (i == 11){
                found = true;
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
        System.out.println("AddToList(IceWater)");
        I.add(11);
        Szkeleton.indentDepth--;
        return I;
    }
    public void Sublimation(Asteroid a){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println("IceWater.Sublimation()");
        a.RemoveResource();
        Szkeleton.indentDepth--;
        return;
    }
}