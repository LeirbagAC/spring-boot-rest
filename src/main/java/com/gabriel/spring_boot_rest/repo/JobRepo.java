package com.gabriel.spring_boot_rest.repo;

import com.gabriel.spring_boot_rest.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<JobPost, Integer> {

    //Usado dessa forma para finalidades de estudo, sql seria a opção correta nesse cenário
    List<JobPost> findByPostProfileContainingIgnoreCaseOrPostDescContainingIgnoreCase(String profile, String desc);

}