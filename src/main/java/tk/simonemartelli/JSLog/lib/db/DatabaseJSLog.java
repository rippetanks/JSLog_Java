/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.simonemartelli.JSLog.lib.db;

import java.sql.SQLException;
import tk.simonemartelli.JSLog.lib.JSLogObject;
import tk.simonemartelli.JSLog.lib.JSProfileObject;

/**
 *
 * @author S. Martelli
 * @version 0.2.0
 * @since 0.1.0
 */
public interface DatabaseJSLog {
    
    /**
     * Check if the log is authorized.
     * 
     * @param logKey
     * @param idEntity
     * @return true if the key exists and matches the entity, false otherwise
     * @throws SQLException 
     */
    boolean checkAuth(String logKey, int idEntity) throws SQLException;
    
    /**
     * Check if the log is authorized.
     * 
     * @param logKey
     * @param idEntity
     * @return true if the key exists and matches the entity, false otherwise
     * @throws SQLException 
     */
    boolean checkAuth(String logKey, String idEntity) throws SQLException;
    
    /**
     * Add log to the database.
     * 
     * @param obj
     * @throws SQLException 
     */
    void addLog(JSLogObject obj) throws SQLException;
    
    /**
     * 
     * @param obj
     * @throws SQLException 
     */
    void addProfile(JSProfileObject obj) throws SQLException;
    
    /**
     * Close connection to the database.
     * 
     * @throws SQLException 
     */
    void close() throws SQLException;
    
}
