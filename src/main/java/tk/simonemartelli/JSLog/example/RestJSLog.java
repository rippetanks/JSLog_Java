/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.simonemartelli.JSLog.example;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.simonemartelli.JSLog.lib.JSLog;
import tk.simonemartelli.JSLog.lib.JSLogObject;

/**
 *  EXAMPLE
 * 
 * @author S. Martelli
 * @version 0.1.0
 * @since 0.1.0
 */
@RestController
public class RestJSLog {
 
    @RequestMapping(value="/JSLog/log/mongodb", method=RequestMethod.POST)
    public void addMongoDB(@RequestBody String json) throws JSONException, 
            ParseException, SQLException 
    {
        JSONArray array = new JSONArray(json);
        for(int i = 0; i < array.length(); i++) {
            JSONObject jsonObj = array.getJSONObject(i);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            JSLogObject obj = new JSLogObject(
                jsonObj.optString("entity"),
                format.parse(jsonObj.getString("record_date")),
                jsonObj.getString("level"),
                jsonObj.optString("UserAgent"),
                jsonObj.optString("Host"),
                jsonObj.getString("message"),
                jsonObj.optInt("http_code"));
            JSLog log = JSLog.getInstance();
            log.add("KEY", obj);
        }
    }
    
    @RequestMapping(value="/JSLog/log/mysql", method=RequestMethod.POST)
    public void addMySQL(@RequestBody String json) throws JSONException, 
            ParseException, SQLException 
    {
        JSONArray array = new JSONArray(json);
        for(int i = 0; i < array.length(); i++) {
            JSONObject jsonObj = array.getJSONObject(i);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            JSLogObject obj = new JSLogObject(
                jsonObj.optInt("entity"),
                format.parse(jsonObj.getString("record_date")),
                jsonObj.getString("level"),
                jsonObj.optString("UserAgent"),
                jsonObj.optString("Host"),
                jsonObj.getString("message"),
                jsonObj.optInt("http_code"));
            JSLog log = JSLog.getInstance();
            log.add("KEY", obj);
        }
    }
    
    @RequestMapping(value="/JSLog/log/sqlserver", method=RequestMethod.POST)
    public void addSQLServer(@RequestBody String json) throws JSONException, 
            ParseException, SQLException 
    {
        JSONArray array = new JSONArray(json);
        for(int i = 0; i < array.length(); i++) {
            JSONObject jsonObj = array.getJSONObject(i);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            JSLogObject obj = new JSLogObject(
                jsonObj.optInt("entity"),
                format.parse(jsonObj.getString("record_date")),
                jsonObj.getString("level"),
                jsonObj.optString("UserAgent"),
                jsonObj.optString("Host"),
                jsonObj.getString("message"),
                jsonObj.optInt("http_code"));
            JSLog log = JSLog.getInstance();
            log.add("KEY", obj);
        }
    }
    
    @RequestMapping(value="/JSLog/log/postgres", method=RequestMethod.POST)
    public void addPostgres(@RequestBody String json) throws JSONException, 
            ParseException, SQLException 
    {
        JSONArray array = new JSONArray(json);
        for(int i = 0; i < array.length(); i++) {
            JSONObject jsonObj = array.getJSONObject(i);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            JSLogObject obj = new JSLogObject(
                jsonObj.optInt("entity"),
                format.parse(jsonObj.getString("record_date")),
                jsonObj.getString("level"),
                jsonObj.optString("UserAgent"),
                jsonObj.optString("Host"),
                jsonObj.getString("message"),
                jsonObj.optInt("http_code"));
            JSLog log = JSLog.getInstance();
            log.add("KEY", obj);
        }
    }
    
}
