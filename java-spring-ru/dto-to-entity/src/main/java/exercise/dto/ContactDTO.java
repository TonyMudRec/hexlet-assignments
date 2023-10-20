package exercise.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ContactDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private Date createdAt;
    private Date updatedAt;
}
