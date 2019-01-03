/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.simonemartelli.JSLog.lib;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author S. Martelli
 * @version 0.1.0
 * @since 0.1.0
 */
public class JSLogObject {
    
    private final int entity;
    private final String str_entity;
    private final Date record_date;
    private final String level;
    private final String user_agent;
    private final String host;
    private final String message;
    private final int http_code;

    public JSLogObject(int entity, Date recordDate, String level, 
        String userAagent, String host, String message, int httpCode) 
    {
        this.str_entity = null;
        this.entity = entity;
        this.record_date = recordDate;
        this.level = level;
        this.user_agent = userAagent;
        this.host = host;
        this.message = message;
        this.http_code = httpCode;
    }

    public JSLogObject(String entity, Date recordDate, String level, 
        String userAagent, String host, String message, int httpCode) 
    {
        this.str_entity = entity;
        this.entity = 0;
        this.record_date = recordDate;
        this.level = level;
        this.user_agent = userAagent;
        this.host = host;
        this.message = message;
        this.http_code = httpCode;
    }
    
    public int getEntity() {
        return this.entity;
    }

    public String getStrEntity() {
        return this.str_entity;
    }
    
    public Date getRecordDate() {
        return this.record_date;
    }

    public String getLevel() {
        return this.level;
    }

    public String getUserAgent() {
        return this.user_agent;
    }

    public String getHost() {
        return this.host;
    }

    public String getMessage() {
        return this.message;
    }

    public int getHttpCode() {
        return this.http_code;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.entity;
        hash = 41 * hash + Objects.hashCode(this.str_entity);
        hash = 41 * hash + Objects.hashCode(this.record_date);
        hash = 41 * hash + Objects.hashCode(this.level);
        hash = 41 * hash + Objects.hashCode(this.user_agent);
        hash = 41 * hash + Objects.hashCode(this.host);
        hash = 41 * hash + Objects.hashCode(this.message);
        hash = 41 * hash + this.http_code;
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
        final JSLogObject other = (JSLogObject) obj;
        if (this.entity != other.entity) {
            return false;
        }
        if (this.http_code != other.http_code) {
            return false;
        }
        if (!Objects.equals(this.str_entity, other.str_entity)) {
            return false;
        }
        if (!Objects.equals(this.level, other.level)) {
            return false;
        }
        if (!Objects.equals(this.user_agent, other.user_agent)) {
            return false;
        }
        if (!Objects.equals(this.host, other.host)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return Objects.equals(this.record_date, other.record_date);
    }

    @Override
    public String toString() {
        return "JSLogObject{" + "entity=" + entity + ", str_entity=" + str_entity 
            + ", record_date=" + record_date + ", level=" + level 
            + ", user_agent=" + user_agent + ", host=" + host 
            + ", message=" + message + ", http_code=" + http_code + '}';
    }
    
}
