package net.groot.data.repositories;

// JpaRepository extends PagingAndSortingRepository which in turn extends CrudRepository.
import org.springframework.data.jpa.repository.JpaRepository;

import net.groot.data.entities.Media;
import net.groot.data.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	void save(Media media);

}
