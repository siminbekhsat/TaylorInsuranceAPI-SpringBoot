package com.example.taylorinsuranceapi;

import org.springframework.data.repository.CrudRepository;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * Auto repository
 * @author Simin
 */

public interface AutoRepository extends CrudRepository <Auto, Integer> {

    //No queries beyond CrudRepository Needed most likely

}
