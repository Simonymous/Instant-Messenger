package main;

import dao.classes.UserDaoImpl;
import model.classes.UserImpl;

public class Main {
    public static void main(String[] args){
        UserDaoImpl userDao = new UserDaoImpl();
        UserImpl user = new UserImpl("Christian", "sicher");

        userDao.addNewUser(user);
    }
}
