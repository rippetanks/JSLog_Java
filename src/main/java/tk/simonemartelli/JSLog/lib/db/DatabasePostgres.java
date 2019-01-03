/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.simonemartelli.JSLog.lib.db;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.simonemartelli.JSLog.lib.JSLogObject;
import tk.simonemartelli.JSLog.lib.config.DBConfig;

/**
 *
 * @author S. Martelli
 * @version 0.1.0
 * @since 0.1.0
 */
class DatabasePostgres implements DatabaseJSLog {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabasePostgres.class);
    
    private final static String SQL_CHECK_AUTH = 
        "SELECT id, log_key FROM entity WHERE log_key = ?;";
    
    private final static String SQL_ADD_LOG =
        "INSERT INTO log (entity, record_date, level, \"UserAgent\", \"Host\", message, http_code)"
        + " VALUES (?, ?, CAST(? AS level_enum), ?, ?, ?, ?);";
    
    protected Jdbc3PoolingDataSource cpds;
    
    /**
     * 
     * @param config
     * @throws PropertyVetoException 
     */
    DatabasePostgres(DBConfig config) throws PropertyVetoException, SQLException {
        // register the JDBC driver
        try {
            Class.forName(config.getDriver());
        }
        catch(ClassNotFoundException e) {
            LOGGER.error("Postgres driver can not be loaded!");
            LOGGER.error(e.getMessage());
            throw new SQLException("Driver can not be loaded!");
        }
        
        this.cpds = new Jdbc3PoolingDataSource();
        this.cpds.setPortNumber(config.getServerPort());
        this.cpds.setServerName(config.getServerURL());
        this.cpds.setDatabaseName(config.getDbName());
        // set database access credentials
        this.cpds.setUser(config.getUsername());
        this.cpds.setPassword(config.getPassword());
        
        this.cpds.setMaxConnections(config.getMaxPoolSize());
    }

    @Override
    public void close() throws SQLException {
        if(this.cpds != null) {
            this.cpds.close();
            LOGGER.warn("DatabasePostgres connection pool closed.");
        }
    }

    @Override
    public boolean checkAuth(String logKey, int idEntity) throws SQLException {
        try (Connection con = this.cpds.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(DatabasePostgres.SQL_CHECK_AUTH);
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
            PreparedStatement stmt = con.prepareStatement(DatabasePostgres.SQL_ADD_LOG);
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

}
