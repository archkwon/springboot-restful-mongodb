package com.plusplm.controllers.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.plusplm.model.Ship;
import java.io.IOException;

public class RecursiveShipSerializer extends StdSerializer<Ship> {

	public RecursiveShipSerializer() {
		this(null);
	}

	public RecursiveShipSerializer(Class<Ship> t) {
		super(t);
	}

	@Override
	public void serialize(Ship value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {

		jgen.writeStartObject();
		jgen.writeStringField("id", value.getId());
		jgen.writeEndObject();
	}

}
