package szkeleton;

import java.util.ArrayList;

public class Coal extends Resource{

    public boolean IsRadioactive(){
        return this.radioactive;
    }

    public ArrayList<Resource> RemoveFromList(ArrayList<Resource> I, Settler s){
        I.remove(this);
        return I;}
    public ArrayList<Resource> AddToList(ArrayList<Resource> I){
        Coal coal= new Coal();
        I.add(coal);
        return I;
    }
}
