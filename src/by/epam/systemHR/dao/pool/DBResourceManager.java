package by.epam.systemHR.dao.pool;

import java.util.ResourceBundle;

/**
 * Class loads parameters to connect to the database. Singleton template
 */
public class DBResourceManager {
	private final static DBResourceManager instance = new DBResourceManager();

	private DBResourceManager() {
	}

	private ResourceBundle bundle = ResourceBundle.getBundle("resource.db");

	public static DBResourceManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return bundle.getString(key);
	}
}
