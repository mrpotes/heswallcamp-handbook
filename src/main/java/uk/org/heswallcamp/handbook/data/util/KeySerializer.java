package uk.org.heswallcamp.handbook.data.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class KeySerializer extends StdScalarSerializer<Key> {

	protected KeySerializer() {
		super(Key.class);
	}

	@Override
	public void serialize(Key key, JsonGenerator jgen, SerializerProvider arg2) throws IOException, JsonGenerationException {
		jgen.writeString(KeyFactory.keyToString(key));
	}

}
