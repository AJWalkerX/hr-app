package com.ajwalker.mapper;

import com.ajwalker.dto.request.IUpdateEmployeeRequestDto;
import com.ajwalker.dto.request.UpdateUserProfileInformationRequestDto;
import com.ajwalker.entity.PersonalDocument;
import com.ajwalker.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "string", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersonalDocumentMapper {
	
	PersonalDocumentMapper INSTANCE = Mappers.getMapper(PersonalDocumentMapper.class);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	PersonalDocument fromIUpdateEmployeeRequestDto(final IUpdateEmployeeRequestDto dto, @MappingTarget PersonalDocument personalDocument);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	PersonalDocument fromUpdateUserProfileInformationRequestDto(final UpdateUserProfileInformationRequestDto dto, @MappingTarget PersonalDocument personalDocument);
}