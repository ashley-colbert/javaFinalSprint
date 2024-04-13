import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HealthMonitoringApp {

  private static UserDao userDao = new UserDao();

  public static void main(String[] args) {

    Scanner pause = new Scanner(System.in);

    DatabaseConnection databaseConnection = new DatabaseConnection();

    UserDao userDao = new UserDao();
    List<User> userList = new ArrayList<>();

    // User user3 = new User (16, "John ", "Cole", "john@mail.com", "john123", false);
    // userList.add(user3);

    // User user4 = new User (15, "Andy", "Smith", "andy@mail.com", "andy123", true);
    // userList.add(user4);

    // User user5 = new User (18, "Jenny", "Star", "jenny@mail.com", "jenny123", false);
    // userList.add(user5);

    // User user6 = new User (, "Ashley", "Colbert", "ashley@mail.com", "ashley123", false);
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

    // for (User users : userList) {
    //   userDao.createUser(users);
    // }
    // System.out.println(userList);

    //adding health data to a user:

    // HealthDataDao healthDataDao = new HealthDataDao();
    // List<HealthData> dataList = new ArrayList<>();

    // HealthData data1 = new HealthData (15, 150, 70, 12000, 75, "2024-12-01");
    // dataList.add(data1);

    // HealthData data2 = new HealthData (15, 160, 70, 5000, 75, "2024-01-01");
    // dataList.add(data2);

    // HealthData data3 = new HealthData (18, 80, 60, 50000, 120, "2024-03-01");
    // dataList.add(data3);

    // pause.nextLine();
    // HealthData data4 = new HealthData (15, 160, 65, 5000, 75, "2024-01-01");
    // dataList.add(data4);

    // HealthData data5 = new HealthData (15, 150, 70, 12000, 75, "2024-12-01");
    // dataList.add(data5);

    // HealthData data6 = new HealthData (15, 160, 70, 5000, 75, "2024-01-01");
    // dataList.add(data6);

    // for (HealthData data : dataList) {
    //   healthDataDao.createHealthData(data);
    //   System.out.println(data.toString());
    // }

    // RecommendationSystem recommendationSystem = new RecommendationSystem();

    // List<String> recommendations = recommendationSystem.generateRecommendations(data3);
    // for (String recommendation : recommendations) {
    //   System.out.println(recommendation.toString());
    // }
  
    // boolean success = recommendationSystem.insertRecommendations(18, recommendations, Date.valueOf("2024-03-01"));
    // if (success) {
    //   System.out.print("Recommendations successfully added");
    // } else {
    //   System.out.print("Failed to insert recommendations");
    // }


  // to assign a doctor to a patient
    DoctorPortalDao dao = new DoctorPortalDao();

    String medicalLicenseNumber = "L12345";
    String specialization = "Pediatrics";
    int userId = 21;

    Doctor newDoctor = new Doctor(userId, "Doctor", "Williams", "doctorwilliams@gmail.com", "doc123", true, medicalLicenseNumber, specialization);

    boolean isDoctorAdded = dao.addDoctor(newDoctor);
    if (isDoctorAdded) {
      System.out.println(newDoctor);
    } else {
      System.out.println("Failed to add new doctor.");
    }
    
    pause.nextLine();

// dao.addDocPatRelation(22, 15);

  //to call the function to test the login a user
    // testLoginUser();

   //to call the function to test the doctor portal
    // testDoctorPortal();

    //Add medical reminders to database
    // MedicineReminderManager medicineReminderManager = new MedicineReminderManager();
    // List<MedicineReminder> reminderList = new ArrayList<>();
   
    // MedicineReminder reminder1 = new MedicineReminder(15, "Advil", "2 pills", "twice daily", Date.valueOf("2023-02-01"), Date.valueOf("2024-03-04"), Time.valueOf("10:20:00"));
    // reminderList.add(reminder1);

    // MedicineReminder reminder2 = new MedicineReminder(15, "Omeprazole", "2 pills", "once daily", Date.valueOf("2023-04-11"), Date.valueOf("2024-04-24"), Time.valueOf("18:20:00"));

    // reminderList.add(reminder2);
    // medicineReminderManager.addReminder(reminder2);

    // medicineReminderManager.updateReminder(reminder1);

    // medicineReminderManager.deleteReminder(2);
    // System.out.println(reminderList);

    // testMedicineReminderManager();


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
    int userId = 15;

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
