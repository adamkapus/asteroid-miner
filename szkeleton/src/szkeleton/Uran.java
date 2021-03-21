package szkeleton;

import java.util.ArrayList;

public class Uran extends Resource{
    protected boolean radioactive= true;

    public boolean IsRadioactive(){
        return this.radioactive;
    }

    public ArrayList<Integer> RemoveFromList(ArrayList<Integer> I){
        ArrayList<Integer> newList = new ArrayList<>();
        boolean found = false;
        for (Integer i : I){
            if (found)
                newList.add(i);
            else if (i == 13){
                found = true;
            }
            else
                newList.add(i);
        }
        return I;}
    public ArrayList<Integer> AddToList(ArrayList<Integer> I){
        I.add(13);
        return I;
    }
}