package com.product.trial.master.repository;

import com.product.trial.master.model.UserEntity;
import com.product.trial.master.model.WhichList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WhichListRepository extends JpaRepository<WhichList,Long> {

    Optional<WhichList> findByUser(UserEntity userEntity);
}
