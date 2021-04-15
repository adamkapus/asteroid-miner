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

    /*
     * 
     * VisszatĂ©r az anyagok radioaktivitĂˇsĂˇval.
     */
    public boolean IsRadioactive(){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".IsRadioactive()");
        Szkeleton.indentDepth--;
        return this.radioactive;
    }

    /**
     * EltĂˇvolĂ­t egy Nyersanyagot a kapott settler listĂˇjĂˇbĂłl
     */
    public ArrayList<Integer> RemoveFromList(ArrayList<Integer> I, Settler s){
        return I;}
    public ArrayList<Integer> AddToList(ArrayList<Integer> I){
        return I;
    }

    /**
     * SzublimĂˇciĂł
     */
    public void Sublimation(Asteroid a){
        Szkeleton.indentDepth--;
        return;}

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
