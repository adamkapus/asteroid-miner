package szkeleton;

import java.util.ArrayList;
/**
 * A Vas az egyik nyersanyag a játékban.
 */
public class Iron extends Resource{
    public Iron(String name){
        super(name);
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".Iron()");
        Szkeleton.indentDepth--;
    }
    public boolean IsRadioactive(){
        return this.radioactive;
    }
    /**
     * Megkapja a játékos listáját és megkeresi, hogy van-e rajta Vas.
     *Ha a kapott játékosnál talál Vasat azt leszedi a játékos nyersanyagai közül.
     * A függvény visszatér a settler listáján talált elemekkel (-1 Vas)
     */
    public ArrayList<Integer> RemoveFromList(ArrayList<Integer> I, Settler s){
        ArrayList<Integer> newList = new ArrayList<>();
        boolean found = false;
        for (Integer i : I){
            if (found)
                newList.add(i);
            else if (i == 12){
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
     *Kapott listához hozzáadja az Szén sorszámát, ami a 12.
     */
    public ArrayList<Integer> AddToList(ArrayList<Integer> I){
        Szkeleton.writeTabs(Szkeleton.indentDepth);
        System.out.println(name +".AddToList()");
        Szkeleton.indentDepth--;
        I.add(12);
        Szkeleton.indentDepth--;
        return I;
    }
}
