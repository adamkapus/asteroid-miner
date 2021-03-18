package szkeleton;

abstract public class Resource {
    protected boolean radioactive;

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
