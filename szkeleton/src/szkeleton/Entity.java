package szkeleton;

import java.util.ArrayList;

public abstract class Entity implements Steppable {
    protected Place place;
    protected Game game;

    public void Move(int asteroidID) {
        Asteroid neighbour = (Asteroid) place.GetNeighbor(place.placeID);
        //Teleport kapura hogy mozgunk? plusz ha itt talaportra kattint -> crash

        place.RemoveEntity(this);
        neighbour.AcceptEntity(this);
    }

    public void Action(){
        //?
        place.Action((Settler)this);
        place.Action((Robot)this);
    }

    public void Drill(){
        Asteroid a = (Asteroid)place;
        a.ReduceRockLayer();
    }

    // innen a settler listája nem érhető el -> override, és ott is kell egy ilyen fv.
    // ha itt marad a List<>, akkor Asteroid: CheckResource-ban is az kéne
    public ArrayList<Resource> UpdateResourceList(ArrayList<Resource> l){return l;}


    public abstract void Die();
    public abstract void BlownUp();
}
