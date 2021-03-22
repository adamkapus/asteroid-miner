package szkeleton;

import java.util.ArrayList;

/**
 * Nyersanyagok Ĺ�sosztĂˇlya
 */

abstract public class Resource {
    /**
     * AlapbĂłl a nyersanyagok nem radioaktĂ­vak
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
}
