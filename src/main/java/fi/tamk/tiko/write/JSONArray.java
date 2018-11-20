package fi.tamk.tiko.write;

import java.util.ArrayList;

/**
 *
 */
public class JSONArray {
    private ArrayList<Object> arrayList;

    /**
     * Constructor of the class.
     */
    public JSONArray() {
        arrayList = new ArrayList<>();
    }

    /**
     * Adds object to JSONArray.
     * @param o Object to be added.
     */
    public void add(Object o) {
        arrayList.add(o);
    }

    /**
     * Get the object from JSONArray by the index.
     * @param index index of the object desired.
     * @return The object in the JSONArray.
     */
    public Object get(int index) {
        return arrayList.get(index);
    }

    /**
     * Returns the {@link String} representation of the JSONArray.
     * @return String representation.
     */
    public String toString() {
        String returned = "[";
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) instanceof String) {
                returned += "\n\"" + arrayList.get(i) + "\"";
            } else if (arrayList.get(i) instanceof JSONObject || arrayList.get(i) instanceof JSONArray) {
                returned += "\n" + arrayList.get(i).toString();
            } else {
                returned += "\n" + arrayList.get(i);
            }
            if (i < arrayList.size() - 1) {
                returned += ",";
            } else if (arrayList.get(i) instanceof JSONObject) {
                returned += "\n";
            } else {
                returned += "\n";
            }
        }
        returned += "]";
        return returned;
    }

    /**
     * Returns the lenght of the JSONArray.
     * @return the size of the JSONArray.
     */
    public int length(){
        return arrayList.size();
    }
}
