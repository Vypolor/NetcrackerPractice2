package buildings.threads;

import buildings.Floor;
import buildings.Semaphore;

public class SequentalRepairer implements Runnable{

    private Floor floor;
    private Semaphore semaphore;

    public SequentalRepairer(Semaphore semaphore, Floor floor){
        this.semaphore = semaphore;
        this.floor = floor;
    }

    @Override
    public synchronized void run() {
        try {
            semaphore.acquire();
            for (int i = 0; i < floor.getSpacesNum(); ++i) {
                System.out.println(Thread.currentThread().getName() +" : Repairing space number " + i + " with total area "
                        + floor.getSpace(i).getSquare() + " square meters");
                Thread.sleep(1000);
            }
            System.out.println("End of Repair");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
