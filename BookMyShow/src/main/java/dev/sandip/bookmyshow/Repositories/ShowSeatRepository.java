package dev.sandip.bookmyshow.Repositories;

import dev.sandip.bookmyshow.Models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    public List<ShowSeat> findAllById(Iterable<Long> showSeats);
    @Override
    ShowSeat save(ShowSeat showSeat);
}
