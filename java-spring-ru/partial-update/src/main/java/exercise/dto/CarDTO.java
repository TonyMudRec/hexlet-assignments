package exercise.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
public class CarDTO {
    private long id;
    private String model;
    private String manufacturer;
    private int enginePower;
    private Date updatedAt;
    private Date createdAt;
}
