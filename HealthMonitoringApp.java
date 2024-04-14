import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HealthMonitoringApp {

  private static UserDao userDao = new UserDao();

  public static void main(String[] args) {

    Scanner pause = new Scanner(System.in);

    DatabaseConnection databaseConnection = new DatabaseConnection();

    //adding users to database
    UserDao userDao = new UserDao();
    List<User> userList = new ArrayList<>();

    User user1 = new User (94, "vinny", "Smith", "vinny123@mail.com", "vinny123", false);
    userList.add(user1);

    // User user2 = new User (32, "Andy", "Jones", "andy@gmail.com", "andy123", false);
    // userList.add(user2);

    // User user3 = new User (33, "James", "Light", "james@mgail.com", "james123", false);
    // userList.add(user3);

    // User user4 = new User (34, "Asha", "Collins", "asha@mail.com", "asha123", false);
    // userList.add(user4);

    // User user5 = new User (35, "Elsa", "Frost", "cold@mail.com", "elsa123", false);
    // userList.add(user5);

    // User user6 = new User (36, "Eva", "Manny", "eva@mail.com", "eva123", false);
    // userList.add(user6);

    // User user7 = new User (37, "Lennon", "Campbell", "lenny@mail.com", "lennon123", false);
    // userList.add(user7);

    // User user8 = new User (38, "Grey", "Lennon", "grey@mail.com", "grey123", false);
    // userList.add(user8);

    // User user9 = new User (39, "Amanda", "Tealy", "amanda@mail.com", "amanda123", false);
    // userList.add(user9);

    // User user10 = new User (40, "Chloe", "Colbert", "chloe@mail.com", "chloe123", false);
    // userList.add(user10);

    for (User users : userList) {
      userDao.createUser(users);
    }
    System.out.println(userList);

pause.nextLine();


  // to add a new doctor
    DoctorPortalDao dao = new DoctorPortalDao();

    String medicalLicenseNumber = "LN0920";
    String specialization = "Family doctor";
    int userId = 95;

    Doctor newDoctor = new Doctor(userId, "Bobby", "Sheen", "bobby@hotmail.com", "bobby123", true, medicalLicenseNumber, specialization);

    boolean isDoctorAdded = dao.addDoctor(newDoctor);
    if (isDoctorAdded) {
      System.out.println(newDoctor);
    } else {
      System.out.println("Failed to add new doctor.");
    }

  pause.nextLine();

//to add doctor patient relationship
dao.addDocPatRelation(95, 94);



pause.nextLine();

//to call the function to test the login a user

  testLoginUser();


    //adding health data to a user:

    HealthDataDao healthDataDao = new HealthDataDao();
    List<HealthData> dataList = new ArrayList<>();

    HealthData data1 = new HealthData (94, 140, 60, 5000, 125, "2023-12-01");
    dataList.add(data1);


    for (HealthData data : dataList) {
      healthDataDao.createHealthData(data);
      System.out.println(data.toString());
    }

    RecommendationSystem recommendationSystem = new RecommendationSystem();

    List<String> recommendations = recommendationSystem.generateRecommendations(data1);
    for (String recommendation : recommendations) {
      System.out.println(recommendation.toString());
    }
  
    boolean bool = recommendationSystem.insertRecommendations(94, recommendations, Date.valueOf("2024-04-13"));
    if (bool) {
      System.out.print("Recommendations successfully added");
    } else {
      System.out.print("Failed to insert recommendations");
    };

   //to call the function to test the doctor portal
    testDoctorPortal();

    //Add medicine reminders to database
    MedicineReminderManager medicineReminderManager = new MedicineReminderManager();
    List<MedicineReminder> reminderList = new ArrayList<>();

    MedicineReminder reminder1 = new MedicineReminder(94, "Advil", "2 pills", "twice daily", Date.valueOf("2023-02-01"), Date.valueOf("2024-03-04"), Time.valueOf("10:20:00"));
    reminderList.add(reminder1);

    MedicineReminder reminder2 = new MedicineReminder(94, "Omeprazole", "2 pills", "once daily", Date.valueOf("2023-04-11"), Date.valueOf("2024-04-24"), Time.valueOf("18:20:00"));
    reminderList.add(reminder2);

    System.out.println(reminderList);

    medicineReminderManager.deleteReminder(4);

    pause.close();
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
    int doctorId = 95;
    int patientId = 94;

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
    List<HealthData> healthData = doctorPortalDao.getHealthDataByPatientId(patientId);
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

  public static void testMedicineReminderManager() {
    MedicineReminderManager medicineReminderManager = new MedicineReminderManager();
    int userId = 94;

    List<MedicineReminder> userReminders = medicineReminderManager.getRemindersForUser(userId);
    if (userReminders.isEmpty()) {
      System.out.println("No reminders found for " + userId);
    } else {
      System.out.println("Reminders for user: " + userId);
      for (MedicineReminder reminder : userReminders) {
        System.out.println(reminder);
      }
    }

    List<MedicineReminder> dueReminders = medicineReminderManager.getDueReminders(userId);
    if (dueReminders.isEmpty()) {
      System.out.println("No reminders due found for " + userId);
    } else {
      System.out.println("Reminders due for user: " + userId);
      for (MedicineReminder reminder : dueReminders) {
        System.out.println(reminder);
      }
    }

  }
}
