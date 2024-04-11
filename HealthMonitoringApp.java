import java.util.ArrayList;
import java.util.List;

public class HealthMonitoringApp {
  
  private static UserDao userDao = new UserDao();



  public static void main(String[] args) {
    DatabaseConnection databaseConnection = new DatabaseConnection();
      UserDao userDao = new UserDao();


    List<User> userList = new ArrayList<>();

    User user2 = new User (4, "Ashley", "Colbert", "ashley@mail.com", "ashley123", false);

    for (User users : userList) {
      userDao.createUser(users);
    }
  }


  public static boolean loginUser(String email, String password) {
    //implement method to login user.
    User user = userDao.getUserByEmail(email);

    if (user != null) {
        // Compare the stored hashed password with the given password and return result
    }

    return false;

  }


  //to test the doctor portal
  
  public static void testDoctorPortal() {
    // Replace the doctorId with a valid ID from your database
    int doctorId = 1;

    // Add code to Fetch the doctor by ID

    // Add code to Fetch patients associated with the doctor

    // Add code to Fetch health data for the patient

  }




}
