package dev.sandip.bookmyshow.Repositories;

import dev.sandip.bookmyshow.Models.Show;
import dev.sandip.bookmyshow.Models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
   public List<ShowSeatType> findAllByShow(Show show);
}
