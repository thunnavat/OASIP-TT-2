package sit.int221.oasipservice.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter @Setter
public class EventDTO {
    private String bookingName;
    private String bookingEmail;
    private Instant eventStartTime;
    private String eventNotes;
    private Integer eventCategoryId;
}
