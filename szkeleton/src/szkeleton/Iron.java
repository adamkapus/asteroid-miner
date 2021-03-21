package szkeleton;

import java.util.ArrayList;

public class Iron extends Resource{

    public boolean IsRadioactive(){
        return this.radioactive;
    }

    public ArrayList<Integer> RemoveFromList(ArrayList<Integer> I, Settler s){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println("RemoveFromList(Iron)");
        ArrayList<Integer> newList = new ArrayList<>();
        boolean found = false;
        for (Integer i : I){
            if (found)
                newList.add(i);
            else if (i == 12){
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
        System.out.println("AddToList(Iron)");
        I.add(12);
        Szkeleton.indentDepth--;
        return I;
    }
}
