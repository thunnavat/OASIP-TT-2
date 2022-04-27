package sit.int221.oasipservice.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter @Setter
@Entity @Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventId", nullable = false)
    private Integer id;

    @Column(name = "bookingName", nullable = false, length = 100)
    private String bookingName;

    @Column(name = "bookingEmail", nullable = false, length = 100)
    private String bookingEmail;

    @Column(name = "eventStartTime", nullable = false)
    private Instant eventStartTime;

    @Column(name = "eventDuration", nullable = false)
    private Integer eventDuration;

    @Column(name = "eventNotes", length = 500)
    private String eventNotes;

    @ManyToOne
    @JoinColumn(name = "eventCategoryId", nullable = false)
    private Eventcategory eventCategory;

}
