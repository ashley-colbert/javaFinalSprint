import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HealthMonitoringApp {
  Scanner pause = new Scanner(System.in);
  private static UserDao userDao = new UserDao();
  


  public static void main(String[] args) {



    DatabaseConnection databaseConnection = new DatabaseConnection();

    UserDao userDao = new UserDao();
    List<User> userList = new ArrayList<>();

    // User user3 = new User (16, "John ", "Cole", "john@mail.com", "john123", false);
    // userList.add(user3);

    // User user4 = new User (15, "Andy", "Smith", "andy@mail.com", "andy123", true);
    // userList.add(user4);

    // User user5 = new User (15, "Ashley", "Colbert", "ashley@mail.com", "ashley123", false);
    // userList.add(user5);

    // User user6 = new User (15, "Ashley", "Colbert", "ashley@mail.com", "ashley123", false);
    // userList.add(user6);

    // User user7 = new User (15, "Ashley", "Colbert", "ashley@mail.com", "ashley123", false);
    // userList.add(user7);

    // User user8 = new User (15, "Ashley", "Colbert", "ashley@mail.com", "ashley123", false);
    // userList.add(user8);

    // User user9 = new User (15, "Ashley", "Colbert", "ashley@mail.com", "ashley123", false);
    // userList.add(user9);

    // User user10 = new User (15, "Ashley", "Colbert", "ashley@mail.com", "ashley123", false);
    // userList.add(user10);

    // User user11 = new User (15, "Ashley", "Colbert", "ashley@mail.com", "ashley123", false);
    // userList.add(user11);

    // User user12 = new User (15, "Ashley", "Colbert", "ashley@mail.com", "ashley123", false);
    // userList.add(user12);

    for (User users : userList) {
      userDao.createUser(users);
    }
    System.out.println(userList);

    //adding health data to a user:

    // HealthDataDao healthDataDao = new HealthDataDao();
    // List<HealthData> dataList = new ArrayList<>();

    // HealthData data1 = new HealthData (15, 150, 70, 12000, 75, "2024-12-01");
    // dataList.add(data1);

    // HealthData data2 = new HealthData (15, 160, 70, 5000, 75, "2024-12-01");
    // dataList.add(data2);

    // for (HealthData data : dataList) {
    //   healthDataDao.createHealthData(data);
    //   System.out.println(data.toString());
    // }

  // to assign a doctor to a patient
    // DoctorPortalDao dao = new DoctorPortalDao();
    // dao.addDocPatRelation(17, 15);

  //to call the function to test the login a user
    // testLoginUser();

   //to call the function to test the doctor portal
    testDoctorPortal();
  }



  public static boolean loginUser(String email, String password) {
    User user = userDao.getUserByEmail(email);

    if (user != null) {
      return userDao.verifyPassword(email, password);
    }

    return false;

  }


  //to test the doctor portal

  public static void testDoctorPortal() {
    DoctorPortalDao doctorPortalDao = new DoctorPortalDao();
    int doctorId = 17;
    int patientId = 15;
    int userId = 15;

    // Add code to Fetch the doctor by ID
    // Doctor doctor = doctorPortalDao.getDoctorById(doctorId);
    // System.out.println(doctor);

    // Add code to Fetch patients associated with the doctor
    List<User> patients = doctorPortalDao.getPatientsByDoctorId(doctorId);
    System.out.println("Patients associated with this Doctor ID " + doctorId + ":");
    for (User patient : patients) {
        System.out.println(patient);
    }

    // Add code to Fetch health data for the patient
    List<HealthData> healthData = doctorPortalDao.getHealthDataByPatientId(userId);
    for (HealthData data: healthData) {
    System.out.println("Health data: " + data);
    }
  }

  public static void testLoginUser() {
    Scanner input = new Scanner(System.in);
    
    System.out.println("Please enter your email");
    String userEmail = input.nextLine();
    System.out.println("Please enter your password");
    String userPassword = input.nextLine();

    boolean loginSuccess = loginUser(userEmail, userPassword);

    if (loginSuccess) {
        System.out.println("Login successful for: " + userEmail);
    } else {
        System.out.println("Incorrect email or password. Please try again.");
    }
    input.close();
}


}
