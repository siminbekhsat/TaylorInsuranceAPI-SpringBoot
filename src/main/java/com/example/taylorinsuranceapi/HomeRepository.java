package com.example.taylorinsuranceapi;

import org.springframework.data.repository.CrudRepository;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * Home repository
 * @author Simin
 */

public interface HomeRepository extends CrudRepository <Home, Integer> {

    //No queries beyond CrudRepository Needed most likely

}
