package prime.holding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import prime.holding.model.WorkPlace;

@Repository
public interface WorkPlaceRepository extends JpaRepository<WorkPlace, Long> {

}
