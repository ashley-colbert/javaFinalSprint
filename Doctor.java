public class Doctor extends User{
  private String medicalLicenseNumber;
  private String specialization;

  public Doctor(int id, String firstName, String lastName, String email, String password, boolean isDoctor, String medicalLicenseNumber, String specialization) {
      super(id, firstName, lastName, email, password, isDoctor);
      this.medicalLicenseNumber = medicalLicenseNumber;
      this.specialization = specialization;
  }

  public Doctor(int id, String firstName, String lastName, String email, String password, boolean isDoctor) {
      this.medicalLicenseNumber = null;
      this.specialization = null;
  }

  public Doctor() {
      super();
      this.medicalLicenseNumber = null;
      this.specialization = null;
  }

  // Getters and setters for the new properties

  public String getMedicalLicenseNumber() {
      return medicalLicenseNumber;
  }

  public void setMedicalLicenseNumber(String medicalLicenseNumber) {
      this.medicalLicenseNumber = medicalLicenseNumber;
  }

  public String getSpecialization() {
      return specialization;
  }

  public void setSpecialization(String specialization) {
      this.specialization = specialization;
  }

  //to String
@Override
public String toString() {
  return "Doctor: " + "\n" +
  super.toString();
  // "License Number:" + medicalLicenseNumber + "\n" +
  // "Specialization: " + specialization;
}
}
