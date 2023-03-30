package ArifAcademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		//read json to string
		String jsonContent= FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\ArifAcademy\\data\\purchaseOrder.json"),StandardCharsets.UTF_8);
		System.out.println( "Json File converted to string");
		//String to HashMap jackson binding
		ObjectMapper mapper = new ObjectMapper();
		System.out.println( "Json File converted to string");
		List<HashMap<String,String>> data =mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		return data;
	}
}
