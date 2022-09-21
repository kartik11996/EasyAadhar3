package com.stackroute.customerservice.repository;

import java.util.List;
//import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.customerservice.model.*;

@Repository
public interface CustomerRepo extends MongoRepository<CustomerList,String>
{

	List<CustomerList> findByMobile(String mobile);

//	List<CustomerList> findByAddress(String address);

}
