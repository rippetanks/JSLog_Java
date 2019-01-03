/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.simonemartelli.JSLog.lib.db;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import tk.simonemartelli.JSLog.lib.config.DBConfig;

/**
 *
 * @author S. Martelli
 * @version 0.1.0
 * @since 0.1.0
 */
public class DatabaseFactory {
    
    /**
     * 
     * @param type
     * @param config
     * @return 
     * @throws java.beans.PropertyVetoException 
     * @throws java.sql.SQLException 
     */
    public DatabaseJSLog getDB(DatabaseType type, DBConfig config) 
        throws PropertyVetoException, SQLException 
    {
        switch(type) {
            case POSTGRES:
                return new DatabasePostgres(config);
            case MY_SQL:
                return new DatabaseMySQL(config);
            case MS_SQL_SERVER:
                return new DatabaseSQLServer(config);
            case MONGO_DB:
                return new DatabaseMongoDB(config);
            default:
                return null;
        }
    }
    
}
