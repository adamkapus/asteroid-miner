package szkeleton;

import java.util.ArrayList;
/**
 * Az Urán az egyik nyersanyag a játékban.
 */
public class Uran extends Resource{

    public Uran(String name){
        super(name);
        radioactive = true;
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Uran()");
        Szkeleton.indentDepth--;
    }

    /**
     * Megkapja a játékos listáját és megkeresi, hogy van-e rajta Urán.
     *Ha a kapott játékosnál talál Uránt azt leszedi a játékos nyersanyagai közül.
     * A függvény visszatér a settler listáján talált elemekkel (-1 Urán)
     */
    public ArrayList<Integer> RemoveFromList(ArrayList<Integer> I,Settler s){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".RemoveFromList()");
        ArrayList<Integer> newList = new ArrayList<>();
        boolean found = false;
        for (Integer i : I){
            if (found)
                newList.add(i);
            else if (i == 13){
                found = true;
                Szkeleton.indentDepth++;
                s.RemoveResource(this);
            }
            else
                newList.add(i);
        }
        Szkeleton.indentDepth--;
        return newList;
    }
    /**
     *Kapott listához hozzáadja az Szén sorszámát, ami a 13.
     */
    public ArrayList<Integer> AddToList(ArrayList<Integer> I){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".AddToList()");
        Szkeleton.indentDepth--;
        I.add(13);
        Szkeleton.indentDepth--;
        return I;
    }
}