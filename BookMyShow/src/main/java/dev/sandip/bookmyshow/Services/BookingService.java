package dev.sandip.bookmyshow.Services;

import dev.sandip.bookmyshow.Exceptions.ShowNotFoundException;
import dev.sandip.bookmyshow.Exceptions.UserNotFoundException;
import dev.sandip.bookmyshow.Models.*;
import dev.sandip.bookmyshow.Repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculatorService priceCalculatorService;
    private BookingRepository bookingRepository;

    public BookingService(UserRepository userRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          PriceCalculatorService priceCalculatorService,
                          BookingRepository bookingRepository){
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculatorService = priceCalculatorService;
        this.bookingRepository = bookingRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, Long showId, List<Long> showSeatIds) throws UserNotFoundException, ShowNotFoundException {

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with id :"+userId+" not found.");
        }
        User user = optionalUser.get();


        Optional<Show> optionalShow = showRepository.findById(showId);
        if(optionalShow.isEmpty()){
            throw new ShowNotFoundException("Show with id :"+showId+" not found.");
        }
        Show show = optionalShow.get();


        List<ShowSeat> showSeatList = showSeatRepository.findAllById(showSeatIds);
        for(ShowSeat showSeat : showSeatList){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.EMPTY)){
                throw new RuntimeException("The show-seat with id: "+showSeat.getId()+"is not available.");
            }
        }
        for(ShowSeat showSeat : showSeatList){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeatRepository.save(showSeat);
        }


        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShowSeats(showSeatList);
        booking.setBookingStatus(BookingStatus.Pending);
        booking.setAmount(priceCalculatorService.calculatePrice(showSeatList));
        booking.setCreatedAt(new Date());

        Booking savedBooking = bookingRepository.save(booking);

        return savedBooking;
    }
}
