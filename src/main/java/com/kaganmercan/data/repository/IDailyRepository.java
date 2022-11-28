package com.kaganmercan.data.repository;

import com.kaganmercan.data.entity.DailyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDailyRepository extends JpaRepository<DailyEntity,Long> {

}
