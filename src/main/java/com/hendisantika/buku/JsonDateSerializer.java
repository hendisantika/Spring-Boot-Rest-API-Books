/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendisantika.buku;

import org.springframework.stereotype.Component;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hendisantika
 */
@Component
public class JsonDateSerializer extends ValueSerializer<Date> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializationContext ctxt) {
        String formattedDate = dateFormat.format(date);
        gen.writeString(formattedDate);
    }

}
