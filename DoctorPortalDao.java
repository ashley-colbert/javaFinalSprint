import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorPortalDao {
    private UserDao userDao;
    private HealthDataDao healthDataDao;

   // Complete all these methods and add more as needed


    public DoctorPortalDao() {
        userDao = new UserDao();
        healthDataDao = new HealthDataDao();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public HealthDataDao getHealthDataDao() {
        return healthDataDao;
    }

    public void setHealthDataDao(HealthDataDao healthDataDao) {
        this.healthDataDao = healthDataDao;
    }

    public Doctor getDoctorById(int doctorId) {

      // Implement this method
      int id = 0;
      String firstName = null;
      String lastName = null;
      String email = null;
      String password = null;
      boolean is_doctor = true;

      //SQL query

      String doc_query = "SELECT * FROM users WHERE id = ?";

      // Database logic to get data by ID Using Prepared Statement
      try {
          Connection con = DatabaseConnection.getCon();
          PreparedStatement statement = con.prepareStatement(doc_query);
          statement.setInt(1, doctorId);
          ResultSet rs = statement.executeQuery();
          while (rs.next()) {
              id = rs.getInt("id");
              firstName = rs.getString("first_name");
              lastName = rs.getString("last_name");
              email = rs.getString("email");
              password = rs.getString("password");
              is_doctor = rs.getBoolean("is_doctor");
          }
      } catch (SQLException e){
          e.printStackTrace();
      }
      return new Doctor(id, firstName, lastName, email, password, is_doctor);
  }

    public List<User> getPatientsByDoctorId(int doctorId) {
        List<User> patients = new ArrayList<>();
        int id = 0;
        String firstName = null;
        String lastName = null;
        String email = null;
        String password = null;
        boolean is_doctor = true;

        //SQL query
        String pat_query = "SELECT users.*\n" + //
                "FROM users \n" + //
                "INNER JOIN doctor_patient ON users.id = doctor_patient.patient_id\n" + //
                "WHERE doctor_patient.doctor_id = ?";
        // Database logic to get data by ID Using Prepared Statement
        try {
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(pat_query);
            statement.setInt(1,doctorId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                email = rs.getString("email");
                password = rs.getString("password");
                is_doctor = rs.getBoolean("is_doctor");
            User patient = new User(id, firstName, lastName, email, password, is_doctor);
            patients.add(patient);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return patients;

    }

    public List<HealthData> getHealthDataByPatientId(int patientId) {
        List<HealthData> healthData = new ArrayList<>();
        // int id = 0;
        int userId = 0 ;
        double weight = 0;
        double height = 0;
        int steps = 0;
        int heartRate = 0;
        String date = null;

        //SQL query
        String data_query = "SELECT * FROM health_data WHERE user_id = ?";
            // Database logic to get data by ID Using Prepared Statement
            try {
                Connection con = DatabaseConnection.getCon();
                PreparedStatement statement = con.prepareStatement(data_query);
                statement.setInt(1, patientId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    // id = rs.getInt("id");
                    userId = rs.getInt("user_id");
                    weight = rs.getDouble("weight");
                    height = rs.getDouble("height");
                    steps = rs.getInt("steps");
                    heartRate = rs.getInt("heart_rate");
                    date = rs.getString("date");
                HealthData data = new HealthData(userId, weight, height, steps, heartRate, date);
                healthData.add(data);
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            return healthData;
    
        }

  //to add doctor_patient relationship

  public void addDocPatRelation(int doctorId, int patientId) {
    String query ="INSERT INTO doctor_patient (doctor_id, patient_id) VALUES (?, ?)";

    try (Connection con = DatabaseConnection.getCon();
    PreparedStatement statement = con.prepareStatement(query)) {
      statement.setInt(1, doctorId);
      statement.setInt(2, patientId);
      statement.executeUpdate();
    } catch(SQLException e) {
      e.printStackTrace();
    }
  }

    }