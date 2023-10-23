package exercise.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ProductDTO {
    private long id;
    private String title;
    private int price;
    private long vendorCode;
    private Date createdAt;
    private Date updatedAt;
}
