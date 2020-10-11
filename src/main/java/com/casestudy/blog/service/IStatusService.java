package com.casestudy.blog.service;

import com.casestudy.blog.model.Status;

import java.util.Optional;

public interface IStatusService {
    Optional<Status> findByName (String statusName);
}
