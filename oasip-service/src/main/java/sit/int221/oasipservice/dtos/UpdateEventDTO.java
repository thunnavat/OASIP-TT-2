package sit.int221.oasipservice.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter @Setter
public class UpdateEventDTO {
    @NotNull(message = "Start time is empty")
    @Future(message = "Start time is not in the future")
    private Instant eventStartTime;

    @Size(max = 500, message = "Event notes is more than 500 char")
    private String eventNotes;
}
