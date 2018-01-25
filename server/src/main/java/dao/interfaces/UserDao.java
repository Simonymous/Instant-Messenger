package dao.interfaces;

public interface UserDao {
    public void changeName(String name);
    public String getName();
    public int getId();
    public void changePassword(String password);
    public String getPassword();
    public char getStatus();
    public void setStatus(char status);
}
