package szkeleton;

import java.util.ArrayList;

/**
 * Nyersanyagok Ősosztálya
 */

abstract public class Resource {
    /**
     * Alapból a nyersanyagok nem radioaktívak
     */
    protected boolean radioactive= false;
    protected String name;

    public Resource(String name){
        this.name = name;
    }

    /**
     * Visszatér az anyagok radioaktivitásával.
     */
    public boolean IsRadioactive(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".IsRadioactive()");
        Szkeleton.indentDepth--;
        return this.radioactive;
    }

    /**
     * Eltávolít egy Nyersanyagot a kapott settler listájából
     */
    public ArrayList<Integer> RemoveFromList(ArrayList<Integer> I, Settler s){
        return I;}
    public ArrayList<Integer> AddToList(ArrayList<Integer> I){
        return I;
    }

    /**
     * Szublimáció
     */
    public void Sublimation(Asteroid a){
        Szkeleton.indentDepth--;
        return;}
}
