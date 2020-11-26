package buildings.threads;

import buildings.Floor;

public class Cleaner extends Thread{
    private Floor floor;

    public Cleaner(Floor floor){
        this.floor = floor;
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < floor.getSpacesNum(); ++i) {
            System.out.println(Thread.currentThread().getName() +" : Cleaning room number " + i + " with total area "
                    + floor.getSpace(i).getSquare() + " square meters");
        }
        System.out.println("End of Cleaner");
    }
}
