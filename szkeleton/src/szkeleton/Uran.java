package szkeleton;

import java.util.ArrayList;

public class Uran extends Resource{
    protected boolean radioactive= true;

    public boolean IsRadioactive(){
        return this.radioactive;
    }

    public ArrayList<Resource> RemoveFromList(ArrayList<Resource> I, Settler s){
        I.remove(this);
        return I;}
    public ArrayList<Resource> AddToList(ArrayList<Resource> I){
        Uran uran= new Uran();
        I.add(uran);
        return I;
    }
}