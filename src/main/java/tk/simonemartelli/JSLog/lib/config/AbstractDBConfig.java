/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.simonemartelli.JSLog.lib.config;

import java.util.Objects;

/**
 *
 * @author S. Martelli
 * @version 0.1.0
 * @since 0.1.0
 */
public abstract class AbstractDBConfig implements DBConfig {
    
    protected final String driver;
    protected final String dbms;
    protected final String server_url;
    protected final int server_port;
    protected final String db_name;
    protected final String username;
    protected final String password;
    protected final int max_pool_size;
    
    AbstractDBConfig(String driver, String dbms, String serverUrl, int serverPort, 
        String dbName, String username, String password, int maxPoolSize) 
    {
        this.driver = driver;
        this.dbms = dbms;
        this.server_url = serverUrl;
        this.server_port = serverPort;
        this.db_name = dbName;
        this.username = username;
        this.password = password;
        this.max_pool_size = maxPoolSize;
    }

    @Override
    public String getDriver() {
        return this.driver;
    }
    
    @Override
    public String getDBMS() {
        return this.dbms;
    }
    
    @Override
    public String getServerURL() {
        return this.server_url;
    }
    
    @Override
    public int getServerPort() {
        return this.server_port;
    }
    
    @Override
    public String getDbName() {
        return this.db_name;
    }
    
    @Override
    public String getUsername() {
        return this.username;
    }
    
    @Override
    public String getPassword() {
        return this.password;
    }
    
    @Override
    public int getMaxPoolSize() {
        return this.max_pool_size;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.driver);
        hash = 29 * hash + Objects.hashCode(this.dbms);
        hash = 29 * hash + Objects.hashCode(this.server_url);
        hash = 29 * hash + this.server_port;
        hash = 29 * hash + Objects.hashCode(this.db_name);
        hash = 29 * hash + Objects.hashCode(this.username);
        hash = 29 * hash + Objects.hashCode(this.password);
        hash = 29 * hash + this.max_pool_size;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractDBConfig other = (AbstractDBConfig) obj;
        if (this.server_port != other.server_port) {
            return false;
        }
        if (this.max_pool_size != other.max_pool_size) {
            return false;
        }
        if (!Objects.equals(this.driver, other.driver)) {
            return false;
        }
        if (!Objects.equals(this.dbms, other.dbms)) {
            return false;
        }
        if (!Objects.equals(this.server_url, other.server_url)) {
            return false;
        }
        if (!Objects.equals(this.db_name, other.db_name)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    @Override
    public String toString() {
        return "DBConfig{" + "driver=" + driver + ", dbms=" + dbms 
            + ", server_url=" + server_url + ", server_port=" + server_port 
            + ", db_name=" + db_name + ", username=" + username 
            + ", password=" + password + ", max_pool_size=" + max_pool_size + '}';
    }
    
}
