package szkeleton;

public class Uran extends Resource{
    protected boolean radioactive= true;

    public boolean IsRadioactive(){
        return this.radioactive;
    }
    public int[] RemoveFromList(int[] I, Settler s){
        return I;
    }
    public int[] AddToList(int[] I){
        return I;
    }
}