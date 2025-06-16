package com.product.trial.master.mapper;

import com.product.trial.master.dto.ProductDTO;
import com.product.trial.master.model.ProductEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface  ProductMapper {
    @Mapping(target = "productId", source = "id")
    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    ProductEntity productDTOtoProduct(ProductDTO productDto);

    @Mapping(target = "id", source = "productId")
    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    ProductDTO productEntityToProductDTO(ProductEntity product);

    List<ProductDTO> productListToProductDTOList(List<ProductEntity> products);
}