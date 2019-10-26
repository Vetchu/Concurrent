package pl.edu.agh.kusnierz.twodmeshparallel.myProductions;

import pl.edu.agh.kusnierz.twodmeshparallel.mesh.Vertex;
import pl.edu.agh.kusnierz.twodmeshparallel.production.AbstractProduction;
import pl.edu.agh.kusnierz.twodmeshparallel.production.PDrawer;

public class P4 extends AbstractProduction<Vertex> {

    public P4(Vertex _obj, PDrawer<Vertex> _drawer) {
        super(_obj, _drawer);
    }

    @Override
    public Vertex apply(Vertex t1) {
        System.out.println("p4");
        Vertex t2 = new Vertex(null,null,null,null, "M");
        t1.setSouth(t2);
        return t2;
    }
}
