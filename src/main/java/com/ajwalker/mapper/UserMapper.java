package com.ajwalker.mapper;

import com.ajwalker.dto.request.IUpdateEmployeeRequestDto;
import com.ajwalker.dto.request.RegisterRequestDto;
import com.ajwalker.dto.request.UpdateUserProfileInformationRequestDto;
import com.ajwalker.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "string", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	User fromRegisterDto(RegisterRequestDto dto); //sağdakine yazdıklarımızla başa yazdığımızın içini dolduruyor.
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	User fromIUpdateEmployeeRequestDto(final IUpdateEmployeeRequestDto dto,@MappingTarget User user);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	User fromUpdateUserProfileInformationRequestDto(final UpdateUserProfileInformationRequestDto dto,@MappingTarget User user);
}