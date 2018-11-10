package fi.tamk.tiko.write;

import java.util.LinkedHashMap;
import java.util.Map;

public class JSONObject {
    private Map<String, Object> json;

    public JSONObject() {
        json = new LinkedHashMap<>();
    }

    public void put(String key, Object value) {
        json.put(key, value);
    }

    public Object get(String key) {
        return json.get(key);
    }

    @Override
    public String toString() {
        String string = "{\n";
        int i = 0;
        for (String key : json.keySet()) {
            if (json.get(key) instanceof JSONArray) {
                string += "\"" + key + "\":" + json.get(key).toString();
            } else if (json.get(key) instanceof JSONObject) {
                string += "\"" + key + "\":" + json.get(key).toString();
            } else if (json.get(key) instanceof String) {
                string += "\"" + key + "\":\"" + json.get(key) + "\"";
            } else {
                string += "\"" + key + "\":" + json.get(key);
            }
            if (i < json.keySet().size() - 1) {
                string += ",\n";
            } else {
                string += "\n";
            }
            i++;
        }
        string += "}";
        return string;
    }

    public String toJsonString() {
        String[] JSONString = toString().split("\n");
        String returned = "";
        String indent = "  ";
        int indentNumber = 0;

        for (int j = 0; j < JSONString.length; j++) {
            if ((JSONString[j].contains("}") || JSONString[j].contains("]")) && !(JSONString[j].contains("{") || JSONString[j].contains("["))) {
                indentNumber--;
            }
            String intended = "";
            for (int i = 0; i < indentNumber; i++) {
                intended += indent;
            }
            JSONString[j] = intended + JSONString[j];
            if ((JSONString[j].contains("{") || JSONString[j].contains("[")) && !(JSONString[j].contains("}") || JSONString[j].contains("]") )) {
                indentNumber++;
            }
        }
        for (String string : JSONString) {
            returned += string + "\n";
        }
        return returned;
    }
}
