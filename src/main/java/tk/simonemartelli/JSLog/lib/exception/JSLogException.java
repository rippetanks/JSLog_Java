/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.simonemartelli.JSLog.lib.exception;

/**
 *
 * @author S. Martelli
 * @version 0.1.0
 * @since 0.1.0
 */
public class JSLogException extends RuntimeException {
    
    /**
     * 
     * @param message 
     */
    public JSLogException(String message) {
        super(message);
    }
    
}
