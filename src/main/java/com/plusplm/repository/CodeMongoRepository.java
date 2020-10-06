package com.plusplm.repository;

import com.plusplm.model.Code;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeMongoRepository extends CrudRepository<Code, String>, PagingAndSortingRepository<Code, String> {

}
