package szkeleton;

public class Coal extends Resource{

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
