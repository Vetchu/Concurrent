package pl.edu.agh.kusnierz.twodmeshparallel.myProductions;

import pl.edu.agh.kusnierz.twodmeshparallel.mesh.Vertex;
import pl.edu.agh.kusnierz.twodmeshparallel.production.AbstractProduction;
import pl.edu.agh.kusnierz.twodmeshparallel.production.PDrawer;

public class P1 extends AbstractProduction<Vertex> {

    public P1(Vertex _obj, PDrawer<Vertex> _drawer) {
        super(_obj, _drawer);
    }

    @Override
    public Vertex apply(Vertex s) {
        System.out.println("p1");
        return new Vertex(null,null,null, null, "M");
    }
}
