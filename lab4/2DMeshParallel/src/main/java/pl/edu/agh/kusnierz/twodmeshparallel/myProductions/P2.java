package pl.edu.agh.kusnierz.twodmeshparallel.myProductions;

import pl.edu.agh.kusnierz.twodmeshparallel.mesh.Vertex;
import pl.edu.agh.kusnierz.twodmeshparallel.production.AbstractProduction;
import pl.edu.agh.kusnierz.twodmeshparallel.production.PDrawer;

public class P2 extends AbstractProduction<Vertex> {

    public P2(Vertex _obj, PDrawer<Vertex> _drawer) {
        super(_obj, _drawer);
    }

    @Override
    public Vertex apply(Vertex t1) {
        System.out.println("p2");
        Vertex t2 = new Vertex(t1,null,t1.getEast(), null, "M");
        t1.setEast(t2);
        return t1;
    }
}
