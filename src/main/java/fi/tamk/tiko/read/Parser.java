package fi.tamk.tiko.read;

import fi.tamk.tiko.JSONArray;
import fi.tamk.tiko.JSONObject;


public class Parser {
    JSONObject returnedObject;

    public JSONObject parse(String text) {
        String[] lines = text.split("\n");
        returnedObject = new JSONObject();

        for (int i = 1; i < lines.length - 1; i++) {
            String[] split = lines[i].split(":");
            String key = split[0].replace("\"", "").trim();
            returnedObject.put(key, parseObject(split[1].replace(",","")));
        }
        return returnedObject;
    }

    private Object parseObject(String value) {
        value = value.trim();
        if (value.contains("\"")) {
            return value.replace("\"", "");
        } else {
            try {
                int valueInt = Integer.parseInt(value);
                return valueInt;
            } catch (NumberFormatException e) {
                try {
                    double valueDouble = Double.parseDouble(value);
                    return valueDouble;
                } catch (Exception ex) {
                    // TODO: logger
                    System.out.println("Unknown error: "+ ex.getMessage());
                }
            }
        }
        return null;
    }

    private Object parseJSONObject(String[] values, int index) {

        return null;
    }
}
