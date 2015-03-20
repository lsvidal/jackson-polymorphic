package org.clael.test.json;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {

	static String jsonInput = "{\"type\":\"dog\",\"name\":\"Spike\",\"values\": [1, 2.5, \"3\"]}";
	
	static String jsonInput1 = "{\"nome\":\"Spike\",\"con\": [1, 2, 5, \"Juju\", 2.3, \"Anima\"]}";

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

		final Object[] valores = { 3.2, 5, 9, "Juju" };
		final Animal anta = new Animal();
		anta.setType("anta");
		anta.setName("Juju");
		anta.setValues(valores);
		try {
			final String json = mapper.writeValueAsString(anta);
			System.out.println(json);
			final Animal mula = mapper.readValue(json, Animal.class);
			for (Object obj : mula.getValues()) {
				System.out.println(obj.getClass());
				if (obj instanceof Integer) {
					System.out.println(((Integer) obj).toString()
							+ " is an Integer");
				}
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Conf conf = mapper.readValue(jsonInput1, Json.Conf.class);
			for (final Con con: conf.getCon()) {
				System.out.println(con.toString());
			}
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

	private static abstract class Con {
		@JsonCreator
		public static Con stringMaker(final String st) {
			return new ConString(st);
		}
		
		@JsonCreator
		public static Con integerMaker(final Integer in) {
			return new ConInteger(in);
		}
		
		@JsonCreator
		public static Con doubleMaker(final Double in) {
			return new ConDouble(in);
		}
		
		public abstract String toString();
	}
	
	private static class ConInteger extends Con {
		private Integer value;

		public ConInteger(Integer value) {
			super();
			this.value = value;
		}
		
		@Override
		public String toString() {
			return "Integer: " + value;
		}
	}
	
	private static class ConDouble extends Con {
		private Double value;

		public ConDouble(Double value) {
			super();
			this.value = value;
		}
		
		@Override
		public String toString() {
			return "Double: " + value;
		}
	}

	private static class ConString extends Con {
		private String value;

		public ConString(String value) {
			super();
			this.value = value;
		}
		
		@Override
		public String toString() {
			return "String: " + value;
		}
	}
	
	public static class Conf {
		private String nome;
		private Con[] con;

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public Con[] getCon() {
			return con;
		}

		public void setCon(Con[] con) {
			this.con = con;
		}
	}
}