package buildings;

public class InexchangeableSpacesException extends Error{
    InexchangeableSpacesException(){
        String msg = "Inexchangeable Spaces!\n Choose another indexes: ";
        throw new Error(msg);
    }
}
