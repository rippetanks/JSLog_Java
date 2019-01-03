package tk.simonemartelli.JSLog.example;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.simonemartelli.JSLog.lib.JSLog;
import tk.simonemartelli.JSLog.lib.config.DBConfig;
import tk.simonemartelli.JSLog.lib.config.MongoDBConfig;
import tk.simonemartelli.JSLog.lib.config.MySQLConfig;
import tk.simonemartelli.JSLog.lib.config.PostgresConfig;
import tk.simonemartelli.JSLog.lib.config.SQLServerConfig;
import tk.simonemartelli.JSLog.lib.db.DatabaseType;

@SpringBootApplication
public class BasicApplication {

	public static void main(String[] args) throws SQLException, PropertyVetoException {
		SpringApplication.run(BasicApplication.class, args);
        
        /*
        // init PostgreSQL
        DBConfig config = new PostgresConfig("localhost", 5433, "jslog", "postgres", "postgres", 4);
        JSLog log = JSLog.getInstance();
        log.init(DatabaseType.POSTGRES, config);
        log.setAuth(true);
        */
        
        /*
        // init MySQL
        DBConfig config = new MySQLConfig("localhost", "jslog", "root", "", 4);
        JSLog log = JSLog.getInstance();
        log.init(DatabaseType.MY_SQL, config);
        log.setAuth(true);
        */
        
        /*
        // init SQL Server
        DBConfig config = new SQLServerConfig("localhost", "jslog", "sa", "simone", 4);
        JSLog log = JSLog.getInstance();
        log.init(DatabaseType.MS_SQL_SERVER, config);
        log.setAuth(true);
        */
        
        // init MongoDB
        DBConfig config = new MongoDBConfig("localhost", "jslog", "", "");
        JSLog log = JSLog.getInstance();
        log.init(DatabaseType.MONGO_DB, config);
        log.setAuth(true);
	}
    
}
