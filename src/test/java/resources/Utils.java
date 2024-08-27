package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification requestSpec;
	public RequestSpecification requestSpec() throws IOException
	{
		if(requestSpec== null)
		{
		PrintStream log = new PrintStream(new FileOutputStream("log.txt"));
		requestSpec = new RequestSpecBuilder().setBaseUri(getValuefromProperties("baseURL"))
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		return requestSpec;
		}
		return requestSpec;
	}
	
	public static String getValuefromProperties(String property) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("D:\\Mahesh\\Java\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		String propertyValue = prop.getProperty(property);
		return propertyValue;		
		
	}
	
	public String getJsonPath(Response response, String key)
	{
		String res = response.asString();
		
		JsonPath js = new JsonPath(res);
		return js.get(key).toString();
	}

}

