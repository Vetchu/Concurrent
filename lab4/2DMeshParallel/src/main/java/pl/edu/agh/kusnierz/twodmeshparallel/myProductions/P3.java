package pl.edu.agh.kusnierz.twodmeshparallel.myProductions;

import pl.edu.agh.kusnierz.twodmeshparallel.mesh.Vertex;
import pl.edu.agh.kusnierz.twodmeshparallel.production.AbstractProduction;
import pl.edu.agh.kusnierz.twodmeshparallel.production.PDrawer;

public class P3 extends AbstractProduction<Vertex> {

    public P3(Vertex _obj, PDrawer<Vertex> _drawer) {
        super(_obj, _drawer);
    }

    @Override
    public Vertex apply(Vertex t1) {
        System.out.println("p3");
        Vertex t2 = new Vertex(null,t1,null,null, "M");
        t1.setSouth(t2);
        return t2;
    }
}
