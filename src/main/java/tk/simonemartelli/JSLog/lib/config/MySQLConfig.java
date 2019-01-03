/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.simonemartelli.JSLog.lib.config;

/**
 *
 * @see <a href="https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-connect-drivermanager.html">MySQL JDBC Connector</a>
 * @see <a href="https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-j2ee-concepts-connection-pooling.html">MySQL Connection Pooling</a>
 * 
 * @author S. Martelli
 * @version 0.1.0
 * @since 0.1.0
 */
public class MySQLConfig extends AbstractDBConfig {
    
    private final static String DBMS = "mysql";
    
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    private final static int DEFAULT_PORT = 3306;
    
    public MySQLConfig(String serverUrl, String dbName, String username, 
        String password, int maxPoolSize) 
    {
        super(MySQLConfig.DRIVER, MySQLConfig.DBMS, serverUrl, 
            MySQLConfig.DEFAULT_PORT, dbName, username, password, maxPoolSize);
    }
    
    public MySQLConfig(String serverUrl, int serverPort, String dbName, 
        String username, String password, int maxPoolSize) 
    {
        super(MySQLConfig.DRIVER, MySQLConfig.DBMS, serverUrl, serverPort, 
            dbName, username, password, maxPoolSize);
    }
    
    @Override
    public String getJDBC() {
        return "jdbc:" + this.dbms + "://" + this.server_url + ":" + this.server_port 
            + "/" + this.db_name; 
    }
    
}
