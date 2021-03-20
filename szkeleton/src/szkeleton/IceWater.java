package szkeleton;

import java.util.ArrayList;

public class IceWater extends Resource{


    public boolean IsRadioactive(){
        return this.radioactive;
    }

    public ArrayList<Resource> RemoveFromList(ArrayList<Resource> I, Settler s){
        I.remove(this);
        return I;}
    public ArrayList<Resource> AddToList(ArrayList<Resource> I){
        IceWater water= new IceWater();
        I.add(water);
        return I;
    }
    public void Sublimation(){}
}