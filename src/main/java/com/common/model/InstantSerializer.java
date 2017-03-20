
package com.common.model;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;

/**
 * Created by dka on 1/22/17.
 */
public class InstantSerializer<T> extends JsonSerializer<Instant> {
	@Override
	public void serialize(Instant value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		String isoDateStr = value.toString();
		jgen.writeObject(isoDateStr);
	}
}