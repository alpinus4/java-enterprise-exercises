package com.example.lab3.util;

import lombok.extern.java.Log;

import java.io.*;
import java.util.logging.Level;

@Log
public class CloningUtility {

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T clone(T object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            log.log(Level.SEVERE, ex.getMessage(), ex);
            throw new IllegalStateException(ex);
        }

    }

}
