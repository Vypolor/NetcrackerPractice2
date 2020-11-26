package buildings.dwelling.hotel;

import buildings.Space;
import buildings.dwelling.DwellingFloor;

public class HotelFloor extends DwellingFloor {

    private int _stars_count;

    final int STANDART_STARS_COUNT = 1;

    public HotelFloor(int num){
        super(num);
        _stars_count = STANDART_STARS_COUNT;
    }

    public HotelFloor(Space[] arr){
        super(arr);
        _stars_count = STANDART_STARS_COUNT;
    }

    public int getStarsCount(){
        return _stars_count;
    }

    public void setStarsCount(int set){
        _stars_count = set;
    }

    @Override
    public String toString() {
        String s = new String( "Hotel Floor (" + getStarsCount()) + ", " + (this.getSpacesNum());
        for(int i = 0; i < super.getSpacesNum(); ++i){
            s += getSpace(i).toString();
        }
        s += ")";
        return s;
    }

    @Override
    public boolean equals(Object object) {
        boolean flag = false;
        if((object instanceof HotelFloor)&&(this.getSpacesNum() == ((HotelFloor) object).getSpacesNum())){
            for(int i = 0 ; i < getSpacesNum(); ++i){
                if(getSpace(i).equals(((HotelFloor) object).getSpace(i)) == false){
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
        for(int i = 0; i < getSpacesNum(); ++i){
            result += _stars_count ^ getSpacesNum() ^ getSpace(i).hashCode();
        }
        return result;
    }
}
