package buildings;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFloor;

public class HotelFactory implements BuildingFactory {
    public Space createSpace(double area){
        Space create = new Flat(area);
        return create;
    }

    public Space createSpace(double area, int roomsCount){
        Space create = new Flat(area, roomsCount);
        return create;
    }

    public Floor createFloor(int spaceCount){
        Floor create = new HotelFloor(spaceCount);
        return create;
    }

    public Floor createFloor(Space[] spaces){
        Floor create = new HotelFloor(spaces);
        return create;
    }

    public Building createBuilding(int floorsCount, int[] spacesCounts){
        Building create = new Hotel(floorsCount, spacesCounts);
        return create;
    }

    public Building createBuilding(Floor[] floors){
        Building create = new Hotel(floors);
        return create;
    }
}
