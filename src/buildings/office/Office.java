package buildings.office;

import buildings.InvalidRoomsCountException;
import buildings.InvalidSpaceAreaException;
import buildings.Space;

import java.io.Serializable;
import java.nio.ByteBuffer;

public class Office implements Space, Serializable, Cloneable {
    private double _square;
    private int _rooms;

    final double SQUARE = 250;
    final int ROOMS = 1;

    public void throwSquare(double square) throws InvalidSpaceAreaException {
        if(square < 20 || square > 500) throw new InvalidSpaceAreaException(square);
    }

    public void throwRooms(int rooms) throws InvalidRoomsCountException {
        if(rooms < 1 || rooms > 10) throw new InvalidRoomsCountException(rooms);
    }

    public Office(){
        _square = SQUARE;
        _rooms = ROOMS;
    }
    public Office(double square){
        throwSquare(square);
        _square = square;
        _rooms = ROOMS;
    }
    public Office(double square, int rooms){
        throwSquare(square);
        throwRooms(rooms);
        _square = square;
        _rooms = rooms;
    }
    public int getRooms(){
        return _rooms;
    }
    public double getSquare(){
        return _square;
    }
    public void setRooms(int set){
        throwRooms(set);
        _rooms = set;
    }
    public void setSquare(double set){
        throwSquare(set);
        _square = set;
    }
    public String toString(){
        String s = new String(" Office" + "( "+_rooms + ", " + _square +" )");
        return s;
    }

    public boolean equals(Object object){
        if((object instanceof Office) && (((Office) object)._rooms == _rooms)
                && ((Office) object)._square == _square){
            return true;
        }
        else return false;
    }

    //использование масок
   /* public int hashCode(){
        int result;
        long doubleAsLong = Double.doubleToLongBits(_square);
        long mask1 = 0b0000000000000000000000000000000011111111111111111111111111111111L;
        long mask2 = 0b1111111111111111111111111111111100000000000000000000000000000000L;
        long a = doubleAsLong & mask1;
        long b = doubleAsLong & mask2;
        result = (int)(_rooms ^ a ^ b);
        return result;
    }*/

    public int hashCode(){
        int result;
        byte[] b = ByteBuffer.allocate(8).putDouble(_square).array();
        int x1 = b[0] + 256*b[1] + 65536*b[2] + 1677721*b[3];
        int x2 = b[4] + 256*b[5] + 65536*b[6] + 1677721*b[7];
        result = _rooms ^ x1 ^ x2;
        return result;
    }

    /*public Object clone(){
        Space obj = new Office(this._square, this._rooms);
        return obj;
    }

     */
    public Object clone(){
        Object result = null;
        try {
            result = super.clone();
            //((Office)result)._square = this._square;
            //((Office)result)._rooms = this._rooms;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int compareTo(Space o) {
        //return (int) (_square - o.getSquare());
        if(_square < o.getSquare())return -1;
        if(_square > o.getSquare())return 1;
        return 0;
    }

    public void print(){
        System.out.println("Office");
        System.out.println(" Rooms: " + _rooms);
        System.out.println(" Square: " + _square + "\n");
    }
}
