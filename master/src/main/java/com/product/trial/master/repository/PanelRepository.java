package com.product.trial.master.repository;

import com.product.trial.master.model.PanelEntity;
import com.product.trial.master.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PanelRepository extends JpaRepository<PanelEntity,Long> {

    Optional<PanelEntity> findByUser(UserEntity userEntity);


}
