package com.authApp;

import com.google.common.hash.Hashing;
import org.json.JSONObject;
import spark.Spark;
import java.nio.charset.StandardCharsets;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Spark.port(getPort());
        Spark.staticFileLocation("/static");
        Spark.post("/login",(req,res)->{
            req.session(true);
            JSONObject obj = new JSONObject(req.body());
            String esp = Hashing.sha256().hashString("test", StandardCharsets.UTF_8).toString();
            String given = Hashing.sha256().hashString(obj.getString("password"), StandardCharsets.UTF_8).toString();
            if(obj.getString("email").equals("jay@mail.com") && given.equals(esp)) {
                req.session().attribute("email", "jay@mail.com");
                return "";
            }
            return "";
        });
        Spark.get("/isLogged",(req,res)->{
            return String.valueOf(req.session().attribute("email")!=null);
        });

        Spark.get("/logout",(rq,rs)->{
            rq.session().attribute("email",null);
            return "";
        });
    }

    static int getPort(){
        if(System.getenv("PORT")!=null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 80;
    }
}
