package com.product.trial.master.repository;

import com.product.trial.master.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {

}
