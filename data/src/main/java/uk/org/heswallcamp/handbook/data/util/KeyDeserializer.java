package uk.org.heswallcamp.handbook.data.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class KeyDeserializer extends StdScalarDeserializer<Key> {

	protected KeyDeserializer() {
		super(Key.class);
	}

	@Override
	public Key deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException, JsonProcessingException {
		if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
            return KeyFactory.stringToKey(jsonParser.getText());
        }

        throw ctx.mappingException("Expected JSON String");
	}

}
