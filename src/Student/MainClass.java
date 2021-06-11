package Student;

import Dao.DataBaseConnection;

/**
 * @author Dyg
 */
public class MainClass {
    public static DataBaseConnection connection = new DataBaseConnection();

    public static void main(String[] args) {
        Login login = new Login();
    }
}   