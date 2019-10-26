package pl.edu.agh.kusnierz.twodmeshparallel;

import pl.edu.agh.kusnierz.twodmeshparallel.mesh.GraphDrawer;
import pl.edu.agh.kusnierz.twodmeshparallel.mesh.Vertex;
import pl.edu.agh.kusnierz.twodmeshparallel.myProductions.P1;
import pl.edu.agh.kusnierz.twodmeshparallel.myProductions.P2;
import pl.edu.agh.kusnierz.twodmeshparallel.myProductions.P3;
import pl.edu.agh.kusnierz.twodmeshparallel.parallelism.BlockRunner;
import pl.edu.agh.kusnierz.twodmeshparallel.production.PDrawer;

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

        System.out.println("iksde");
        //p1 
        P1 p1 = new P1(s, drawer);
        this.runner.addThread(p1);

        //start threads
        this.runner.startAll();

        //p2,p3
        P2 p2 = new P2(p1.getObj(), drawer);
        P3 p3 = new P3(p2.getObj(), drawer);
        this.runner.addThread(p2);
        this.runner.addThread(p3);

        //start threads
        this.runner.startAll();

        //start threads
        this.runner.startAll();

        //done
        System.out.println("done");
        drawer.draw(p3.getObj());

    }
}
