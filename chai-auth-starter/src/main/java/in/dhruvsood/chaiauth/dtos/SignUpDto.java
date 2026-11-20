package in.dhruvsood.chaiauth.dtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SignUpDto {


    private String email;


    private String name;

    private String password;
}
