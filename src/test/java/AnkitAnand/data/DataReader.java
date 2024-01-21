package AnkitAnand.data;

import java.io.File;	
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException
	{
		//Read File Data to String
		String jsonContent = FileUtils.readFileToString(new File(filepath));
		
		//String to HashMap
		ObjectMapper obj = new ObjectMapper();
		return obj.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
		
	
		//TypeReference is something which Jakson is providing so that code should be made simpler
		//TypeReference ref = new TypeReference<List<Integer>>() { };
	}

}
