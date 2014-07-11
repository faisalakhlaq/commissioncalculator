package properties;

import gui.dailogue.MessageDialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class CommissionCalcProperties {
	public CommissionCalcProperties() {
		OutputStream output = null;
		// Path path = Paths.get("config.properties");
		try {
			Boolean exists = new File("config.properties").isFile();
			// if (Files.notExists(path))
			if (!exists) {
				Properties prop = new Properties();

				output = new FileOutputStream("config.properties");

				// set the properties value
				prop.setProperty("database", "localhost");
				prop.setProperty("dbname", "commission_db");
				prop.setProperty("url", "jdbc:mysql://localhost:3306/");
				prop.setProperty("user", "root");
				prop.setProperty("password", "root");
				prop.setProperty("company_name", "SAMI-ULLAH PHOTOSTATE");

				// save properties to project root folder
				prop.store(output, null);
			}
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @return Properties
	 * */
	public Properties getPropFile() {
		Properties prop = new Properties();

		FileInputStream input = null;

		try {
			String filename = "config.properties";
			input = new FileInputStream(filename);
			prop.load(input);
		} catch (IOException ex) {
			new MessageDialog("Error",
					"Sorry, unable to find/load properties file");
			prop = null;
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					new MessageDialog("Error",
							"Sorry, unable to find/load properties file");
					prop = null;
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
}