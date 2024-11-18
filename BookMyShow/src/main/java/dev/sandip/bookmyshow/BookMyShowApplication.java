package dev.sandip.bookmyshow;

import dev.sandip.bookmyshow.Controllers.UserController;
import dev.sandip.bookmyshow.Dtos.SignUpRequestDto;
import dev.sandip.bookmyshow.Dtos.SignUpResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowApplication implements CommandLineRunner {
    @Autowired
    private UserController userController;
    public static void main(String[] args) {
        SpringApplication.run(BookMyShowApplication.class, args);
    }
@Override
    public void run(String... args) throws Exception {
    SignUpRequestDto requestDto = new SignUpRequestDto();
    requestDto.setName("Sandip Chatterjee");
    requestDto.setEmail("chatterjeesandip083@gmail.com");
    requestDto.setPassword("Sandip007@");

    requestDto.setName("Akash Bhattacharya");
    requestDto.setEmail("akashB2007@gmail.com");
    requestDto.setPassword("SAkashB007@");

    requestDto.setName("Sandip Chatterjee");
    requestDto.setEmail("sandipchatterjee09081998@gmail.com");
    requestDto.setPassword("Sandip007@");

    SignUpResponseDto responseDto = userController.signUp(requestDto);
    }
}
