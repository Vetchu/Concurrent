package pl.edu.agh.kusnierz.twodmeshparallel.production;

import pl.edu.agh.kusnierz.twodmeshparallel.parallelism.MyLock;

public interface IProduction<P> {

    public P apply(P _p);

    public void join() throws InterruptedException;

    public void start();

    public void injectRefs(MyLock _lock);

    public P getObj();
}
