package sit.int221.oasipservice.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.Instant;

@Getter @Setter
public class CreateEventDTO {
    @NotBlank(message = "Booking name is empty")
    @Size(max = 100, message = "Booking name is more than 100 char")
    private String bookingName;

    @NotBlank(message = "Booking Email is empty")
    @Email(message = "Email is not correctly")
    private String bookingEmail;

    @NotNull(message = "Start time is empty")
    @Future(message = "Start time is not in the future")
    private Instant eventStartTime;

    @Size(max = 500, message = "Event notes is more than 500 char")
    private String eventNotes;

    @NotNull(message = "Event Category is empty")
    private Integer eventCategoryId;
}
