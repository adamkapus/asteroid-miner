package szkeleton;

import java.util.ArrayList;

public abstract class Entity implements Steppable {
    protected Place place;
    protected Game game;

    public void Move(int asteroidID) {
        Asteroid neighbour = (Asteroid) place.GetNeighbor(place.placeID);
        //Teleport kapura hogy mozgunk? plusz ha itt talaportra kattint -> crash
        // MB: ugyanúgy mint aszteroidára. Pont ezért jó a közös ősosztály. Nem is kell cast Asteroid-ra

        place.RemoveEntity(this);
        neighbour.AcceptEntity(this);
    }

    public void Action(){
        //?
        place.Action((Settler)this);
        place.Action((Robot)this);
        // MB: ide szerintem nem kell semmi, majd a Settler meg a Robot megcsinálja
    }

    public void Drill(){
        Asteroid a = (Asteroid)place;
        a.ReduceRockLayer();
    }

    // innen a settler listája nem érhető el -> override, és ott is kell egy ilyen fv.
    // ha itt marad a List<>, akkor Asteroid: CheckResource-ban is az kéne
    // MB: amúgy amikor a nyersanyagokat visszaadjuk az aszteroidának akkor inkább a nyersanyagok id-jét kéne visszaadni
    // nem a konkrét nyersanyagot. Ebből utána nem lehet megmondani a nyersanyag típust. Ugyanez vonatkozik egyébként a
    // nyersanyag osztlyra is. Tehát az ArrayList-nek inkább int-eket kéne tartalmaznia a típussal
    public ArrayList<Resource> UpdateResourceList(ArrayList<Resource> l){return l;}


    public abstract void Die();
    public abstract void BlownUp();
}
