package szkeleton;

import java.util.ArrayList;

public class Coal extends Resource{

    public boolean IsRadioactive(){
        return this.radioactive;
    }

    public ArrayList<Integer> RemoveFromList(ArrayList<Integer> I,Settler s){
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
        return I;}
    public ArrayList<Integer> AddToList(ArrayList<Integer> I){
        I.add(10);
        return I;
    }
}
