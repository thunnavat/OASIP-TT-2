package sit.int221.oasipservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
public class UpdateEventDTO {
    private Instant eventStartTime;
    private String eventNotes;
}
