package superfrog_scheduler.backend;

import jakarta.persistence.*;
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "performance")
    private float performance_rating;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPerformance_rating() {
        return performance_rating;
    }

    public void setPerformance_rating(float performance_rating) {
        this.performance_rating = performance_rating;
    }
}
