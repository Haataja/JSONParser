package fi.tamk.tiko;

import fi.tamk.tiko.read.Parser;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args){
        /*JSONObject owner = new JSONObject();
        owner.put("nimi","Hanna");
        owner.put("ika", 26);
        JSONArray array = new JSONArray();
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
        System.out.println(owner.toJsonString());*/
        String text = "";
        try{
            text = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("jotain.json").toURI())));
        } catch (Exception e){
            System.out.println("Kettu ");
            e.printStackTrace();
        }
        System.out.println(text);
        Parser parser = new Parser();
        JSONObject object = parser.parse(text);
        /*for(String key:object.getJson().keySet()){
            System.out.println(key);
            System.out.println(object.get(key));
        }*/
        System.out.println(object.toJsonString());

    }
}
