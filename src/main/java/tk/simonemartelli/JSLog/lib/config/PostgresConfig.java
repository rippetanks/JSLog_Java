/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.simonemartelli.JSLog.lib.config;

/**
 *
 * @see <a href="https://jdbc.postgresql.org/documentation/80/connect.html">jdbc.postgresql.org</a>
 * @see <a href="https://www.postgresql.org/docs/7.3/jdbc-datasource.html">PoolingDataSource</a>
 * 
 * @author S. Martelli
 * @version 0.1.0
 * @since 0.1.0
 */
public class PostgresConfig extends AbstractDBConfig {
    
    private final static String DBMS = "postgresql";
    
    private final static String DRIVER = "org.postgresql.Driver";
    
    private final static int DEFAULT_PORT = 5432;
    
    public PostgresConfig(String serverUrl, String dbName, String username, 
        String password, int maxPoolSize) 
    {
        super(PostgresConfig.DRIVER, PostgresConfig.DBMS, serverUrl, 
            PostgresConfig.DEFAULT_PORT, dbName, username, password, maxPoolSize);
    }
    
    public PostgresConfig(String serverUrl, int serverPort, String dbName, 
        String username, String password, int maxPoolSize) 
    {
        super(PostgresConfig.DRIVER, PostgresConfig.DBMS, serverUrl, serverPort, 
            dbName, username, password, maxPoolSize);
    }
    
    @Override
    public String getJDBC() {
        throw new UnsupportedOperationException();
    }
    
}
