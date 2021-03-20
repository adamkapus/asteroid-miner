package szkeleton;

import java.util.ArrayList;

public class Iron extends Resource{

    public boolean IsRadioactive(){
        return this.radioactive;
    }

    public ArrayList<Resource> RemoveFromList(ArrayList<Resource> I, Settler s){
        I.remove(this);
        return I;}
    public ArrayList<Resource> AddToList(ArrayList<Resource> I){
        Iron iron= new Iron();
        I.add(iron);
        return I;
    }
}
