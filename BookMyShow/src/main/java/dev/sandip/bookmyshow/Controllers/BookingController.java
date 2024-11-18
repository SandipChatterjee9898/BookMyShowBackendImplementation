package dev.sandip.bookmyshow.Controllers;

import dev.sandip.bookmyshow.Dtos.BookMovieRequestDto;
import dev.sandip.bookmyshow.Dtos.BookMovieResponseDto;
import dev.sandip.bookmyshow.Dtos.ResponseStatus;
import dev.sandip.bookmyshow.Exceptions.ShowNotFoundException;
import dev.sandip.bookmyshow.Exceptions.UserNotFoundException;
import dev.sandip.bookmyshow.Models.Booking;
import dev.sandip.bookmyshow.Services.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }
    public BookMovieResponseDto bookTicket(BookMovieRequestDto requestDto) throws UserNotFoundException, ShowNotFoundException {
        BookMovieResponseDto responseDto = new BookMovieResponseDto();
        try {
            Booking booking = bookingService.bookMovie(
                    requestDto.getUserId(),
                    requestDto.getShowId(),
                    requestDto.getShowSeatIds()
            );
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setBooking(booking);
        }
        catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
