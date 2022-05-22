package sit.int221.oasipservice.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter @Setter
public class UpdateEventCategoryDTO {
    @NotBlank(message = "Event category name is empty")
    @Size(max = 100, message = "Event categoryname is more than 100 char")
    private String eventCategoryName;

    @Size(max = 500, message = "Event categorydescription is more than 500 char")
    private String eventCategoryDescription;

    @NotNull(message = "Event duration is empty")
    @Min(value = 1, message = "Event duration is out of range")
    @Max(value = 480, message = "Event duration is out of range")
    private Integer eventDuration;
}
