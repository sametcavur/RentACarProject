package com.etiya.recap.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.etiya.recap.entities.concretes.Color;

@Repository
public interface ColorDao extends JpaRepository<Color, Integer>{

}
