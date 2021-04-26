package szkeleton;

import java.util.ArrayList;
/**
 * A Vízjég az egyik nyersanyag a játékban.
 */
public class IceWater extends Resource{
    public IceWater(String name){
        super(name);
    }
    /**
     * Megkapja a játékos listáját és megkeresi, hogy van-e rajta Vízjég.
     *Ha a kapott játékosnál talál Vízjeget azt leszedi a játékos nyersanyagai közül.
     * A függvény visszatér a settler listáján talált elemekkel (-1 Vízjég)
     */
    public ArrayList<Integer> RemoveFromList(ArrayList<Integer> I,Settler s){
        ArrayList<Integer> newList = new ArrayList<>();
        boolean found = false;
        for (Integer i : I){
            if (found)
                newList.add(i);
            else if (i == 11){
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
     *Kapott listához hozzáadja az Szén sorszámát, ami a 11.
     */
    public ArrayList<Integer> AddToList(ArrayList<Integer> I){
        I.add(11);
        return I;
    }

    /**
     * Vízjég szublimálása
     */
    public void Sublimation(Asteroid a){
        a.RemoveResource();
        return;
    }
}