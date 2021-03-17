package szkeleton;

public abstract class Entity /*implements Steppable*/ {
    //private Place place;

    public void Move(int placeID) {}
    public void Action(){}
    public void Drill(){}
    public int[] UpdateResourceList(int[] l) { return l; }


    public abstract void Die();
    public abstract void BlownUp();
}
