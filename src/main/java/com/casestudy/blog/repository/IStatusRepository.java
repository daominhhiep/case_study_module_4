package com.casestudy.blog.repository;

import com.casestudy.blog.model.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IStatusRepository extends CrudRepository<Status,Long> {
    Optional<Status> findByName (String statusName);
}
