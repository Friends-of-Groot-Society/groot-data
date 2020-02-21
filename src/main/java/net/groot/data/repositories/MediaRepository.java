package net.groot.data.repositories;

// JpaRepository extends PagingAndSortingRepository which in turn extends CrudRepository.
import org.springframework.data.jpa.repository.JpaRepository;

import net.groot.data.entities.Media;

public interface MediaRepository extends JpaRepository<Media, Long> {

}
