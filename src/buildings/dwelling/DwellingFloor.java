package buildings.dwelling;

import buildings.Floor;
import buildings.Space;
import buildings.SpaceIndexOutOfBoundsException;

import java.awt.desktop.PreferencesEvent;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Iterator;

public class DwellingFloor implements Floor, Serializable, Cloneable, Iterable<Space> {

    private int _spacesNum;
    private Space _arr[];



    public DwellingFloor(int flatsNum){
        _arr = new Space[flatsNum];
        _spacesNum = flatsNum;
        for(int i = 0; i < flatsNum; ++i){
            _arr[i] = new Flat();
        }

    }

    public DwellingFloor(Space arr[]){
        _arr = new Space[arr.length];
        for(int i = 0; i < arr.length; ++i){
            _arr[i] = arr[i];
        }
        _spacesNum = arr.length;

    }

    public double getFloorSquare(){
        int sum = 0;
        for(int i = 0; i < _spacesNum; ++i){
            sum += _arr[i].getSquare();
        }
        return sum;
    }

    public int getFloorRooms(){
        int sum = 0;
        for(int i = 0; i < _spacesNum; ++i){
            sum += _arr[i].getRooms();
        }
        return sum;
    }

    public int getSpacesNum(){
        return _spacesNum;
    }

    public Space[] getSpacesArr(){
        return _arr;
    }

    public Space getSpace(int num) /*throws SpaceIndexOutOfBoundsException*/ {
       /* if(num < 0 || num >= _arr.length){
            throw new SpaceIndexOutOfBoundsException(num,_arr.length);
        }*/
        return _arr[num];
    }

    public void changeSpace(int num, Space space) /*throws SpaceIndexOutOfBoundsException*/{
        /*if(num < 0 || num >= _arr.length){
            throw new SpaceIndexOutOfBoundsException(num,_arr.length);
        }*/
        _arr[num] = space;
    }

    public void addSpace(int num, Space space) throws SpaceIndexOutOfBoundsException{
        if(num < 0 || num > _arr.length){
            throw new SpaceIndexOutOfBoundsException(num,_arr.length + 1);
        }

        Space tmp[] = new Space[_spacesNum + 1];;
        for(int i = 0; i < num; ++i){
            tmp[i] = _arr[i];
        }
        tmp[num] = space;
        for(int i = num; i < _spacesNum; ++i){
            tmp[i+1] = _arr[i];
        }
        _arr = new Space[_spacesNum + 1];
        for(int i = 0; i < _spacesNum + 1; ++i){
            _arr[i] = tmp[i];
        }
        _spacesNum += 1;
    }

    public void removeSpace(int num) throws SpaceIndexOutOfBoundsException{
        if(num < 0 || num >= _arr.length){
            throw new SpaceIndexOutOfBoundsException(num,_arr.length);
        }
        Space tmp[] = new Space[_spacesNum - 1];
        /*int j = 0;
        for(int i = 0; i < _flatsNum; ++i){
            if(i!=num){
                tmp[j] = _arr[i];
            }
            else{
                tmp[j] = _arr[i+1];
                i++;
            }
            j++;
        }*/
        for(int i = 0; i < num; ++i){
            tmp[i] = _arr[i];
        }
        for(int i = num + 1; i < _spacesNum; ++i){
            tmp[i-1] = _arr[i];
        }
        _arr = new Space[_spacesNum - 1];
        for(int i = 0; i < _spacesNum - 1; ++i){
            _arr[i] = tmp[i];
        }
        _spacesNum -= 1;
    }


    public Space getBestSpace(){
        Space best = _arr[0];
        for(int i = 1; i < _spacesNum; ++i){
            if(best.getSquare() < _arr[i].getSquare()) {
                best = _arr[i];
            }
        }
        return best;
    }

    public String toString(){
        String s = new String("Dwelling Floor" + "(" + this.getSpacesNum()) + ", ";
        for(int i = 0 ; i < getSpacesNum(); ++i){
            s += getSpace(i).toString();
        }
        s = s + ")";
        return s;
    }

    public boolean equals(Object object){
        boolean flag = false;
        if((object instanceof DwellingFloor)&&(this.getSpacesNum() == ((DwellingFloor) object).getSpacesNum())){
            for(int i = 0 ; i < getSpacesNum(); ++i){
                if(getSpace(i).equals(((DwellingFloor) object).getSpace(i)) == false){
                    return flag;
                }
            }
            flag = true;
        }
        return flag;
    }



    public int hashCode(){
        int result = 0;
        for(int i = 0; i < getSpacesNum(); ++i){
            result += getSpacesNum() ^ getSpace(i).hashCode();
        }
        return result;
    }

    public Object clone(){
        Object result = null;
        try {
            result = super.clone();
            ((DwellingFloor)result)._arr = _arr.clone();
            for (int i = 0; i < _arr.length; ++i) {
                ((DwellingFloor)result)._arr[i] = (Space)_arr[i].clone();
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Iterator<Space> iterator(){
        Iterator<Space> it = new Iterator<Space>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                if(index < getSpacesNum()){
                    return true;
                }
                else return false;
            }

            @Override
            public Space next() {
                return getSpace(index++);
            }
        };
        return it;
    }

    @Override
    public int compareTo(Floor o) {
        if(getSpacesNum() < o.getSpacesNum())return -1;
        if(getSpacesNum() > o.getSpacesNum())return 1;
        return 0;
    }

    public void print(){
        System.out.println("=======Dwelling Floor=======");
        for(int i = 0; i < _spacesNum; ++i){
            _arr[i].print();
        }
        System.out.print("\n");
    }
}
