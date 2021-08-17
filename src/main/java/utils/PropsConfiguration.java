package utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Loads test suite configuration from resource files.
 */
public class PropsConfiguration {

  private Properties properties;

  public PropsConfiguration() throws IOException {
  	this(System.getProperty("application.properties"));
  }

  public PropsConfiguration(String fromResource) throws IOException {
    properties = new Properties();
    properties.load(PropsConfiguration.class.getResourceAsStream(fromResource));
  }

  public String getProperty(String name) {
    return properties.getProperty(name);
  }
}
