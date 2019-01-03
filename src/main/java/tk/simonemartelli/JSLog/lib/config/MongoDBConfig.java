/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.simonemartelli.JSLog.lib.config;

/**
 * 
 * @author S. Martelli
 * @version 0.1.0
 * @since 0.1.0
 */
public class MongoDBConfig extends AbstractDBConfig {
    
    private final static String DBMS = null;
    
    private final static String DRIVER = null;
    
    private final static int DEFAULT_PORT = 27017;
    
    public MongoDBConfig(String serverUrl, String dbName, String username, String password) {
        super(MongoDBConfig.DRIVER, MongoDBConfig.DBMS, serverUrl, 
            MongoDBConfig.DEFAULT_PORT, dbName, username, password, 0);
    }
    
    public MongoDBConfig(String serverUrl, String dbName, int serverPort, 
        String username, String password) 
    {
        super(MongoDBConfig.DRIVER, MongoDBConfig.DBMS, serverUrl, serverPort, 
            dbName, username, password, 0);
    }
    
    @Override
    public String getJDBC() {
        throw new UnsupportedOperationException();
    }
    
}
