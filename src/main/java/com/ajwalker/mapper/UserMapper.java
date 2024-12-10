package com.ajwalker.mapper;

import com.ajwalker.dto.request.RegisterRequestDto;
import com.ajwalker.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "string", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	User fromRegisterDto(RegisterRequestDto dto); //sağdakine yazdıklarımızla başa yazdığımızın içini dolduruyor.
}