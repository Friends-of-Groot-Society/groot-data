package net.groot.repositories;

// JpaRepository extends PagingAndSortingRepository which in turn extends CrudRepository.
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.groot.beans.Media;

public interface MediaRepository extends JpaRepository<Media, Long> {

}
