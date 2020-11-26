package buildings;

import java.util.Iterator;

public interface Floor extends Comparable<Floor>, Iterable<Space> {
    int getSpacesNum();
    double getFloorSquare();
    int getFloorRooms();
    Space[] getSpacesArr();
    Space getSpace(int num);
    void changeSpace(int num, Space space);
    void addSpace(int num, Space space);
    void removeSpace(int num);
    Space getBestSpace();
    String toString();
    boolean equals(Object object);
    Object clone();
    void print();

}
