package BibliothekenTest;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJacksonStadion {

	public static void main(String[] args) {

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "";
		Stadion stadium = new Stadion("Arsenal", "Arsenal", "London", "Emirates Stadium", "60361.51", "-0.108611",
				"England");

		try {
			jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(stadium);
			System.out.println(jsonString);

		} catch (

		JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
