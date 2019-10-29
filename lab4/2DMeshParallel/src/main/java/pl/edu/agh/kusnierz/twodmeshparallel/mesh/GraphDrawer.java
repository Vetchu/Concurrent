package pl.edu.agh.kusnierz.twodmeshparallel.mesh;

import pl.edu.agh.kusnierz.twodmeshparallel.production.PDrawer;

import java.util.AbstractMap;

public class GraphDrawer implements PDrawer<Vertex> {

    @Override
    public void draw(Vertex v) {

        //go left
        while (v.mWest != null) {
            v = v.mWest;
        }
        //go top
        while (v.mNorth != null) {
            v = v.mNorth;
        }
        //plot
        Vertex v2 = v;

        do {
            v = v2;
            while (v.mEast != null) {
                System.out.print(v.mLabel + "--");
                v = v.mEast;
            }
            System.out.println(v.mLabel);
        } while ((v2 = v2.mSouth) != null);
    }
}
