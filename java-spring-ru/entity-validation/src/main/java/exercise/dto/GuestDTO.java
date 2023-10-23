package exercise.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
public class GuestDTO {
    private long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String clubCard;

    private Date cardValidUntil;

    private Date createdAt;
}
