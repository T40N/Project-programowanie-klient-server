import com.mongodb.*;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;


public class MongoConnect {
    private MongoClient mongoClient;
    private DB database;

    public MongoConnect(){
        mongoClient = new MongoClient();
        database = mongoClient.getDB("test");
    }

    void saveUser(User user){

        DBObject newUser = new BasicDBObject("_id", user.getId())
                                .append("name", user.getName())
                                .append("surname", user.getSurname())
                                .append("email", user.getEmail())
                                .append("password", user.getPassword());
        DBCollection collection = database.getCollection("users");
        collection.insert(newUser);
    }

    boolean findUser(User user){
        DBObject newUser = new BasicDBObject("_id", user.getId())
                .append("name", user.getName())
                .append("surname", user.getSurname())
                .append("email", user.getEmail())
                .append("password", user.getPassword());

        if(database.getCollection("users").find(newUser)==null){
            return true;
        }
        return false;
    }

    public static final DBObject getUser(User user){
        return new BasicDBObject("_id", user.getId())
                    .append("name", user.getName())
                    .append("surname", user.getSurname())
                    .append("email", user.getEmail())
                    .append("password", user.getPassword());
    }

    public boolean passwordCheck(User user, String plainPassword){
        if(BCrypt.checkpw(plainPassword,user.getPassword())){
            return true;
        }else{
            return false;
        }
    }
}
