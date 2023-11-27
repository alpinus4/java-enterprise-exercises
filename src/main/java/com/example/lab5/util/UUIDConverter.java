package com.example.lab5.util;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import java.util.UUID;

@FacesConverter(forClass = UUID.class)
public class UUIDConverter implements Converter<UUID> {
    @Override
    public UUID getAsObject(FacesContext context, UIComponent component, String value) {
        return UUID.fromString(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, UUID value) {
        return value.toString();
    }
}
