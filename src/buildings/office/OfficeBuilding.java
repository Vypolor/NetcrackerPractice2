package buildings.office;
import buildings.*;
import buildings.FloorIndexOutOfBoundsException;
import buildings.lists.LinkList;
import buildings.lists.LinkListNode;

import java.io.Serializable;
import java.util.Iterator;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class OfficeBuilding implements Building, Serializable, Cloneable {
    private LinkList building;

    private LinkListNode getFloorNode(int num) throws FloorIndexOutOfBoundsException {
        if(num < 0 || num >= building.getSize()) throw new FloorIndexOutOfBoundsException(num,building.getSize());

        return building.getLinkListNode(num);
    }

    private void addFloorNode(Floor floor, int num) throws FloorIndexOutOfBoundsException {
        if(num < 0 || num > building.getSize()) throw new FloorIndexOutOfBoundsException(num,building.getSize() + 1);

        building.insert(floor, num);
    }

    private void removeFloorNode(int num) throws FloorIndexOutOfBoundsException {
        if(num < 0 || num >= building.getSize()) throw new FloorIndexOutOfBoundsException(num,building.getSize());

        building.removeAt(num);
    }


    public OfficeBuilding(int floorsNum, int spacesNum[]){
        building = new LinkList();
        for(int i = 0; i < floorsNum; ++i){
           building.push_back(new OfficeFloor(spacesNum[i]));
        }

    }


    public OfficeBuilding(Floor[] arr){
        building = new LinkList();
        for (Floor floor : arr) {
            building.push_back(floor);
        }
    }


    public int getFloorsNum(){
        return building.getSize();
    }


    public int getGenSpacesNum(){
        int sum = 0;
        for(int i = 0; i < building.getSize(); ++i){
            sum += getFloorNode(i)._floor.getSpacesNum();
        }
        return sum;
    }


    public double getGenSquare(){
        int sum = 0;
        for(int i = 0; i < building.getSize(); ++i){
            sum += getFloorNode(i)._floor.getFloorSquare();
        }
        return sum;
    }

    public int getGenRooms(){
        int sum = 0;
        for(int i = 0; i < building.getSize(); ++i){
            sum += getFloorNode(i)._floor.getFloorRooms();
        }
        return sum;
    }


    public Floor[] getFloorsArr(){
        Floor[] arr = new Floor[building.getSize()];
        for(int i = 0; i < arr.length; ++i){
            arr[i] = getFloorNode(i)._floor;
        }
        return arr;
    }


    public Floor getFloor(int num){
        return getFloorNode(num)._floor;
    }


    public void changeFloor(int num, Floor iFloor) throws FloorIndexOutOfBoundsException {
        if(num < 0 || num >= building.getSize()) throw new FloorIndexOutOfBoundsException(num,building.getSize());
        getFloorNode(num)._floor = iFloor;
    }


    public Space getSpace(int num) throws SpaceIndexOutOfBoundsException {
        if(num < 0 || num >= getGenSpacesNum()) throw new SpaceIndexOutOfBoundsException(num,getGenSpacesNum());
        int office_number = 0;
        Space office = new Office();
        for(int i = 0; i < getFloorsNum(); ++i){
            for(int j = office_number; j < office_number + getFloorsArr()[i].getSpacesNum(); ++j){
                if(j == num){
                    office = getFloorNode(i)._floor.getSpace(j-office_number);// getFloorsArr()[i].getOfficesArr()[j - office_number];
                    break;
                }
            }
            office_number += getFloorNode(i)._floor.getSpacesNum();//getFloorsArr()[i].getOfficesNum();
        }
        return office;
    }


    public void changeSpace(int num, Space space) throws SpaceIndexOutOfBoundsException {
        if(num < 0 || num > getGenSpacesNum()) throw new SpaceIndexOutOfBoundsException(num,getGenSpacesNum() + 1);

        int space_number = 0;
        for(int i = 0; i < getFloorsNum(); ++i){
            for(int j = space_number; j < space_number + getFloorsArr()[i].getSpacesNum(); ++j){
                if(j == num){
                    getFloorsArr()[i].changeSpace(j - space_number, space);
                    break;
                }
            }
            space_number += getFloorsArr()[i].getSpacesNum();
        }
    }


    public void addSpace(int num, Space space) throws SpaceIndexOutOfBoundsException {
        if(num < 0 || num > getGenSpacesNum()) throw new SpaceIndexOutOfBoundsException(num,getGenSpacesNum() + 1);

        int office_number = 0;
        for(int i = 0; i < getFloorsNum(); ++i){
            for(int j = office_number; j <= office_number + getFloorsArr()[i].getSpacesNum(); ++j){
                if(j == num){
                    getFloorsArr()[i].addSpace(j - office_number, space);
                    break;
                }
            }
            office_number += getFloorsArr()[i].getSpacesNum();
        }
    }


    public void removeSpace(int num) throws SpaceIndexOutOfBoundsException {
        if(num < 0 || num >= getGenSpacesNum()) throw new SpaceIndexOutOfBoundsException(num,getGenSpacesNum());
        int office_number = 0;
        for(int i = 0; i < getFloorsNum(); ++i){
            for(int j = office_number; j < office_number + getFloorsArr()[i].getSpacesNum(); ++j){
                if(j == num){
                    getFloorsArr()[i].removeSpace(j - office_number);
                    break;
                }
            }
            office_number += getFloorsArr()[i].getSpacesNum();
        }
    }


    public Space getBestSpace(){
        Space best = new Office();
        for(int i = 0; i < getFloorsArr().length - 1; ++i){
            if(getFloorsArr()[i].getBestSpace().getSquare() < getFloorsArr()[i + 1].getBestSpace().getSquare()) best = getFloorsArr()[i + 1].getBestSpace();
        }
        return best;
    }


    public Space[] getSortSpaces(){

        Space[] arr = new Space[getGenSpacesNum()];
        int space_num = 0;
        for(int i = 0; i < getFloorsArr().length; ++i){
            for(int j = 0; j < getFloorsArr()[i].getSpacesNum(); ++j){
                arr[j + space_num] = getFloorsArr()[i].getSpacesArr()[j];
            }
            space_num += getFloorsArr()[i].getSpacesNum();
        }

        Space tmp;
        for(int i = 0; i < arr.length - 1; ++i){
            for(int j = 0; j < arr.length - i - 1; ++j){
                if(arr[j].getSquare() < arr[j+1].getSquare()){
                    tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
        return arr;
    }


    public String toString(){
        String s = new String("OfficeBuilding (" + this.getFloorsNum());
        for(int i = 0; i < getFloorsNum(); ++i){
            s += ", " + getFloor(i).toString();
        }
        s+= ")";
        return s;
    }


    public int hashCode(){
        int result = 0;
        for(int i = 0; i < getFloorsNum(); ++i){
            result += getFloorsNum() ^ getFloor(i).hashCode();
        }
        return result;
    }


    public boolean equals(Object object){
        boolean flag = false;
        if((object instanceof OfficeBuilding)&&(this.getFloorsNum() == ((OfficeBuilding) object).getFloorsNum())&&
                (this.getGenSpacesNum() == ((OfficeBuilding) object).getGenSpacesNum())){
            for(int i = 0; i < this.getFloorsNum(); ++i){
                if(this.getFloor(i).equals(((OfficeBuilding) object).getFloor(i)) == false){
                    return flag;
                }
            }
            flag = true;
        }
        return flag;
    }


    public Object clone(){
        Object result = null;
        try {
            result = super.clone();
            ((OfficeBuilding)result).building = (LinkList)building.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public Iterator<Floor> iterator(){
        Iterator<Floor> it = new Iterator<Floor>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                if(index < getFloorsNum()){
                    return true;
                }
                else return false;
            }

            @Override
            public Floor next() {
                return getFloor(index++);
            }
        };
        return it;
    }


    public void print(){
        System.out.println("\n=============== Office Building ===============" + "\n");
        int office_number = 0;
        for(int i = 0; i < getFloorsNum(); ++i){
            System.out.println(" ======= Floor # " + i + " =======");
            for(int j = office_number; j < office_number + getFloorsArr()[i].getSpacesNum(); ++j){
                System.out.print("== '" + j + "' ");
                getFloorsArr()[i].getSpacesArr()[j - office_number].print();
            }
            office_number += getFloorsArr()[i].getSpacesNum();
        }
        System.out.println("===============================================" + "\n\n\n");
    }
}