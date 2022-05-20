package sit.int221.oasipservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
public class GetEventDTO {
    private Integer id;
    private String bookingName;
    private String bookingEmail;
    private Instant eventStartTime;
    private Integer eventDuration;
    private String eventNotes;
    private String eventCategoryName;
}
