package org.clael.test.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {

	static String jsonInput = "{\"type\":\"dog\",\"name\":\"Spike\",\"values\": [1, 2.5, \"3\"]}";

	public static void main(final String[] args) {
		final ObjectMapper mapper = new ObjectMapper();
		try {
			Animal animal = mapper.readValue(jsonInput, Json.Animal.class);
			System.out.println(mapper.writeValueAsString(animal));
			System.out.println(animal.getValues()[0].getClass());
			System.out.println(animal.getValues()[1].getClass());
			System.out.println(animal.getValues()[2].getClass());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static class Animal {
		private String type;
		private String name;
		private Object[] values;
		
		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Object[] getValues() {
			return values;
		}

		public void setValues(Object[] values) {
			this.values = values;
		}
	}
}