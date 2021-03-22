package szkeleton;

import java.util.ArrayList;
/**
 * A Szén az egyik nyersanyag a játékban.
 */
public class Coal extends Resource{

    public Coal(String name){
        super(name);
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Coal()");
        Szkeleton.indentDepth--;
    }
    /**
     * Megkapja a játékos listáját és megkeresi, hogy van-e rajta szén.
     *Ha a kapott játékosnál talál szenet azt leszedi a játékos nyersanyagai közül.
     * A függvény visszatér a settler listáján talált elemekkel (-1 szén)
     */
    public ArrayList<Integer> RemoveFromList(ArrayList<Integer> I,Settler s){
    	
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println("RemoveFromList(Coal)");
        ArrayList<Integer> newList = new ArrayList<>();
        boolean found = false;
        for (Integer i : I){
            if (found)
                newList.add(i);
            else if (i == 10){
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
     *Kapott listához hozzáadja az Szén sorszámát, ami a 10.
     */
    public ArrayList<Integer> AddToList(ArrayList<Integer> I){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".AddToList()");
        Szkeleton.indentDepth--;
        I.add(10);
        return I;
    }
}
