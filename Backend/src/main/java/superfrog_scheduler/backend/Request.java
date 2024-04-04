package superfrog_scheduler.backend;

@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String address;
    private String description;
    //private Date date
    private String event;
    private String status;
    private int sup_id;
    private int cust_id;
    private String info;
    private String other_orgs;

    public Request(String address, String description, String event, String status, int sup_id, int cust_id, String info, String other_orgs) {
        this.address = address;
        this.description = description;
        this.event = event;
        this.status = status;
        this.sup_id = sup_id;
        this.cust_id = cust_id;
        this.info = info;
        this.other_orgs = other_orgs;
    }

    public int getId() {
        return id;
    }

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

    public int getSup_id() {
        return sup_id;
    }

    public void setSup_id(int sup_id) {
        this.sup_id = sup_id;
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
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
