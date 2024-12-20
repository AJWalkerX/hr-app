package com.ajwalker.mapper;

import com.ajwalker.dto.request.CompanyCustomerInfoRequestDto;
import com.ajwalker.entity.Company;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "string", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyMapper {
	CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	Company fromUpdateCompanyCustomerInfoRequestDto(final CompanyCustomerInfoRequestDto dto, @MappingTarget Company company);
}