/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.simonemartelli.JSLog.lib.db;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import org.bson.Document;
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
class DatabaseMongoDB implements DatabaseJSLog {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabasePostgres.class);
    
    protected MongoClient client;
    protected MongoDatabase db;
    protected MongoCollection c_entity;
    protected MongoCollection c_log;
    
    /**
     * 
     * @param config
     * @throws PropertyVetoException 
     */
    DatabaseMongoDB(DBConfig config) {       
        /*
        MongoCredential credential = MongoCredential.createCredential(
            config.getUsername(), 
            config.getDbName(), 
            config.getPassword().toCharArray());
        MongoClientOptions options = MongoClientOptions.builder()
            .sslEnabled(true).build();
        this.client = new MongoClient(
            new ServerAddress(config.getServerURL(), 
                              config.getServerPort()),
                              Arrays.asList(credential),
                              options);
        */
        this.client = new MongoClient(config.getServerURL(), config.getServerPort());
        this.db = this.client.getDatabase(config.getDbName());
        
        this.c_entity = this.db.getCollection("entity");
        this.c_log = this.db.getCollection("log");
    }

    @Override
    public void close() throws SQLException {
        if(this.client != null) {
            this.client.close();
            LOGGER.warn("DatabaseMongoDB connection pool closed.");
        }
    }
    
    @Override
    public boolean checkAuth(String logKey, int idEntity) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean checkAuth(String logKey, String idEntity) throws SQLException {
        BasicDBObject query = new BasicDBObject("log_key", logKey);
        Document doc = (Document)this.c_entity.find(eq("log_key", logKey)).first();
        if(doc != null) {
            String dbEntity = doc.getObjectId("_id").toHexString();
            if(idEntity.equals(dbEntity))
                return true;
        }
        return false;
    }

    @Override
    public void addLog(JSLogObject obj) throws SQLException {
        Document doc = new Document("entity", obj.getStrEntity())
                .append("record_date", obj.getRecordDate())
                .append("level", obj.getLevel())
                .append("UserAgent", obj.getUserAgent())
                .append("Host", obj.getHost())
                .append("message", obj.getMessage())
                .append("http_code", obj.getHttpCode())
                .append("storage_date", new Date());
        this.c_log.insertOne(doc);
    }
    
}
