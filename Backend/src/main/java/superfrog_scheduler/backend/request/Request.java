package superfrog_scheduler.backend.request;

import jakarta.persistence.*;

@Entity
@Table(name= "request")
public class Request {

    @Id
    private String id;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;
    //private Date date

    @Column(name = "event")
    private String event;

    @Column(name = "status")
    private String status;

    @Column(name = "sid")
    private String sup_id;

    @Column(name = "cid")
    private String cust_id;

    @Column(name = "info")
    private String info;

    @Column(name = "other_orgs")
    private String other_orgs;

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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getOther_orgs() {
        return other_orgs;
    }

    public void setOther_orgs(String other_orgs) {
        this.other_orgs = other_orgs;
    }
}
