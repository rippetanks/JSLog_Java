/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.simonemartelli.JSLog.lib.config;

/**
 *
 * @see <a href="https://docs.microsoft.com/it-it/sql/connect/jdbc/connection-url-sample?view=sql-server-2017">MS JDBC Doc</a>
 * 
 * @author S. Martelli
 * @version 0.1.0
 * @since 0.1.0
 */
public class SQLServerConfig extends AbstractDBConfig {
    
    private final static String DBMS = "sqlserver";
    
    private final static String DRIVER = "net.sourceforge.jtds.jdbc.Driver";
    
    private final static int DEFAULT_PORT = 1433;
    
    public SQLServerConfig(String serverUrl, String dbName, String username, 
        String password, int maxPoolSize) 
    {
        super(SQLServerConfig.DRIVER, SQLServerConfig.DBMS, serverUrl, 
            SQLServerConfig.DEFAULT_PORT, dbName, username, password, maxPoolSize);
    }
    
    public SQLServerConfig(String serverUrl, int serverPort, String dbName, 
        String username, String password, int maxPoolSize) 
    {
        super(SQLServerConfig.DRIVER, SQLServerConfig.DBMS, serverUrl, 
            serverPort, dbName, username, password, maxPoolSize);
    }
    
    @Override
    public String getJDBC() {
        return "jdbc:jtds:" + this.dbms + "://" + this.server_url + ":" + this.server_port 
            + ";databaseName=" + this.db_name;
    }
    
}
