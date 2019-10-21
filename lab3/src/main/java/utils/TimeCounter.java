package utils;

public class TimeCounter {
    private long starttime;
    private int id;
    public TimeCounter(int id){
        this.id=id;
    }

    public void startCount(){
        this.starttime=System.nanoTime();
    }
    public void endCount(){
        System.out.println(id+";"+(System.nanoTime()-starttime));
        starttime=0;
    }
}
