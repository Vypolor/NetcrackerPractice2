package buildings.office;
import buildings.Floor;
import buildings.Space;
import buildings.SpaceIndexOutOfBoundsException;
import buildings.FloorIndexOutOfBoundsException;
import buildings.dwelling.Flat;
import buildings.lists.ArrList;
import buildings.lists.ArrayListNode;

import java.io.Serializable;
import java.util.Iterator;

public class OfficeFloor implements Floor, Serializable, Cloneable, Iterable<Space> {

    private ArrList _spaces;


    private ArrayListNode getSpaceNode(int num) throws SpaceIndexOutOfBoundsException {
        if(num < 0 || num >= _spaces.getSize()){
            throw new SpaceIndexOutOfBoundsException(num, _spaces.getSize());
        }
        return _spaces.getArrayListNode(num);
    }

    private void addSpaceNode(Space space, int num) throws SpaceIndexOutOfBoundsException{
        if(num < 0 || num > _spaces.getSize()){
            throw new SpaceIndexOutOfBoundsException(num, _spaces.getSize() + 1);
        }
        _spaces.insert(space,num);
    }

    private void removeSpaceNode(int num) throws SpaceIndexOutOfBoundsException{
        if(num < 0 || num >= _spaces.getSize()){
            throw new SpaceIndexOutOfBoundsException(num, _spaces.getSize());
        }
        _spaces.removeAt(num);
    }

    public OfficeFloor(int officesNum){
        _spaces = new ArrList(officesNum);
    }

    public OfficeFloor(Space[] space){
        _spaces = new ArrList();
        for(int i = 0; i < space.length; ++i) {
            _spaces.push_back(space[i]);
        }
    }

    public int getSpacesNum(){
        return _spaces.getSize();
    }

    public double getFloorSquare(){
        int genSquare = 0;
        for(int i = 0; i < _spaces.getSize(); ++i){
            genSquare += _spaces.getArrayListNode(i)._space.getSquare();
        }
        return genSquare;
    }

    public int getFloorRooms(){
        int genRooms = 0;
        for(int i = 0; i < _spaces.getSize(); ++i){
            genRooms += _spaces.getArrayListNode(i)._space.getRooms();
        }
        return genRooms;
    }

    public Space[] getSpacesArr(){
        Space[] arr = new Space[getSpacesNum()];
        for(int i = 0; i < arr.length; ++i){
            arr[i] = _spaces.getArrayListNode(i)._space;
        }
        return arr;
    }

    public Space getSpace(int num){
        return getSpaceNode(num)._space;
    }

    public void changeSpace(int num, Space space) throws SpaceIndexOutOfBoundsException {
        if(num < 0 || num >= _spaces.getSize()){
            throw new SpaceIndexOutOfBoundsException(num, _spaces.getSize());
        }
        for(int i = 0; i < _spaces.getSize(); ++i){
            if(i == num) _spaces.getArrayListNode(i)._space = space;
        }
    }

    public void addSpace(int num, Space space){
        addSpaceNode(space,num);
    }

    public void removeSpace(int num){
        removeSpaceNode(num);
    }

    public Space getBestSpace(){
        Space best = new Office();
        for(int i = 0; i < _spaces.getSize()-1; ++i){
            if(getSpacesArr()[i].getSquare() < getSpacesArr()[i + 1].getSquare()) best = getSpacesArr()[i + 1];
        }
        return best;
    }

    public String toString(){
        String s = new String("OfficeFloor" + "(" + this.getSpacesNum()) +", ";
        for(int i = 0 ; i < getSpacesNum(); ++i){
            s += getSpace(i).toString();
        }
        s = s + ")";
        return s;
    }

    public int hashCode(){
        int result = 0;
        for(int i = 0; i < getSpacesNum(); ++i){
            result += getSpacesNum() ^ getSpace(i).hashCode();
        }
        return result;
    }

    public boolean equals(Object object){
        boolean flag = false;
        if((object instanceof OfficeFloor)&&(this.getSpacesNum() == ((OfficeFloor) object).getSpacesNum())){
            for(int i = 0 ; i < getSpacesNum(); ++i){
                if(getSpace(i).equals(((OfficeFloor) object).getSpace(i)) == false){
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
            ((OfficeFloor)result)._spaces = (ArrList)this._spaces.clone();
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
        System.out.println("======Office Floor ======\n");
        for(int i = 0; i < _spaces.getSize(); ++i){
            getSpace(i).print();
        }
    }
}