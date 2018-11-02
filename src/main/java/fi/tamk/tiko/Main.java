package fi.tamk.tiko;

public class Main {
    public static void main(String[] args){
        JSONObject owner = new JSONObject();
        owner.put("nimi","Hanna");
        owner.put("ika", 26);
        JSONObject object = new JSONObject();
        object.put("nimi","Kiivi");
        object.put("ika",5);
        object.put("paino", 6.5);
        owner.put("pet",object);
        System.out.println(owner.toJsonString());
    }
}
