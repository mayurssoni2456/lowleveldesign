package singleton;
import java.util.concurrent.locks.Lock;

public class DBConnection {

    static DBConnection dbInstance = null;
    static Lock lock;
    private DBConnection(){}

    public static DBConnection getDBInstance(){
        if(dbInstance == null){
            synchronized(DBConnection.class) {
                if(dbInstance == null){
                    dbInstance = new DBConnection();
                }
            }
        }
        return dbInstance;
    }
}
