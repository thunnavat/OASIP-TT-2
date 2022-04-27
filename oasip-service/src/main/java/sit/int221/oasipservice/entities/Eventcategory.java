package sit.int221.oasipservice.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter @Setter
@Entity @Table(name = "eventcategories")
public class Eventcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventCategoryId", nullable = false)
    private Integer id;

    @Column(name = "eventCategoryName", nullable = false, length = 100)
    private String eventCategoryName;

    @Column(name = "eventCategoryDescription", length = 500)
    private String eventCategoryDescription;

    @Column(name = "eventDuration", nullable = false)
    private Integer eventDuration;

    @OneToMany(mappedBy = "eventCategory")
    private Set<sit.int221.oasipservice.entities.Event> events = new LinkedHashSet<>();

}