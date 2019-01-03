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
public interface DBConfig {
    
    /**
     * 
     * @return 
     */
    String getDriver();
    
    /**
     * 
     * @return 
     */
    String getDBMS();
    
    /**
     * 
     * @return 
     */
    String getServerURL();
    
    /**
     * 
     * @return 
     */
    int getServerPort();
    
    /**
     * 
     * @return 
     */
    String getDbName();
    
    /**
     * 
     * @return 
     */
    String getUsername();
    
    /**
     * 
     * @return 
     */
    String getPassword();
    
    /**
     * 
     * @return 
     */
    String getJDBC();
    
    /**
     * 
     * @return 
     */
    int getMaxPoolSize();
    
}
