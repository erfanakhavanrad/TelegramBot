import com.google.common.annotations.VisibleForTesting;

public class Users {

    private int id;
    private String userName;
    private int number;

    public Users(int id, String userName, int number) {
        this.id = id;
        this.userName = userName;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", number=" + number +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
