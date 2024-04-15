package superfrog_scheduler.backend.request;

import jakarta.persistence.*;
import main.java.superfrog_scheduler.backend.student.Student;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name= "request")
public class Request implements Serializable {

    @Id
    private String id;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @Column(name = "eventTitle")
    private String eventTitle;

    @Column(name = "status")
    private RequestStatus status;

    @Column(name = "sid")
    private String sup_id;

    @Column(name = "cid")
    private String cust_id;

    @Column(name = "specialInstructions")
    private String specialInstructions;

    @Column(name = "other_orgs")
    private String other_orgs;

    private LocalDate eventDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private String eventType;

    private String clientFName;

    private String clientLName;

    private String clientPhoneNumber;

    private String clientEmail;

    private String orgName;

    private Double milesFromTCU;

    private String expenses;

    @ManyToOne
    private Student superfrog;

    //Constructors
    public Request(){
    }

    //Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id){this.id = id;}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getSup_id() {
        return sup_id;
    }

    public void setSup_id(String sup_id) {
        this.sup_id = sup_id;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public String getOther_orgs() {
        return other_orgs;
    }

    public void setOther_orgs(String other_orgs) {
        this.other_orgs = other_orgs;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getClientFName() {
        return clientFName;
    }

    public void setClientFName(String clientFName) {
        this.clientFName = clientFName;
    }

    public String getClientLName() {
        return clientLName;
    }

    public void setClientLName(String clientLName) {
        this.clientLName = clientLName;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Double getMilesFromTCU() {
        return milesFromTCU;
    }

    public void setMilesFromTCU(Double milesFromTCU) {
        this.milesFromTCU = milesFromTCU;
    }

    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }

    public Student getSuperfrog() {
        return superfrog;
    }

    public void setSuperfrog(Student superfrog) {
        this.superfrog = superfrog;
    }
}
