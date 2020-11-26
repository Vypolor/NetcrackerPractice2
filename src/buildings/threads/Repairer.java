package buildings.threads;

import buildings.Floor;

public class Repairer extends Thread{
    private Floor floor;

    public Repairer(Floor floor){
        this.floor = floor;
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < floor.getSpacesNum(); ++i) {
            System.out.println(Thread.currentThread().getName() +" : Repairing space number " + i + " with total area "
                    + floor.getSpace(i).getSquare() + " square meters");
        }
        System.out.println("End of Repair");
    }
}
