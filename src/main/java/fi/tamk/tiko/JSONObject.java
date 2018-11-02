package fi.tamk.tiko;

import java.util.HashMap;
import java.util.Map;

public class JSONObject {
    private Map<String,Object> json;

    public JSONObject(){
        json = new HashMap<String, Object>();
    }

    public void put(String key, Object value){
        json.put(key,value);
    }

    public Object get(String key){
        return json.get(key);
    }

    public String toString(){
        String string = "{";
        int i = 0;
        for (String key: json.keySet()){
            if(json.get(key) instanceof JSONArray){
                string += "\""+key+"\":["+((JSONArray)json.get(key)).toJsonString();
            }else if(json.get(key) instanceof JSONObject){
                string += "\""+key+"\":"+((JSONObject) json.get(key)).toJsonString();
            } else if (json.get(key) instanceof String){
                string += "\""+key+"\":\""+json.get(key)+"\"";
            }else {
                string += "\""+key+"\":"+json.get(key);
            }
            if(i < json.keySet().size() - 1){
                string += ",";
            }
            i++;
        }
        string += "}";
        return string;
    }

    public String toJsonString(){
        String JSONStirng = toString();
        String indent = "  ";
        int indentNumber = 0;
        return JSONStirng;
    }
}
