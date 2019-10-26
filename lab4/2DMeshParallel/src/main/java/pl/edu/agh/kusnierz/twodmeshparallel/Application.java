package pl.edu.agh.kusnierz.twodmeshparallel;

import pl.edu.agh.kusnierz.twodmeshparallel.parallelism.ConcurentBlockRunner;

class Application {

    public static void main(String args[]) {

        Executor e = new Executor(new ConcurentBlockRunner());
        e.start();
    }
}
