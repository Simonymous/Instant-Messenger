package builder;

import model.classes.*;
import model.interfaces.*;

import java.util.Date;

/**
 * Generets implementations of the Model Classes
 */
public class ModelObjectBuilder {
    public static User getUserObject(){
        return new UserImpl();
    }

    public static User getUserObject(String username, String password){
        return new UserImpl(username, password);
    }

    //TODO Modelbuilder Group erstellen
    //TODO Modelbuilder Group_User erstellen
    //TODO Modelbuilder Message erstellen
}