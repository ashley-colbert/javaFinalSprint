import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * In this basic version of the
 * RecommendationSystem class, complete the generateRecommendations to take a
 * HealthData object as input and generates recommendations based on the user's
 * heart rate and step count.
 * You can also expand this class to include more health data analysis and
 * generate more specific
 * recommendations based on the user's unique health profile
 * NOTE:
 * To integrate this class into your application, you'll need to pass the
 * HealthData object to the generateRecommendations method
 * and store the generated recommendations in the recommendations table in the
 * database.
 */

public class RecommendationSystem {
        private static final int MIN_HEART_RATE = 60;
        private static final int MAX_HEART_RATE = 100;
        private static final int MIN_STEPS = 10000;

        public List<String> generateRecommendations(HealthData healthData) {
        List<String> recommendations = new ArrayList<>();

                // Analyze heart rate
        int heartRate = healthData.getHeartRate();
        if (heartRate < MIN_HEART_RATE) {
                recommendations.add("Your heart rate is lower than the recommended range. " +
                "Consider increasing your physical activity to improve your cardiovascular health.");
                }

        if (heartRate > MAX_HEART_RATE) {
                recommendations.add("Your heart rate is too high, if you are at rest this could be a sign of a serious medical condition, consider seeing a doctor. " + "If you are taking part in physical activity consider some yoga or meditation to lower your heart rate before continuing.");
                }
                // // Analyze steps
        int steps = healthData.getSteps();
        if (steps < MIN_STEPS) {
                recommendations.add("You're not reaching the recommended daily step count. " +
                "Try to incorporate more walking or other physical activities into your daily routine.");
                }

        return recommendations;
        }

        public boolean insertRecommendations(List<String> recommendations, HealthData healthData, Date date) {
        boolean bool = false;

        //SQL query
        String query = "INSERT INTO recommendations (user_id, recommendation_text, date) VALUES ( ?, ?, ?)";

        //Database connection

        try (Connection con = DatabaseConnection.getCon();
        PreparedStatement statement = con.prepareStatement(query)) {
                for (String recommendation : recommendations) {
                        statement.setInt(1, healthData.getUserId());
                        statement.setString(2,recommendation);
                        statement.setDate(3, date);
                        int upDatedRows = statement.executeUpdate();
                        if (upDatedRows !=0) {
                        bool = true;
                        }
                }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                return bool;
        
        }
}