import java.sql.Date;


public class MedicineReminder {
    private int id;
    private int userId;
    private String medicineName;
    private String dosage;
    private String schedule;
    private Date startDate;
    private Date endDate;

    // Parameterized constructor

    public MedicineReminder(int id, int userId, String medicineName, String dosage, String schedule, Date startDate, Date endDate) {
        this.id = id;
        this.userId = userId;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.schedule = schedule;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    //default constructor

    public MedicineReminder() {
        this.id = 0;
        this.userId = 0;
        this.medicineName = null;
        this.dosage = null;
        this.schedule = null;
        this.startDate = null;
        this.endDate = null;
    }

    //getters, and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId( int userId ) {
        this.userId = userId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName( String medicineName ) {
        this.medicineName = medicineName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage( String dosage ) {
        this.dosage = dosage;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule( String schedule ) {
        this.schedule = schedule;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void getStartDate( Date startDate ) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate( Date endDate ) {
        this.endDate = endDate;
    }

}