package net.groot.data;

// JpaRepository extends PagingAndSortingRepository which in turn extends CrudRepository.
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrootRepository extends JpaRepository<Groot, Long> {

}
