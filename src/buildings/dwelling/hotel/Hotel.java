package buildings.dwelling.hotel;

import buildings.Floor;
import buildings.Space;
import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;

public class Hotel extends Dwelling {
    //private Floor _arr;
    private int _starsCount;
    private final int STARS_NUMBER = 1;

    public Hotel(int floorsNum, int[] arr_num_of_flats) {
        super(floorsNum, arr_num_of_flats);
        for(int i = 0; i < floorsNum; ++i){
            changeFloor(i, new HotelFloor(arr_num_of_flats[i]));
        }
        _starsCount = STARS_NUMBER;
    }

    public Hotel(Floor[] arr){
        super(arr);
        int max = ((HotelFloor)arr[0]).getStarsCount();
        for(int i = 0 ; i < arr.length; ++i){
            if(arr[i] instanceof HotelFloor) {
                if (max < ((HotelFloor) arr[i]).getStarsCount()) {
                    max = ((HotelFloor) arr[i]).getStarsCount();
                }
            }
        }
        _starsCount = max;
    }

    @Override
    public Space getBestSpace(){
        Space best = new Flat();
        double coeff = 0.25;
        double area;
        double bestAreaCoeff = 0;
        for(int i = 0; i < getFloorsNum(); ++i){
            if(!(getFloor(i) instanceof HotelFloor)) continue;
            switch(((HotelFloor)getFloor(i)).getStarsCount()){
                case 1:
                    coeff = 0.25;
                    break;
                case 2:
                    coeff = 0.5;
                    break;
                case 3:
                    coeff = 1;
                    break;
                case 4:
                    coeff = 1.25;
                    break;
                case 5:
                    coeff = 1.5;
                    break;
            }
            area = getFloor(i).getBestSpace().getSquare();

            if(bestAreaCoeff < area * coeff) {
                bestAreaCoeff = area * coeff;
                best = getFloor(i).getBestSpace();
            }

        }
        return best;
    }

    @Override
    public String toString() {
        String s = new String("Hotel (" + _starsCount) +", " + getFloorsNum() + ", ";
        for(int i = 0; i < getFloorsNum(); ++i){
            s += getFloor(i).toString();
        }
        s += ")";
        return s;
    }

    @Override
    public boolean equals(Object object) {
        boolean flag = false;
        if((object instanceof Hotel)&&(this.getFloorsNum() == ((Hotel) object).getFloorsNum())&&
                (this.getGenSpacesNum() == ((Hotel) object).getGenSpacesNum())){
            for(int i = 0; i < this.getFloorsNum(); ++i){
                if(this.getFloor(i).equals(((Hotel) object).getFloor(i)) == false){
                    return flag;
                }
            }
            flag = true;
        }
        return flag;
    }

    @Override
    public int hashCode(){
        int result = 0;
        for(int i = 0; i < getFloorsNum(); ++i){
            result += _starsCount ^ getFloorsNum() ^ getFloor(i).hashCode();
        }
        return result;
    }

}
