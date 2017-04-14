package dao.connectionpool;

import java.util.ResourceBundle;

/**
 * Created by irinaleibutina on 22.02.17.
 */
public class DBResourceManager {

    private final static DBResourceManager instance = new DBResourceManager();

    private ResourceBundle bundle = ResourceBundle.getBundle("resource.db");

    public static DBResourceManager getInstance(){
        return instance;
    }

    public String getValue(String key){
        return bundle.getString(key);
    }
}
