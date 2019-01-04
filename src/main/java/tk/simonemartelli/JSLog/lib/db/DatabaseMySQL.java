/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.simonemartelli.JSLog.lib.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.simonemartelli.JSLog.lib.JSLogObject;
import tk.simonemartelli.JSLog.lib.JSProfileObject;
import tk.simonemartelli.JSLog.lib.config.DBConfig;

/**
 *
 * @author S. Martelli
 * @version 0.2.0
 * @since 0.1.0
 */
class DatabaseMySQL implements DatabaseJSLog {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabasePostgres.class);
    
    private final static String SQL_CHECK_AUTH = 
        "SELECT id, log_key FROM entity WHERE log_key = ?;";
    
    private final static String SQL_ADD_LOG =
        "INSERT INTO log (entity, record_date, level, UserAgent, Host, message, http_code)"
        + " VALUES (?, ?, ?, ?, ?, ?, ?);";
    
    private final static String SQL_ADD_PROFILE =
        "INSERT INTO profile (entity, profile_time, descr) VALUES (?, ?, ?);";
    
    protected ComboPooledDataSource cpds;
    
    /**
     * 
     * @param config
     * @throws PropertyVetoException 
     */
    DatabaseMySQL(DBConfig config) throws PropertyVetoException, SQLException {
        // register the JDBC driver
        try {
            Class.forName(config.getDriver());
        }
        catch(ClassNotFoundException e) {
            LOGGER.error("MySQL driver can not be loaded!");
            LOGGER.error(e.getMessage());
            throw new SQLException("Driver can not be loaded!");
        }
        
        this.cpds = new ComboPooledDataSource();
        this.cpds.setDriverClass(config.getDriver());
        this.cpds.setJdbcUrl(config.getJDBC());
        
        Properties prop = new Properties();
        prop.setProperty("serverTimezone", "UTC");
        this.cpds.setProperties(prop);
        
        // set database access credentials
        this.cpds.setUser(config.getUsername());
        this.cpds.setPassword(config.getPassword());
        
        this.cpds.setMinPoolSize(1);
        this.cpds.setAcquireIncrement(2);
        this.cpds.setMaxPoolSize(config.getMaxPoolSize());
    }

    @Override
    public void close() throws SQLException {
        if(this.cpds != null) {
            this.cpds.close();
            LOGGER.warn("DatabaseMySQL connection pool closed.");
        }
    }
    
    @Override
    public boolean checkAuth(String logKey, int idEntity) throws SQLException {
        try (Connection con = this.cpds.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(DatabaseMySQL.SQL_CHECK_AUTH);
            stmt.setString(1, logKey);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            if(idEntity != rs.getInt("id"))
                return false;
        }
        return true;
    }

    @Override
    public boolean checkAuth(String logKey, String idEntity) throws SQLException {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void addLog(JSLogObject obj) throws SQLException {
        try (Connection con = this.cpds.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(DatabaseMySQL.SQL_ADD_LOG);
            stmt.setInt(1, obj.getEntity());
            stmt.setTimestamp(2, new Timestamp(obj.getRecordDate().getTime()));
            stmt.setString(3, obj.getLevel());
            stmt.setString(4, obj.getUserAgent());
            stmt.setString(5, obj.getHost());
            stmt.setString(6, obj.getMessage());
            stmt.setInt(7, obj.getHttpCode());
            stmt.executeUpdate();
        }
    }
    
    @Override
    public void addProfile(JSProfileObject obj) throws SQLException {
        try (Connection con = this.cpds.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(SQL_ADD_PROFILE);
            stmt.setInt(1, obj.getEntity());
            stmt.setInt(2, obj.getTime());
            stmt.setString(3, obj.getDesc());
            stmt.executeUpdate();
        }
    }
    
}
