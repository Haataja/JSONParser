package fi.tamk.tiko;

public class Main {
    public static void main(String[] args){
        JSONObject owner = new JSONObject();
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
        System.out.println(array.toString());
        System.out.println("---------------------------------");
        System.out.println(owner.toJsonString());
    }
}
