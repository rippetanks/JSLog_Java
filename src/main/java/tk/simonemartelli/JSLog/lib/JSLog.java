/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.simonemartelli.JSLog.lib;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import tk.simonemartelli.JSLog.lib.config.DBConfig;
import tk.simonemartelli.JSLog.lib.db.DatabaseFactory;
import tk.simonemartelli.JSLog.lib.db.DatabaseJSLog;
import tk.simonemartelli.JSLog.lib.db.DatabaseType;
import tk.simonemartelli.JSLog.lib.exception.JSLogException;

/**
 * Heart of the lib.
 * 
 * @author S. Martelli
 * @version 0.2.0
 * @since 0.1.0
 */
public class JSLog {
    
    private static volatile JSLog INSTANCE;
    
    private static final Object MUTEX = new Object();
    
    private final DatabaseFactory db_factory;
    private DatabaseJSLog db;
    private boolean auth;
    
    /**
     * 
     */
    private JSLog() {
        this.db_factory = new DatabaseFactory();
        this.auth = false;
    }
    
    /**
     * 
     * @return 
     */
    public static JSLog getInstance() {
        JSLog result = INSTANCE;
		if (result == null) {
			synchronized(MUTEX) {
				result = INSTANCE;
				if (result == null)
					INSTANCE = result = new JSLog();
			}
		}
		return result;
    }
    
    /**
     * Initialize the lib. Calling this function closes previously established 
     * connections to the database.
     * 
     * @param type
     * @param config
     * @throws SQLException
     * @throws PropertyVetoException 
     */
    public void init(DatabaseType type, DBConfig config) 
        throws SQLException, PropertyVetoException 
    {
        close();
        this.db = this.db_factory.getDB(type, config);
    }
    
    /**
     * Close the connection.
     * 
     * @throws SQLException 
     */
    public void close() throws SQLException {
        if(this.db != null)
            this.db.close();
    }
    
    /**
     * 
     * @return 
     */
    public boolean isAuth() {
        return this.auth;
    }
    
    /**
     * 
     * @param auth 
     */
    public void setAuth(boolean auth) {
        this.auth = auth;
    }
    
    /**
     * Add log to the database. If authentication is enabled, it executes it.
     * 
     * @param logKey
     * @param obj
     * @throws SQLException 
     */
    public void add(String logKey, JSLogObject obj) throws SQLException {
        if(this.auth) {
            if(obj.getStrEntity() != null) {
                if(!this.db.checkAuth(logKey, obj.getStrEntity())) {
                    throw new JSLogException("Wrong credentials!");
                }
            }
            else {
                if(!this.db.checkAuth(logKey, obj.getEntity())) {
                    throw new JSLogException("Wrong credentials!");
                }
            }
        }
        this.db.addLog(obj);
    }
    
    /**
     * Add log to the database. It does not use authentication, 
     * if it is enabled it throws an exception.
     * 
     * @param obj
     * @throws JSLogException
     * @throws SQLException 
     */
    public void add(JSLogObject obj) throws JSLogException, SQLException {
        if(this.auth)
            throw new JSLogException("Authentication enabled! It is necessary to authenticate.");
        this.db.addLog(obj);
    }
    
    /**
     * Add profile data to the database. If authentication is enabled, it executes it.
     * 
     * @param logKey
     * @param obj
     * @throws SQLException 
     */
    public void profile(String logKey, JSProfileObject obj) throws SQLException {
        if(this.auth) {
            if(obj.getStrEntity() != null) {
                if(!this.db.checkAuth(logKey, obj.getStrEntity())) {
                    throw new JSLogException("Wrong credentials!");
                }
            }
            else {
                if(!this.db.checkAuth(logKey, obj.getEntity())) {
                    throw new JSLogException("Wrong credentials!");
                }
            }
        }
        this.db.addProfile(obj);
    }
    
    /**
     * Add profile data to the database. It does not use authentication, 
     * if it is enabled it throws an exception.
     * 
     * @param obj
     * @throws JSLogException
     * @throws SQLException 
     */
    public void profile(JSProfileObject obj) throws JSLogException, SQLException {
        if(this.auth)
            throw new JSLogException("Authentication enabled! It is necessary to authenticate.");
        this.db.addProfile(obj);
    }
    
}
