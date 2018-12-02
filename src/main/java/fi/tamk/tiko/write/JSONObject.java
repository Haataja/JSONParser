package fi.tamk.tiko.write;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <h2>JSONObject</h2>
 * JSONObject can be used to write or read JSON-files.
 *
 * <h3>Writing</h3>
 * To write an JSON-file use {@link #put(String, Object)} to map
 * keys and values to each others. Value can be any of the primitives or
 * {@link JSONObject} or {@link JSONArray}. Use {@link #toJsonString()}-method to
 * get {@link String} in JSON format. You can write it to file using any method you choose.
 * <p>
 * For example: JSONObject object = new JSONObject();<p>
 * object.put("Auhtor","Hanna Haataja");<p>
 * FileWriter fileWriter = new FileWriter(file);<p>
 * fileWriter.write(object.toJsonString());<p>
 * fileWriter.close();
 *
 * <h3>Reading</h3>
 * {@link JSONObject} is given as a return object by {@link fi.tamk.tiko.read.Parser#parse(String)}.
 * To get objects from the JSONObject you have to know the key of the value. Returned value
 * is an {@link Object}, to unlock the potential of that value just cast it to primitive value,
 * {@link JSONArray} or JSONObject, e.g. boolean value = (boolean) object.get("key");
 *
 * @author Hanna Haataja, hanna.haataja@cs.tamk.fi
 * @version 1.0, 11/20/2018
 * @since 1.0
 */
public class JSONObject {
    private Map<String, Object> json;

    /**
     * Constructor of the class.
     */
    public JSONObject() {
        json = new LinkedHashMap<>();
    }

    /**
     * Puts key-value pair to the object.
     *
     * @param key   The key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     */
    public void put(String key, Object value) {
        json.put(key, value);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned.
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     */
    public Object get(String key) {
        return json.get(key);
    }

    @Override
    public String toString() {
        String string = "{\n";
        int i = 0;
        for (String key : json.keySet()) {
            if (json.get(key) instanceof JSONArray) {
                string += "\"" + key + "\": " + json.get(key).toString();
            } else if (json.get(key) instanceof JSONObject) {
                string += "\"" + key + "\": " + json.get(key).toString();
            } else if (json.get(key) instanceof String) {
                string += "\"" + key + "\": \"" + json.get(key) + "\"";
            } else {
                string += "\"" + key + "\": " + json.get(key);
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

    /**
     * Formats the JSONObject to JSON-format that can be written in the JSON-file.
     *
     * @return {@link String} in JSON-format.
     */
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
            if ((JSONString[j].contains("{") || JSONString[j].contains("[")) && !(JSONString[j].contains("}") || JSONString[j].contains("]"))) {
                indentNumber++;
            }
        }
        for (int i = 0; i < JSONString.length; i++) {
            if (i < JSONString.length - 1) {
                returned += JSONString[i] + System.getProperty("line.separator");
            } else {
                returned += JSONString[i];
            }
        }
        return returned;
    }

    @Override
    public boolean equals(Object o) {
        boolean returned = true;
        if (o == null || getClass() != o.getClass()) {
            returned = false;
        } else if (o != this) {
            JSONObject object = (JSONObject) o;
            for (String key : json.keySet()) {
                System.out.println("Key: " + key + "values: "+ json.get(key)+ " " +object.get(key));
                if (object.get(key) == null) {
                    if (json.get(key) != null && !json.get(key).equals(object.get(key))) {
                        returned = false;
                        break;
                    }
                } else if(!json.get(key).equals(object.get(key))){
                    returned = false;
                    break;
                }
            }
        }
        return returned;
    }
}
