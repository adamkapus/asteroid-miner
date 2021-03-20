package szkeleton;

import java.util.ArrayList;

abstract public class Resource {
    protected boolean radioactive= false;

    public boolean IsRadioactive(){
        return this.radioactive;
    }
    public ArrayList<Resource> RemoveFromList(ArrayList<Resource> I, Settler s){
        //ezt nem tudom megcsinálni
        //nem tudja a függvény azt se hogy mit akarunk építeni  és azt se milyen hosszú a lista
        //utóbbit mondjuk lehet azzal kezelni hogy a) megszámoljuk az elemket b) használunk valami I.hasNext() függvényt
        //gondolom kell egy while bele 4 if meg 4 bool változó, de nem térünk vissza semmivel hogy sikerült e mindent leszedni vagy sem

        return I;}
    public ArrayList<Resource> AddToList(ArrayList<Resource> I){
        return I;
    }

}
