package com.product.trial.master.mapper;

import com.product.trial.master.dto.UserDTO;
import com.product.trial.master.model.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    UserDTO userToUserDTO(UserEntity user);


    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    UserEntity userDTOToUser(UserDTO user);
}

