package szkeleton;

import java.util.ArrayList;
/**
 * Az Urán az egyik nyersanyag a játékban.
 */
public class Uran extends Resource{
    private int CloseToSunTicks=3;

    public Uran(String name){
        super(name);
        radioactive = true;
    }

    /**
     * Megkapja a játékos listáját és megkeresi, hogy van-e rajta Urán.
     *Ha a kapott játékosnál talál Uránt azt leszedi a játékos nyersanyagai közül.
     * A függvény visszatér a settler listáján talált elemekkel (-1 Urán)
     */
    public ArrayList<Integer> RemoveFromList(ArrayList<Integer> I,Settler s){
        ArrayList<Integer> newList = new ArrayList<>();
        boolean found = false;
        for (Integer i : I){
            if (found)
                newList.add(i);
            else if (i == 13){
                found = true;
                s.RemoveResource(this);
            }
            else {
                newList.add(i);
                s.RemoveResource(this);
            }
        }
        return newList;
    }
    /**
     *Kapott listához hozzáadja az Szén sorszámát, ami a 13.
     */
    public ArrayList<Integer> AddToList(ArrayList<Integer> I){
        I.add(13);
        return I;
    }
    public void closeToSun(){CloseToSunTicks-=1;}
    public int getCloseToSunTicks(){
        return CloseToSunTicks;
    }
    public void setCloseToSunTicks(int i){
        CloseToSunTicks=i;
    }
    
    @Override
    public boolean IsRadioactive(){
        if(CloseToSunTicks <= 1) {
        	return this.radioactive;
        }
        //csokkentjuk eggyel
        closeToSun();
        return false;
    }
    
    
}