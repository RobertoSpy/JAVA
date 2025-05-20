package com.example;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CapitalConverter implements AttributeConverter<Boolean, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Boolean attribute) {
        return (attribute == null) ? null : (attribute ? 1 : 0);
    }

    @Override
    public Boolean convertToEntityAttribute(Integer dbData) {
        return (dbData != null && dbData == 1);
    }
}
