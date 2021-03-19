package szkeleton;

import java.util.ArrayList;

abstract public class Resource {
    protected boolean radioactive;

    public boolean IsRadioactive(){
        return this.radioactive;
    }
    public ArrayList<Resource> RemoveFromList(ArrayList<Resource> I, Settler s){
        return I;
    }
    public ArrayList<Resource> AddToList(ArrayList<Resource> I){
        return I;
    }

}
