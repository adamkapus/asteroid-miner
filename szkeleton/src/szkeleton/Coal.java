package szkeleton;

import java.util.ArrayList;
/**
 * A Szén az egyik nyersanyag a játékban.
 */
public class Coal extends Resource{

    public Coal(String name){
        super(name);
    }
    /**
     * Megkapja a játékos listáját és megkeresi, hogy van-e rajta szén.
     *Ha a kapott játékosnál talál szenet azt leszedi a játékos nyersanyagai közül.
     * A függvény visszatér a settler listáján talált elemekkel (-1 szén)
     */
    public ArrayList<Integer> RemoveFromList(ArrayList<Integer> I,Settler s){

        ArrayList<Integer> newList = new ArrayList<>();
        boolean found = false;
        for (Integer i : I){
            if (found)
                newList.add(i);
            else if (i == 10){
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
     *Kapott listához hozzáadja az Szén sorszámát, ami a 10.
     */
    public ArrayList<Integer> AddToList(ArrayList<Integer> I){
        I.add(10);
        return I;
    }
}
