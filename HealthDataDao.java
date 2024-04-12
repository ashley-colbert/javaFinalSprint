import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HealthDataDao {
  public boolean createHealthData(HealthData healthData) {
    boolean bool = false;
    /* insert health data into database */
    String data_query = "INSERT INTO health_data (user_id, weight, height, steps, heart_rate, date) VALUES (?, ?, ?, ?, ?, ?)";

    // Database logic to insert data using PREPARED Statement
    try {
      Connection con = DatabaseConnection.getCon();
      PreparedStatement statement = con.prepareStatement(data_query);
      // statement.setInt(1, healthData.getId());
      statement.setInt(1, healthData.getUserId());
      statement.setDouble(2, healthData.getWeight());
      statement.setDouble(3, healthData.getHeight());
      statement.setInt(4, healthData.getSteps());
      statement.setInt(5, healthData.getHeartRate());
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      java.sql.Date sqlDate = null;
      try {
        Date parsedDate = format.parse(healthData.getDate());
        sqlDate = new java.sql.Date(parsedDate.getTime());
      } catch (ParseException e) {
        System.err.println("Error parsing the date: "+ e.getMessage());
      }
      statement.setDate(6, sqlDate);
      int updatedRows = statement.executeUpdate();
      if (updatedRows != 0) {
        bool = true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return bool;
  }

  public HealthData getHealthDataById(int id) {
    /* get health data by id from database */

    // int health_id = 0;
    int userId = 0;
    double weight = 0;
    double height = 0;
    int steps = 0;
    int heartRate = 0;
    String date = null;

    // Prepare the SQL query
    String id_query = "SELECT * FROM health_data WHERE id = ?";

    // Database logic to get data by ID Using Prepared Statement
    try {
      Connection con = DatabaseConnection.getCon();
      PreparedStatement statement = con.prepareStatement(id_query);
      statement.setInt(1, id);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        // health_id = rs.getInt("id");
        userId = rs.getInt("user_id");
        weight = rs.getDouble("weight");
        height = rs.getDouble("height");
        steps = rs.getInt("steps");
        heartRate = rs.getInt("heart_rate");
        date = rs.getString("date");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return new HealthData(userId, weight, height, steps, heartRate, date);
  }

  public List<HealthData> getHealthDataByUserId(int userId) {
    List<HealthData> healthData = new ArrayList<>();
    // int id = 0;
    // int userID = 0;
    // double weight = 0;
    // double height = 0;
    // int steps = 0;
    // int heartRate = 0;
    // String date = null;

    // SQL query
    String data_query = "SELECT * FROM health_data WHERE user_id = ?";
    // Database logic to get data by ID Using Prepared Statement
    try {
      Connection con = DatabaseConnection.getCon();
      PreparedStatement statement = con.prepareStatement(data_query);
      statement.setInt(1, userId);
      ResultSet rs = statement.executeQuery();
      int count = 0;
      while (rs.next()) {

        // int id = rs.getInt("id");
        int userID = rs.getInt("user_id");
        double weight = rs.getDouble("weight");
        double height = rs.getDouble("height");
        int steps = rs.getInt("steps");
        int heartRate = rs.getInt("heart_rate");
        String date = rs.getString("date");
        HealthData data = new HealthData(userID, weight, height, steps, heartRate, date);
        healthData.add(data);
        count++;
      }
      System.out.println("Fetched" + count + "health data for" + userId);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return healthData;

  }

  public boolean updateHealthData(HealthData healthData) {
    boolean bool = false;

    String query = "UPDATE health_data SET user_id = ?, weight = ?, height = ? steps = ?, heart_rate = ?, date = ? WHERE id = ?";

    // database logic
    try {
      Connection con = DatabaseConnection.getCon();
      PreparedStatement statement = con.prepareStatement(query);
      statement.setInt(1, healthData.getUserId());
      statement.setDouble(2, healthData.getWeight());
      statement.setDouble(3, healthData.getHeight());
      statement.setInt(4, healthData.getSteps());
      statement.setInt(5, healthData.getHeartRate());
      statement.setString(6, healthData.getDate());
      int updatedRows = statement.executeUpdate();
      if (updatedRows != 0) {
        bool = true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return bool;

  }

  public boolean deleteHealthData(int id) {
    boolean bool = false;

    // delete health data from the database
    String delete_query = "DELETE FROM health_data WHERE id = ?";

    // database logic
    try {
      Connection con = DatabaseConnection.getCon();
      PreparedStatement statement = con.prepareStatement(delete_query);
      statement.setInt(1, id);
      int updatedRows = statement.executeUpdate();
      if (updatedRows != 0) {
        bool = true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return bool;

  }
};