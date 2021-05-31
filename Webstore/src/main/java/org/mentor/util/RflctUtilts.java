package org.mentor.util;

import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class RflctUtilts {
    public static String[] getClassFields(Class clss){
        Field[] fields = clss.getDeclaredFields();
        int size = fields.length;
        String[] rowNames = new String[size];
        for(int i=0;i<fields.length;i++){
            rowNames[i] = fields[i].getName();
        }
        return rowNames;
    }
}
