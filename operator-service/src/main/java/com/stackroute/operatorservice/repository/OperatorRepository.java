package com.stackroute.operatorservice.repository;

import com.stackroute.operatorservice.model.Operator;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends ElasticsearchRepository<Operator,String> {
}
