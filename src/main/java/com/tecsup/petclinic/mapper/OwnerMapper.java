package com.tecsup.petclinic.mapper;

import com.tecsup.petclinic.domain.OwnerTO;
import com.tecsup.petclinic.entities.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface OwnerMapper {

    OwnerMapper INSTANCE = Mappers.getMapper(OwnerMapper.class);

    @Mapping(source = "nombres", target = "nombres")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "telephone", target = "telephone")
    @Mapping(source = "email", target = "email")
    Owner toOwner(OwnerTO ownerTO);

    @Mapping(source = "nombres", target = "nombres")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "telephone", target = "telephone")
    @Mapping(source = "email", target = "email")
    OwnerTO toOwnerTO(Owner owner);

    List<OwnerTO> toOwnerTOList(List<Owner> ownerList);

    List<Owner> toOwnerList(List<OwnerTO> ownerTOList);
}