import buildings.*;
import buildings.dwelling.Dwelling;
import buildings.dwelling.hotel.*;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.dwelling.hotel.HotelFloor;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;
import buildings.threads.Cleaner;
import buildings.threads.Repairer;
import buildings.threads.SequentalCleaner;
import buildings.threads.SequentalRepairer;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ThreadPoolExecutor;

public class Runner {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        /*//Space tmpo1 = new Office();
        //Space tmpo2 = new Office();
        Space tmpo3 = new Flat(75,3);
        Space tmpo0 = new Flat(66,4);

        Space[] arrSpace = new Space[2];
        //arrSpace[0] = tmpo1;
        //arrSpace[1] = tmpo2;
        arrSpace[0] = tmpo3;
        arrSpace[1] = tmpo0;
        Floor fl = new DwellingFloor(arrSpace);
        //fl.print();

        //Space tmpo4 = new Office();
        //Space tmpo5 = new Office(250.5,1);
       */
        /*Space[] arrSpace2 = new Space[2];
        //arrSpace2[0] = tmpo4;
        //arrSpace2[1] = tmpo5;
        arrSpace2[0] = tmpo6;
        arrSpace2[1] = tmpo7;
        //Floor fl2 = new OfficeFloor(arrSpace2);
        //fl2.print();
        Floor floor1 = new DwellingFloor(3);
        Floor floor2 = new OfficeFloor(3);
        Floor floor3 = new DwellingFloor(5);
        //System.out.println(floor1.equals(floor2));
        Floor offciefloor = new OfficeFloor(5);
        //offciefloor.print();
        Floor arrfloor[] = new Floor[3];
        //arrfloor[0] = fl;
        arrfloor[0] = floor1;
        arrfloor[1] = floor3;
        arrfloor[2] = floor2;




        Floor f67 = null;
        double t = 204;

        Floor hotel = new HotelFloor(4);
        ((HotelFloor)hotel).setStarsCount(4);
        Floor floor_1 = new DwellingFloor(2);
        Floor floor_2 = new HotelFloor(3);
        //Buildings.sortSpaceSquare(floor2);
        //==============================Sort=================================

        Space obj = new Flat(76, 3);
        Space obj2 = new Flat(68, 2);
        Space obj3 = new Office(245, 1);
        Space obj4 = new Flat(53, 4);
        Space obj5 = new Flat(78, 7);
        Space obj6 = new Flat(44, 2);
        Space obj7 = new Flat(32, 9);
        Space []sortFloor = new Space[7];
        sortFloor[0] = obj5;
        sortFloor[1] = obj2;
        sortFloor[2] = obj;
        sortFloor[3] = obj4;
        sortFloor[4] = obj3;
        //sortFloor[5]obj7; = obj6;
        //sortFloor[6] =
        //Floor sort = new DwellingFloor(sortFloor);
        //Buildings.sortSpaceSquare(sort);
        System.out.println("====================================");
        //Buildings.sort(sort.getSpacesArr());
        Floor[] sortBuilding = new Floor[3];
        //sortBuilding[0] = sort;
        sortBuilding[1] = floor_1;
        sortBuilding[2] = floor_2;
        Building buildsort = new Dwelling(sortBuilding);
        System.out.println("====================================");
        //Buildings.sortFloorSpacesNum(buildsort);
        System.out.println("====================================");
        //Buildings.sort(buildsort.getFloorsArr());
        //System.out.println(sort);
        //Buildings.sortSpaceRoom(sort.getSpacesArr());
        System.out.println("====================================");
        Buildings.sortFloorGenSquare(buildsort.getFloorsArr());

        //===================================================================================
       // Building Hotel = new Hotel(arr6);
       // System.out.println(Hotel);
       // System.out.println(Hotel.getBestSpace());
       */

        //System.out.println("==========================================");
        

      /*  System.out.println("================================================");

        Floor testbomb = new DwellingFactory().createFloor(4);
        System.out.println(testbomb);
        int intarr[] = {2, 3, 4, 5};
        Building qqqq = new HotelFactory().createBuilding(3, intarr);
        System.out.println(qqqq);

        Space tmpo6 = new Flat(57,2);
        Space tmpo7 = new Flat(65,3);
        Space office = new OfficeFactory().createSpace(254);
        System.out.println(office.compareTo(office));
        */
        // ======================================== 7 ============================================
       /* Floor floor = new DwellingFloor(10);
        Floor floor1 = new DwellingFloor(4);*/
        /*Thread th = new Repairer(floor);
        Thread th1 = new Repairer(floor);
        Thread th2 = new Repairer(floor);
        Thread th3 = new Cleaner(floor);
        th3.interrupt();
        th.start();
        th1.start();
        th2.start();
        th3.start();*/

       /* Semaphore semaphore = new Semaphore(100);
        SequentalRepairer t = new SequentalRepairer(semaphore, floor);
        SequentalCleaner t1 = new SequentalCleaner(semaphore, floor);
        *//*SequentalRepairer t3 = new SequentalRepairer(semaphore1, floor1);
        SequentalCleaner t2 = new SequentalCleaner(semaphore1, floor1);*//*

        for (int i = 0; i < 100; ++i) {
            new Thread(t).start();
            new Thread(t1).start();
        }*/
        /*new Thread(t3).start();
        new Thread(t2).start();*/
        //new Thread(t2).start();
       /* OutputStream out = null;
        try {
            out = new FileOutputStream(new File("E:/Kreker/#8/Tmp.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Floor[] floors1 = new Floor[3];
        for (int i = 0; i < floors1.length; i++) {
            floors1[i] = new DwellingFloor(3);
        }

        Floor[] floors2 = new Floor[3];
        for (int i = 0; i < floors1.length; i++) {
            floors1[i] = new OfficeFloor(3);
        }

        Building b1 = new Dwelling(floors1);
        Building b2 = new OfficeBuilding(floors2);

        Buildings.serializeBuilding(b1, out);
        Buildings.serializeBuilding(b2, out);

        FileReader in = null;
        try {
            in = new FileReader(new File("E:/Kreker/#8/BuildingInfo.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        b1 = Buildings.readBuilding(in);
        b1.print();*/

        //Buildings.deserializeBuilding(in).print();
        Space space[] = new Space[3];
        for (int i = 0; i < space.length; i++) {
            space[i] = Buildings.createSpace(Flat.class, 100, 3);
        }

        Floor floor[] = new Floor[3];
        for (int i = 0; i < floor.length; i++) {
            floor[i] = Buildings.createFloor(DwellingFloor.class, space);
        }

        Building b = Buildings.createBuilding(floor);
        System.out.println(b);
    }
}
