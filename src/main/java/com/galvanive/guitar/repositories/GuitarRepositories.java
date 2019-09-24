package com.galvanive.guitar.repositories;


import com.galvanive.guitar.entities.Guitar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuitarRepositories extends JpaRepository<Guitar, Long> {

}
