package com.casestudy.blog.service.impl;

import com.casestudy.blog.model.Status;
import com.casestudy.blog.repository.IStatusRepository;
import com.casestudy.blog.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusServiceImpl implements IStatusService {
    @Autowired
    IStatusRepository statusRepository;
    @Override
    public Optional<Status> findByName(String statusName) {
        return statusRepository.findByName(statusName);
    }
}
