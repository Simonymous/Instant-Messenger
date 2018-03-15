package restClientTests;

import im.model.classes.UserImpl;
import im.model.classes.UserQueryResponseImpl;
import im.model.interfaces.User;
import im.model.interfaces.UserQueryResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import static org.junit.Assert.assertEquals;

import im.rest.exceptions.UserDoesNotExistException;
import im.rest.services.UserRestClientImpl;

import java.util.ArrayList;
import java.util.List;




class UserRestClientImplTest {
    UserRestClientImpl urci;

    @Before
    void init() {
        urci = new UserRestClientImpl();
    }

    @Test
    void testAddUser() {
        urci.addUser("testUser0", "testPassword0");
        urci.addUser("testUser1", "testPassword1");
        urci.addUser("testUser2", "testPassword2");
        urci.addUser("testUser3", "testPassword3");
        urci.addUser("otherUser4", "testPassword4");
        urci.addUser("otherUser5", "testPassword5");
        urci.addUser("otherUser6", "testPassword6");
        urci.addUser("otherUser7", "testPassword7");
        urci.addUser("testUser8", "testPassword8");
        urci.addUser("otherUser9", "testPassword9");
        urci.addUser("", "a");
        urci.addUser("a", "");
    }

    @Test
    void testUpdateUser() {
        urci.updateUser("1", new UserImpl("newUsername", "newPassword"));
    }


    @Test
    void testGetUserByName() {
        List<String> ids = new ArrayList<>();
        ids.add("4");
        ids.add("5");
        ids.add("6");
        ids.add("7");
        ids.add("9");
        UserQueryResponseImpl uqrExpected = new UserQueryResponseImpl();
        uqrExpected.setIds(ids);

        UserQueryResponse uqr = urci.getUserByName("other");

        assertEquals(uqrExpected, uqr);
    }

    @Test(expected = UserDoesNotExistException.class)
    void testGetUserByNameNotFound() {
        urci.getUserByName("Notexistinguser");
    }

    @Test
    void testGetAllUser() {
        List<String> ids = new ArrayList<>();
        ids.add("0");
        ids.add("1");
        ids.add("2");
        ids.add("3");
        ids.add("4");
        ids.add("5");
        ids.add("6");
        ids.add("7");
        ids.add("8");
        ids.add("9");
        UserQueryResponseImpl uqrExpected = new UserQueryResponseImpl();
        uqrExpected.setIds(ids);

        UserQueryResponse uqr = urci.getAllUser();

        assertEquals(uqrExpected, uqr);
    }

    @Test
    void testGetUserById() {
        User expectedUser = new UserImpl("otherUser6", "testPassword6");
        User user = urci.getUserById("6");

        assertEquals(expectedUser, user);
    }

    @Test(expected = UserDoesNotExistException.class)
    void testGetUserByIdNotExist() {
        User user = urci.getUserById("193");
    }

    @Test(expected = RuntimeException.class)
    void testGetUserByIdString() {
        User user = urci.getUserById("string");
    }

    @Test
    void testGetTheUserByName() {
        User expectedUser = new UserImpl("otherUser9", "testPassword9");
        User user = urci.getTheUserByName("otherUser9");

        assertEquals(expectedUser, user);
    }

    @Test(expected = UserDoesNotExistException.class)
    void testGetTheUserByNameNotExist() {
        User user = urci.getTheUserByName("Notexisting");
    }

    @Test
    void testGetGroupsOfUser() {
    }

    @Test
    void testAuthenticateUserTrue() {
        boolean auth = urci.authenticateUser("0", "testPassword0");

        assertEquals(true, auth);
    }

    @Test
    void testAuthenticateUserFalse() {
        boolean auth = urci.authenticateUser("3", "wrongPassword");

        assertEquals(false, auth);
    }

    @Test
    void testRemoveUser() {
        urci.removeUser("1");
    }

    @Test(expected = UserDoesNotExistException.class)
    void testRemoveUserNotExist() {
        urci.removeUser("1100");
    }

    @After
    void destroy() {
        urci = null;
    }
}