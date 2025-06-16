package com.product.trial.master.mapper;

import com.product.trial.master.dto.UserProductDTO;
import com.product.trial.master.model.UserProductEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface UserProductMapper {

    @Mapping(target = "product.id", source = "product.productId")
    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    UserProductDTO userProductEntityToUserProductDTO(UserProductEntity userProductEntity);

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    UserProductDTO userProductDTOToUserProductEntity(UserProductEntity userProductEntity);

}
