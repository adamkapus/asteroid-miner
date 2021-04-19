package szkeleton;

import java.util.ArrayList;

/**
 * Nyersanyagok ősosztálya
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
     * 
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
     * Szublimáció (csak vízjég esetén)
     */
    public void Sublimation(Asteroid a){
        Szkeleton.indentDepth--;
        return;}

    /**
     * Setter és Getter fügvények
     */
    public String getName(){
       return name;
    }
    public void setName(String n){
        this.name=n;
    }

    public boolean getRacioactive(){
        return radioactive;
    }
    public void setRadioactive(boolean tf){
        radioactive=tf;
    }
}
