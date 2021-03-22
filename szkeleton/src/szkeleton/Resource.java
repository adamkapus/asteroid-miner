package szkeleton;

import java.util.ArrayList;

abstract public class Resource {
    protected boolean radioactive= false;
    protected String name;

    public Resource(String name){
        this.name = name;
    }

    public boolean IsRadioactive(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".IsRadioactive()");
        return this.radioactive;
    }
    abstract public ArrayList<Integer> RemoveFromList(ArrayList<Integer> I, Settler s);
    abstract public ArrayList<Integer> AddToList(ArrayList<Integer> I);
    public void Sublimation(Asteroid a){
        Szkeleton.indentDepth--;
        return;
    }
}
