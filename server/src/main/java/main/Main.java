package main;

import dao.classes.UserDaoImpl;
import model.classes.UserImpl;
import service.classes.UserServiceImpl;
import service.interfaces.UserService;

public class Main {
    public static void main(String[] args){
//        UserDaoImpl userDao = new UserDaoImpl();
//        UserImpl user = new UserImpl("Christian", "sicher");
//
//        userDao.addNewUser(user);
//        user.setUserId(userDao.getUserByName(user).getUserId());
//        user.setPassword("test");
//        userDao.changePasword(user);
//        userDao.removeUser(user);

        UserService user = new UserServiceImpl();
        user.changeUserName("Simon", "Test");
    }
}
