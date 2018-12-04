package fi.tamk.tiko;

import fi.tamk.tiko.read.Parser;
import fi.tamk.tiko.write.JSONArray;
import fi.tamk.tiko.write.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args){
        System.out.println("Author: Hanna Haataja");
        /*JSONObject owner = new JSONObject();
        owner.put("nimi","Hanna");
        owner.put("ika", 26);
        JSONArray array = new JSONArray();
        JSONArray kissa = new JSONArray();
        kissa.add(false);
        kissa.add(null);
        kissa.add(true);
        array.add(kissa);
        JSONObject kiivi = new JSONObject();
        kiivi.put("Name","Kiivi");
        kiivi.put("ika",5);
        kiivi.put("paino",6.5);
        JSONObject kapu = new JSONObject();
        kapu.put("Name","Kapu");
        kapu.put("ika",4);
        kapu.put("paino",9.5);
        array.add(kapu);
        array.add(kiivi);
        owner.put("pets",array);
        owner.put("kiivi",kiivi);
        System.out.println(owner.toJsonString());


        String text = "";
        try{
            text = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("multi.json").toURI())));
        } catch (Exception e){
            System.out.println("Kettu ");
            e.printStackTrace();
        }
        //System.out.println(text);
        Parser parser = new Parser();
        JSONObject object = parser.parse(text);
        System.out.println("HELLO:");
        /*for(String key:object.getJson().keySet()){
            System.out.print(key + " : ");
            System.out.println(object.get(key));
        }
        System.out.println(object.toJsonString());*/
        //System.out.println(object.get("organization"));
        //for(int i = 0; i < ((JSONArray) object.get("studentNumbers")).length(); i++){
            //System.out.println(((JSONArray) object.get("studentnumbers")).get(i));
       //}

    }
}
