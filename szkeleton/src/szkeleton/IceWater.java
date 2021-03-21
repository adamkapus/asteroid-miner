package szkeleton;

import java.util.ArrayList;

public class IceWater extends Resource{


    public boolean IsRadioactive(){
        return this.radioactive;
    }

    public ArrayList<Integer> RemoveFromList(ArrayList<Integer> I,Settler s){
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
        return newList;
    }
    public ArrayList<Integer> AddToList(ArrayList<Integer> I){
        I.add(11);
        return I;
    }
    public void Sublimation(Asteroid a){
        a.RemoveResource();
    }
}