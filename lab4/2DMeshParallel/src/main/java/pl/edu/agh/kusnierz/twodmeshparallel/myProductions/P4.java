package pl.edu.agh.kusnierz.twodmeshparallel.myProductions;

import pl.edu.agh.kusnierz.twodmeshparallel.mesh.Vertex;
import pl.edu.agh.kusnierz.twodmeshparallel.production.AbstractProduction;
import pl.edu.agh.kusnierz.twodmeshparallel.production.PDrawer;

import java.util.AbstractMap;

public class P4 extends AbstractProduction<AbstractMap.SimpleEntry<Vertex,Vertex>> {

    public P4(AbstractMap.SimpleEntry<Vertex, Vertex> _obj, PDrawer<AbstractMap.SimpleEntry<Vertex, Vertex>> _drawer) {
        super(_obj,_drawer);
    }

    @Override
    public AbstractMap.SimpleEntry<Vertex, Vertex> apply(AbstractMap.SimpleEntry<Vertex, Vertex> _p) {
        System.out.println("p4");
        _p.getKey().setNorth(_p.getValue());
        _p.getValue().setSouth(_p.getKey());
        return _p;
    }

}
