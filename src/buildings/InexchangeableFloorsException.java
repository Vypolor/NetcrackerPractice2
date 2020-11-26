package buildings;

public class InexchangeableFloorsException extends Error{
    InexchangeableFloorsException(){
        String msg = "Inexchangeable Floors!\n Choose another indexes.";
        throw new Error(msg);
    }
}
