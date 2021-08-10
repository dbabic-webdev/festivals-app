package com.ftninformatika.jwd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.model.Festival;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Long>{
	
	Festival findOneById (Long id);
	
	Page <Festival> findAll (Pageable pageable);
	
	@Query("SELECT t FROM Festival t WHERE" +
			"(:naziv = NULL OR t.naziv LIKE :naziv) AND " + 
			"(:mestoId = NULL OR t.mesto.id = :mestoId)")
	Page<Festival> search(@Param("naziv") String naziv, @Param("mestoId") Long mestoId, Pageable pageable);

}
