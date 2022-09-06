package com.customerservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.customerservice.model.CustomerList;

@Repository
public interface CustomerRepo extends MongoRepository<CustomerList, Integer> {




}
