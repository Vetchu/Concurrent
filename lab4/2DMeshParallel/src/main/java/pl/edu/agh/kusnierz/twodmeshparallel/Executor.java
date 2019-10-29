package pl.edu.agh.kusnierz.twodmeshparallel;

import pl.edu.agh.kusnierz.twodmeshparallel.mesh.GraphDrawer;
import pl.edu.agh.kusnierz.twodmeshparallel.mesh.Vertex;
import pl.edu.agh.kusnierz.twodmeshparallel.myProductions.P1;
import pl.edu.agh.kusnierz.twodmeshparallel.myProductions.P2;
import pl.edu.agh.kusnierz.twodmeshparallel.myProductions.P3;
import pl.edu.agh.kusnierz.twodmeshparallel.myProductions.P4;
import pl.edu.agh.kusnierz.twodmeshparallel.parallelism.BlockRunner;
import pl.edu.agh.kusnierz.twodmeshparallel.production.PDrawer;

import java.util.AbstractMap;

public class Executor extends Thread {
    
    private final BlockRunner runner;
    
    public Executor(BlockRunner _runner){
        this.runner = _runner;
    }

    @Override
    public void run() {
        PDrawer drawer = new GraphDrawer();
        //axiom
        Vertex s = new Vertex(null, null,null,null, "S");

        //p1
        P1 p1 = new P1(s, drawer);
        this.runner.addThread(p1);

        //start threads
        this.runner.startAll();

        //p2,p3
        P2 p2 = new P2(p1.getObj(), drawer);
        P3 p3 = new P3(p1.getObj(), drawer);
        this.runner.addThread(p2);
        this.runner.addThread(p3);
        //start threads
        this.runner.startAll();

        P2 p2a = new P2(p2.getObj(), drawer);
        P2 p2b = new P2(p3.getObj(), drawer);
        P3 p3b = new P3(p3.getObj(), drawer);
        this.runner.addThread(p2a);
        this.runner.addThread(p2b);
        this.runner.addThread(p3b);
        //start threads
        this.runner.startAll();

        P2 p2c = new P2(p2b.getObj(), drawer);
        P2 p2d = new P2(p3b.getObj(), drawer);
        P4 p4 = new P4(new AbstractMap.SimpleEntry<>(p2b.getObj(),p2.getObj()), drawer);
        this.runner.addThread(p2c);
        this.runner.addThread(p2d);
        this.runner.addThread(p4);
        //start threads
        this.runner.startAll();

        P2 p2e = new P2(p2d.getObj(), drawer);
        P4 p4b = new P4(new AbstractMap.SimpleEntry<>(p2d.getObj(),p2b.getObj()), drawer);
        P4 p4c = new P4(new AbstractMap.SimpleEntry<>(p2c.getObj(),p2a.getObj()), drawer);

        this.runner.addThread(p2e);
        this.runner.addThread(p4b);
        this.runner.addThread(p4c);
        this.runner.startAll();

        P4 p4d = new P4(new AbstractMap.SimpleEntry<>(p2e.getObj(),p2c.getObj()), drawer);
        this.runner.addThread(p4d);
        this.runner.startAll();
        //done
        System.out.println("done");
        drawer.draw(p3.getObj());
    }
}
