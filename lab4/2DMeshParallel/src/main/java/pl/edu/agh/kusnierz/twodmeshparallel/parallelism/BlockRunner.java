package pl.edu.agh.kusnierz.twodmeshparallel.parallelism;

import pl.edu.agh.kusnierz.twodmeshparallel.production.IProduction;

public interface BlockRunner {

    //starts all threads
    public void startAll();

    //adds a thread to poll
    public void addThread(IProduction pThread);
}
