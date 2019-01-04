/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.simonemartelli.JSLog.lib;

import java.util.Objects;

/**
 *
 * @author S. Martelli
 * @version 0.2.0
 * @since 0.2.0
 */
public class JSProfileObject {
    
    private final int entity;
    private final String str_entity;
    private final int time;
    private final String desc;

    public JSProfileObject(String entity, int time, String desc) {
        this.str_entity = entity;
        this.entity = 0;
        this.time = time;
        this.desc = desc;
    }
    
    public JSProfileObject(int entity, int time, String desc) {
        this.entity = entity;
        this.str_entity = null;
        this.time = time;
        this.desc = desc;
    }

    public int getEntity() {
        return entity;
    }
    
    public String getStrEntity() {
        return str_entity;
    }
    
    public int getTime() {
        return time;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.entity;
        hash = 71 * hash + Objects.hashCode(this.str_entity);
        hash = 71 * hash + this.time;
        hash = 71 * hash + Objects.hashCode(this.desc);
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
        final JSProfileObject other = (JSProfileObject) obj;
        if (this.entity != other.entity) {
            return false;
        }
        if (this.time != other.time) {
            return false;
        }
        if (!Objects.equals(this.str_entity, other.str_entity)) {
            return false;
        }
        return Objects.equals(this.desc, other.desc);
    }

    @Override
    public String toString() {
        return "JSProfileObject{" + "entity=" + entity + ", str_entity=" + str_entity 
            + ", time=" + time + ", desc=" + desc + '}';
    }
    
}
