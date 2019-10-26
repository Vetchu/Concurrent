package pl.edu.agh.kusnierz.twodmeshparallel.mesh;

import pl.edu.agh.kusnierz.twodmeshparallel.production.PDrawer;

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
        do {
            while (v.mEast != null) {
                System.out.print(v.mLabel + "--");
                v = v.mEast;
            }
            System.out.println(v.mLabel);
        }while (v.mSouth!=null);

    }
}
