package fi.tamk.tiko.write;

import java.util.ArrayList;

public class JSONArray {
    private ArrayList<Object> arrayList;

    public JSONArray() {
        arrayList = new ArrayList<>();
    }

    public void add(Object o) {
        arrayList.add(o);
    }

    public Object get(int index) {
        return arrayList.get(index);
    }

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

    public int length(){
        return arrayList.size();
    }
}
