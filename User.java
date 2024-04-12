public class User {
  private int id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private boolean isDoctor;


  //parameterized constructor
  public User(int id, String firstName, String lastName, String email, String password, boolean isDoctor) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.password = password;
      this.isDoctor = isDoctor;
  }

  //default constructor
  public User() {
      this.id = 0;
      this.firstName = null;
      this.lastName = null;
      this.email = null;
      this.password = null;
      this.isDoctor = false;
  }


  //getters and setters
  public int getId() {
      return id;
  }

  public void setId(int id) {
      this.id = id;
  }

  public String getFirstName() {
      return firstName;
  }

  public void setFirstName(String firstName) {
      this.firstName = firstName;
  }

  public String getLastName() {
      return lastName;
  }

  public void setLastName(String lastName) {
      this.lastName = lastName;
  }

  public String getEmail() {
      return email;
  }

  public void setEmail(String email) {
      this.email = email;
  }

  public String getPassword() {
      return password;
  }

  public void setPassword(String password) {
      this.password = password;
  }

  public boolean isDoctor() {
      return isDoctor;
  }

  public void setDoctor(boolean doctor) {
      isDoctor = doctor;
  }

//to string
@Override
public String toString() {
  return "User: " + "\n" +
  "ID: " + id + "\n" +
  "First Name: " + firstName + "\n" +
  "Last Name: " + lastName + "\n" +
  "Email: " + email + "\n" +
  "Is Doctor: " + isDoctor;
  }
}