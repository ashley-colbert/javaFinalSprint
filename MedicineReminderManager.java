import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
// import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MedicineReminderManager {
  private List<MedicineReminder> reminders;

  public MedicineReminderManager() {
      this.reminders = new ArrayList<>();
  }

  //to add a reminder to the database
  public void addReminder(MedicineReminder reminder) {
    String insertQuery = "INSERT INTO medicine_reminders (user_id, medicine_name, dosage, schedule, start_date, end_date, reminder_time) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (Connection con = DatabaseConnection.getCon();
        PreparedStatement statement = con.prepareStatement(insertQuery)) {
        statement.setInt(1, reminder.getUserId());
        statement.setString(2, reminder.getMedicineName());
        statement.setString(3, reminder.getDosage());
        statement.setString(4, reminder.getSchedule());
        statement.setDate(5, reminder.getStartDate());
        statement.setDate(6, reminder.getEndDate());
        statement.setTime(7, reminder.getReminderTime());
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

//to update a reminder in the database
public void updateReminder(MedicineReminder reminder) {
  String updateQuery = "UPDATE medicine_reminders SET medicine_name = ?, dosage = ?, schedule = ?, start_date = ?, end_date = ?, reminder_time = ? WHERE id = ?";
  try (Connection con = DatabaseConnection.getCon();
      PreparedStatement statement = con.prepareStatement(updateQuery)) {
      statement.setString(1, reminder.getMedicineName());
      statement.setString(2, reminder.getDosage());
      statement.setString(3, reminder.getSchedule());
      statement.setDate(4, reminder.getStartDate());
      statement.setDate(5, reminder.getEndDate());
      statement.setTime(6, reminder.getReminderTime());
      statement.setInt(7, reminder.getId());
      statement.executeUpdate();
  } catch (SQLException e) {
      e.printStackTrace();
  }
}


//to delete a reminder from the database
public void deleteReminder(int reminderId) {
  String deleteQuery = "DELETE FROM medicine_reminders WHERE id = ?";
  try (Connection con = DatabaseConnection.getCon();
      PreparedStatement statement = con.prepareStatement(deleteQuery)) {
      statement.setInt(1, reminderId);
      statement.executeUpdate();
  } catch (SQLException e) {
      e.printStackTrace();
  }
}


  public List<MedicineReminder> getRemindersForUser(int userId) {
      List<MedicineReminder> userReminders = new ArrayList<>();
              //SQL query
        String remind_query = "SELECT * FROM medicine_reminders WHERE user_id = ?";
          // Database logic to get data by ID Using Prepared Statement
            try {
                Connection con = DatabaseConnection.getCon();
                PreparedStatement statement = con.prepareStatement(remind_query);
                statement.setInt(1, userId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int userID = rs.getInt("user_id");
                    String medicineName = rs.getString("medicine_name");
                    String dosage = rs.getString("dosage");
                    String schedule = rs.getString("schedule");
                    Date startDate = rs.getDate("start_date");
                    Date endDate = rs.getDate("end_date");
                    Time reminderTime = rs.getTime("reminderTime");

                    MedicineReminder reminder = new MedicineReminder(id, userID, medicineName, dosage, schedule, startDate, endDate, reminderTime);
                    userReminders.add(reminder);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

      return userReminders;
  }

  public List<MedicineReminder> getDueReminders(int userId) {
      List<MedicineReminder> dueReminders = new ArrayList<>();
      LocalDate nowD = LocalDate.now();
      LocalTime nowT = LocalTime.now();

      String query = "SELECT * FROM medicine_reminders WHERE user_id = ? AND start_date <= ? AND end_date >= ?";
      try(Connection con = DatabaseConnection.getCon();
      PreparedStatement statement = con.prepareStatement(query)) {
        statement.setInt(1, userId);
        statement.setDate(2, Date.valueOf(nowD));
        statement.setDate(3, Date.valueOf(nowD));

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
          int id = rs.getInt("id");
          String medicineName = rs.getString("medicine_name");
          String dosage = rs.getString("dosage");
          String schedule = rs.getString("schedule");
          Date startDate = rs.getDate("start_date");
          Date endDate = rs.getDate("end_date");
          Time reminderTime = rs.getTime("reminder_time");

          LocalDateTime reminderDateTime = LocalDateTime.of(startDate.toLocalDate(), reminderTime.toLocalTime());

          if (!reminderDateTime.isAfter(LocalDateTime.of(nowD, nowT))) {
            MedicineReminder reminder = new MedicineReminder(id, userId, medicineName, dosage, schedule, startDate, endDate, reminderTime);
            dueReminders.add(reminder);
        }
        }

      } catch (SQLException e) {
        e.printStackTrace();
      }

      return dueReminders;
  }
}
