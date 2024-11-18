package dev.sandip.bookmyshow.Dtos;

import dev.sandip.bookmyshow.Models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDto {
    private User user;
    private ResponseStatus responseStatus;
}
