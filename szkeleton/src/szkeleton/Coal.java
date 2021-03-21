package szkeleton;

import java.util.ArrayList;

public class Coal extends Resource{

    public boolean IsRadioactive(){
        return this.radioactive;
    }

    public ArrayList<Integer> RemoveFromList(ArrayList<Integer> I,Settler s){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println("RemoveFromList(Coal)");
        ArrayList<Integer> newList = new ArrayList<>();
        boolean found = false;
        for (Integer i : I){
            if (found)
                newList.add(i);
            else if (i == 10){
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
        System.out.println("AddToList(Coal)");
        I.add(10);
        Szkeleton.indentDepth--;
        return I;
    }
}
