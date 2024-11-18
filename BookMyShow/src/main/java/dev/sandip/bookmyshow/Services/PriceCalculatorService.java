package dev.sandip.bookmyshow.Services;

import dev.sandip.bookmyshow.Models.ShowSeat;
import dev.sandip.bookmyshow.Models.ShowSeatType;
import dev.sandip.bookmyshow.Repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {
    private ShowSeatTypeRepository showSeatTypeRepository;

    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository){
        this.showSeatTypeRepository = showSeatTypeRepository;
    }
    public  int  calculatePrice(List<ShowSeat> showSeatList){
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(showSeatList.get(0).getShow());
        int amount = 0;
        for(ShowSeat showSeat : showSeatList){
            for(ShowSeatType showSeatType : showSeatTypes){
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())){
                    amount += showSeatType.getPrice();
                    break;
                }
            }
        }
    return amount;
    }
}
