public class HealthData {
  private int id;
  private int userId;
  private double weight;
  private double height;
  private int steps;
  private int heartRate;
  private String date;

  // Constructors

  public HealthData(int id, int userId, double weight, double height, int steps, int heartRate, String date) {
      this.id = id;
      this.userId = userId;
      this.weight = weight;
      this.height = height;
      this.steps = steps;
      this.heartRate = heartRate;
      this.date = date;
  }

  public HealthData() {
      this.id = 0;
      this.userId = 0;
      this.weight = 0;
      this.height = 0;
      this.steps = 0;
      this.heartRate = 0;
      this.date = null;
  }

//getters and setters:

  public int getId() {
      return id;
  }
  public void setID(int id) {
      this.id = id;
  }

  public int getUserId() {
      return userId;
  }

  public void setUserId(int userId) {
      this.userId=userId;
  }

  public double getWeight() {
      return weight;
  }

  public void setWeight( double weight) {
      this.weight=weight;
  }

  public double getHeight() {
      return height;
  }

  public void setHeight(double height) {
      this.height = height;
  }

  public int getSteps() {
      return steps;
  }

  public void setSteps(int steps) {
      this.steps = steps;
  }

  public int getHeartRate() {
      return heartRate;
  }

  public void setHeartRate(int heartRate) {
      this.heartRate = heartRate;
  }

  public String getDate() {
      return date;
  }

  public void setDate(String date) {
      this.date = date;
  }

  //to string

  public String toString() {
      return "Health Data: " + "\n" +
      "Weight: " + weight + "\n" +
      "Height: " + height + "\n" +
      "Steps: " + steps + "\n" +
      "Heart Rate: " + heartRate + "\n" +
      "Date: " + date;
  }
}