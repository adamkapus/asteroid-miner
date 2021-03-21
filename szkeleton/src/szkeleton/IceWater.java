package szkeleton;

import java.util.ArrayList;

public class IceWater extends Resource{


    public boolean IsRadioactive(){
        return this.radioactive;
    }

    public ArrayList<Integer> RemoveFromList(ArrayList<Integer> I, Settler s){
        return I;}
    public ArrayList<Integer> AddToList(ArrayList<Integer> I){
        return I;
    }
    public void Sublimation(Asteroid a){
        a.RemoveResource();
    }
}