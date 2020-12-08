package buildings;

import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.Scanner;


public class Buildings {



    public static BuildingFactory factory = new DwellingFactory();

    public static void setBuildingFactory(BuildingFactory set){
        factory = set;
    }
    //=======================================Create Factory Objects====================================================
    public static Space createSpace(double area){
        Space create = factory.createSpace(area);
        return create;
    }

    public static Space createSpace(double area, int roomsCount){
        Space create = factory.createSpace(area, roomsCount);
        return create;
    }

    public static Space createSpace(Class < ? extends Space> spaceClass, int roomsCount, double area) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Space create = spaceClass.getDeclaredConstructor(int.class, double.class).newInstance(roomsCount, area);
        return create;
    }

    public static Floor createFloor(int spaceCount){
        Floor create = factory.createFloor(spaceCount);
        return create;
    }

    public static Floor createFloor(Space[] spaces){
        Floor create = factory.createFloor(spaces);
        return create;
    }

    public static Floor createFloor(Class<? extends Floor> floorClass, Space... spaces) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Floor create = floorClass.getDeclaredConstructor(Space[].class).newInstance((Object) spaces);
        return create;
    }

    public static Building createBuilding(int floorsCount, int[] spacesCounts){
        Building create = factory.createBuilding(floorsCount, spacesCounts);
        return create;
    }

    public static Building createBuilding(Floor[] floors){
        Building create = factory.createBuilding(floors);
        return create;
    }

    public static Building createBuilding(Class<? extends Building> buildingClass, Floor... floors) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Building create = buildingClass.getDeclaredConstructor(Floor[].class).newInstance((Object) floors);
        return create;
    }

    //======================================Sorts=============================================
    //                                    Comparable
    /*public static void sortSpaceSquare(Floor floor){
        boolean isSorted = false;
        Space tmp;
        while(!isSorted){
            isSorted = true;
            for(int i = 0; i < floor.getSpacesArr().length - 1; ++i){
                if(floor.getSpacesArr()[i].getSquare() > floor.getSpacesArr()[i+1].getSquare()){
                    isSorted = false;

                    tmp = floor.getSpacesArr()[i];
                    floor.getSpacesArr()[i] = floor.getSpacesArr()[i+1];
                    floor.getSpacesArr()[i+1] = tmp;
                }
            }
        }
        for (int i = 0; i < floor.getSpacesArr().length; ++i) {
            System.out.println(floor.getSpacesArr()[i]);
        }
    }*/

    public static void sortSpacesSquare(Space[] arr){

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                Space tmp;
                if(arr[j].compareTo(arr[j+1]) > 0){
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        for(int i = 0; i < arr.length; ++i) {
            System.out.println(arr[i]);
        }
    }

    /*public static void sortFloorSpacesNum(Building building){
        boolean isSorted = false;
        Floor tmp;
        while (!isSorted){
            isSorted = true;
            for(int i = 0; i < building.getFloorsArr().length - 1; ++i){
                if(building.getFloorsArr()[i].getSpacesNum() > building.getFloorsArr()[i+1].getSpacesNum()){
                    isSorted = false;

                    tmp = building.getFloorsArr()[i];
                    building.getFloorsArr()[i] = building.getFloorsArr()[i+1];
                    building.getFloorsArr()[i+1] = tmp;
                }
            }
        }
        for (int i = 0; i < building.getFloorsArr().length; ++i) {
            System.out.println(building.getFloorsArr()[i]);
        }
    }*/

    public static void sortFloorsRooms(Floor[] arr){

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                Floor tmp;
                if(arr[j].compareTo(arr[j+1]) > 0){
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        for(int i = 0; i < arr.length; ++i) {
            System.out.println(arr[i]);
        }

    }

    public static <T extends Comparable<T>> void sortArrays(T[] arr){

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                T tmp;
                if(arr[j].compareTo(arr[j+1]) > 0){
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        for(int i = 0; i < arr.length; ++i) {
            System.out.println(arr[i]);
        }
    }

    //===================================Compare==============================================

    public static void sortSpaceRoom(Space[] spaces){
        CriterionSpace obj = new CriterionSpace();
        for(int i = 0 ; i < spaces.length; ++i){
            for(int j = 0; j < spaces.length - i - 1;++j){
                if(obj.compare(spaces[j], spaces[j+1]) > 0){
                    Space tmp = spaces[j];
                    spaces[j] = spaces[j+1];
                    spaces[j+1] = tmp;
                }
            }
        }
        for(int i = 0; i < spaces.length; ++i) {
            System.out.println(spaces[i]);
        }
    }

    public static void sortFloorGenSquare(Floor[] floors){
        CriterionFloor obj = new CriterionFloor();
        for(int i = 0 ; i < floors.length; ++i){
            for(int j = 0; j < floors.length - i - 1;++j){
                if(obj.compare(floors[j], floors[j+1]) > 0){
                    Floor tmp = floors[j];
                    floors[j] = floors[j+1];
                    floors[j+1] = tmp;
                }
            }
        }
        for(int i = 0; i < floors.length; ++i) {
            System.out.println(floors[i]);
        }
    }

    public static <T> void sortArraysComparator(T[] arr, Comparator<T> comparator){

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                T tmp;
                if((comparator.compare(arr[j], arr[j+1])) > 0){
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        for(int i = 0; i < arr.length; ++i) {
            System.out.println(arr[i]);
        }
    }

    //=================================Input, Output, Serialization===========================
    public static void outputBuilding(Building building, OutputStream out){
        DataOutputStream out1 = new DataOutputStream(out);
        try {
            out1.writeInt(building.getFloorsNum());
            for (int i = 0; i < building.getFloorsNum(); ++i) {
                out1.writeInt(building.getFloor(i).getSpacesNum());
                for (int j = 0; j < building.getFloor(i).getSpacesNum(); ++j) {
                    out1.writeInt(building.getFloor(i).getSpace(j).getRooms());
                    out1.writeDouble(building.getFloor(i).getSpace(j).getSquare());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Building inputBuilding(InputStream in){
        DataInputStream in1 = new DataInputStream(in);
        Building building = null;
        try {
            int floors_num = in1.readInt();
            Floor[] floors = new Floor[floors_num];
            int spaces_number;
            for (int i = 0; i < floors_num; ++i) {
                spaces_number = in1.readInt();
                floors[i] = createFloor(spaces_number);
                int rooms;
                double square;
                for (int j = 0; j < spaces_number; ++j) {
                    rooms = in1.readInt();
                    square = in1.readDouble();
                    floors[i].changeSpace(j, createSpace(square, rooms));
                }
            }
            building = createBuilding(floors);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return building;
    }


    public static void writeBuilding (Building building, Writer out){
        try {

            out.write(building.getFloorsNum() + " ");

            for (int i = 0; i < building.getFloorsNum(); ++i) {

                out.write(building.getFloor(i).getSpacesNum() + " ");

                for (int j = 0; j < building.getFloor(i).getSpacesNum(); ++j) {

                    out.write(building.getFloor(i).getSpace(j).getRooms() + " ");
                    out.write("" + building.getFloor(i).getSpace(j).getSquare() + " ");

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static Building readBuilding (Reader in){
        StreamTokenizer token = new StreamTokenizer(in);

        try {
            token.nextToken();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int floors_num = (int)token.nval;
        if(floors_num == 0){
            return null;
        }
        else {
            Floor[] floors = new Floor[floors_num];
            int spaces_number;
            int rooms;
            double square;
            for (int i = 0; i < floors_num; ++i) {   //Analyze Floors
                try {
                    token.nextToken();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                spaces_number = (int) token.nval;

                floors[i] = createFloor(spaces_number);
                for (int space_index = 0; space_index < spaces_number; ++space_index) {

                    try {
                        token.nextToken();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    rooms = (int) token.nval;
                    try {
                        token.nextToken();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    square = token.nval;
                    floors[i].changeSpace(space_index, createSpace(square, rooms));
                }
            }

            Building building = createBuilding(floors);
            return building;
        }

    }


    public static void serializeBuilding (Building building, OutputStream out){
        ObjectOutputStream out1 = null;
        try {
            out1 = new ObjectOutputStream(out);
            out1.writeObject(building);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Building deserializeBuilding (InputStream in){
        Building B = null;
        ObjectInputStream in1 = null;
        try {
            in1 = new ObjectInputStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            B = (Building)in1.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return B;
    }


    public static void writeBuildingFormat (Building building, Writer out){
        ((PrintWriter) out).printf("\t Building ");
        int floors_number = building.getFloorsNum();
        ((PrintWriter) out).printf(" \n Floors number = " + floors_number);
        int spaces_number;
        int rooms;
        double square;
        for(int i = 0; i < floors_number; ++i){
            spaces_number = building.getFloor(i).getSpacesNum();
            ((PrintWriter) out).printf(" \n Spaces number = " + spaces_number);
            for(int j = 0; j < building.getFloor(i).getSpacesNum(); ++j){

                rooms = building.getFloor(i).getSpace(j).getRooms();
                ((PrintWriter) out).printf(" Rooms: " + rooms);

                square = building.getFloor(i).getSpace(j).getSquare();
                ((PrintWriter) out).printf(" Square: " + square);
            }
        }


    }


    public static Building readBuildingScanner (Scanner scanner){
        int floors_num = scanner.nextInt();
        System.out.println(floors_num);
        Floor[] floors = new Floor[floors_num];
        int spaces_number;

        for(int i = 0; i < floors_num; ++i){
            spaces_number = scanner.nextInt();
            //System.out.println(spaces_number);
            floors[i] = createFloor(spaces_number);
            for(int space_index = 0; space_index < spaces_number; ++space_index) {
                int rooms;
                double square;
                rooms = scanner.nextInt();
                //System.out.println(rooms);

                square = scanner.nextDouble();
                //System.out.println(square);

                floors[i].changeSpace(space_index,createSpace(square,rooms));
            }
        }
        Building building = createBuilding(floors);
        return building;
    }


    public static Floor synchronizedFloor(Floor floor){
        return new SynchronizedFloor(floor);
    }
}
