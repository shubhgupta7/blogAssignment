package com.KoffeeClan.AssignMent.repositories;

import com.KoffeeClan.AssignMent.models.blogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface blogRepo extends JpaRepository<blogModel,Long> {

}
