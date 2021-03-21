package szkeleton;

import java.util.ArrayList;

abstract public class Resource {
    protected boolean radioactive= false;

    public boolean IsRadioactive(){
        return this.radioactive;
    }
    public ArrayList<Integer> RemoveFromList(ArrayList<Integer> I, Settler s){
        return I;}
    public ArrayList<Integer> AddToList(ArrayList<Integer> I){
        return I;
    }

    // MB: a removeFromList és az addToList paraméterezése ArrayList<Integer> kéne hogy legyen. Különben nem fogunk tudni
    // dolgozni vele az aszteroidában meg a settlerben
    // ja meg ide is kell szublimálásos függvény ami itt nem csinál semmit, csak az icewater-ben
    // szublimálás
    public void Sublimation(Asteroid a){}

}
