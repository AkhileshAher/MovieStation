package dev.akhileshaher.moviestation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {

    @NotBlank(message = "Username cant be blank")
    private String username;

    @NotBlank(message = "Password is Required")
    private String password;

}
