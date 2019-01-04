/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.simonemartelli.JSLog.example;

import java.sql.SQLException;
import java.text.ParseException;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.simonemartelli.JSLog.lib.JSLog;
import tk.simonemartelli.JSLog.lib.JSProfileObject;

/**
 * EXAMPLE PROFILE
 * 
 * @author S. Martelli
 * @version 0.2.0
 * @since 0.2.0
 */
@RestController
public class RestJSProfile {
    
    @RequestMapping(value="/JSLog/profile/mongodb", method=RequestMethod.POST)
    public void addMongoDB(@RequestBody String json) throws JSONException, 
            ParseException, SQLException 
    {
        JSONObject jsonObj = new JSONObject(json);
        JSProfileObject obj = new JSProfileObject(
            jsonObj.optString("entity"),
            jsonObj.getInt("time"),
            jsonObj.getString("desc"));
        JSLog log = JSLog.getInstance();
        log.profile("KEY", obj);
    }
    
    @RequestMapping(value="/JSLog/profile/mysql", method=RequestMethod.POST)
    public void addMySQL(@RequestBody String json) throws JSONException, 
            ParseException, SQLException 
    {
        JSONObject jsonObj = new JSONObject(json);
        JSProfileObject obj = new JSProfileObject(
            jsonObj.optInt("entity"),
            jsonObj.getInt("time"),
            jsonObj.getString("desc"));
        JSLog log = JSLog.getInstance();
        log.profile("KEY", obj);
    }
    
    @RequestMapping(value="/JSLog/profile/sqlserver", method=RequestMethod.POST)
    public void addSQLServer(@RequestBody String json) throws JSONException, 
            ParseException, SQLException 
    {
        JSONObject jsonObj = new JSONObject(json);
        JSProfileObject obj = new JSProfileObject(
            jsonObj.optInt("entity"),
            jsonObj.getInt("time"),
            jsonObj.getString("desc"));
        JSLog log = JSLog.getInstance();
        log.profile("KEY", obj);
    }
    
    @RequestMapping(value="/JSLog/profile/postgres", method=RequestMethod.POST)
    public void addPostgres(@RequestBody String json) throws JSONException, 
            ParseException, SQLException 
    {
        JSONObject jsonObj = new JSONObject(json);
        JSProfileObject obj = new JSProfileObject(
            jsonObj.optInt("entity"),
            jsonObj.getInt("time"),
            jsonObj.getString("desc"));
        JSLog log = JSLog.getInstance();
        log.profile("KEY", obj);
    }
    
}
