package com.example.lab3.util;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import java.time.LocalDate;

@FacesConverter(forClass = LocalDate.class)
public class LocalDateConverter implements Converter<LocalDate> {
    @Override
    public LocalDate getAsObject(FacesContext context, UIComponent component, String value) {
        return LocalDate.parse(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, LocalDate value) {
        return value.toString();
    }
}
