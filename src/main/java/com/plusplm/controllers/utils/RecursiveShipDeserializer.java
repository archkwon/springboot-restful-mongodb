package com.plusplm.controllers.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.plusplm.model.Ship;
import com.plusplm.repository.ShipMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;

public class RecursiveShipDeserializer extends KeyDeserializer {

    @Autowired
    private ShipMongoRepository shipMongoRepository;

    @Override
    public Ship deserializeKey(
            String key,
            DeserializationContext ctx) throws IOException, JsonProcessingException {

        return shipMongoRepository.findOne(key);
    }

}
