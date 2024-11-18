package dev.sandip.bookmyshow.Controllers;

import dev.sandip.bookmyshow.Dtos.ResponseStatus;
import dev.sandip.bookmyshow.Dtos.SignUpRequestDto;
import dev.sandip.bookmyshow.Dtos.SignUpResponseDto;
import dev.sandip.bookmyshow.Models.User;
import dev.sandip.bookmyshow.Services.UserService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    public SignUpResponseDto signUp(SignUpRequestDto requestDto){
        SignUpResponseDto responseDto = new SignUpResponseDto();
        try{
            User user = userService.signUp(requestDto.getName(),
                                           requestDto.getEmail(),
                                           requestDto.getPassword());
            responseDto.setUser(user);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
