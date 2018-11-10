package fi.tamk.tiko.read;

import fi.tamk.tiko.write.JSONArray;
import fi.tamk.tiko.write.JSONObject;


public class Parser {
    private JSONObject returnedObject;

    public JSONObject parse(String text) {
        String[] lines = text.split("\n");
        returnedObject = new JSONObject();

        for (int i = 1; i < lines.length - 1; i++) {
            String[] split = lines[i].split(":");
            String key = split[0].replace("\"", "").trim();
            if (split[1].contains("[")) {
                JSONArray array = new JSONArray();
                int j = i + 1;
                boolean loop = true;
                while (loop) {
                    if (lines[j].contains("{")) {
                        JSONObject object = new JSONObject();
                        boolean innerLoop = true;
                        int k = j + 1;
                        while (innerLoop) {
                            String[] objectSplit = lines[k].split(":");
                            String objectKey = objectSplit[0].replace("\"", "").trim();
                            if (objectSplit[1].contains(",")) {
                                object.put(objectKey,parseObject(objectSplit[1].replace(",", "")));
                                k++;
                            } else {
                                object.put(objectKey,parseObject(objectSplit[1]));
                                k++;
                                if (lines[k].contains("}")) {
                                    innerLoop = false;
                                    if(lines[k + 1].contains("]")){
                                        loop = false;
                                    }
                                }
                            }
                        }
                        array.add(object);
                        j = k + 1;
                    } else {
                        if (lines[j].contains(",")) {
                            array.add(parseObject(lines[j].replace(",", "")));
                            j++;
                        } else {
                            array.add(parseObject(lines[j]));
                            j++;
                            if (lines[j].contains("]")) {
                                loop = false;
                            }
                        }
                    }
                }
                returnedObject.put(key, array);
                i = j;
            } else if (lines[i].contains("{")) {
                JSONObject object = new JSONObject();
                boolean innerLoop = true;
                int k = i + 1;
                while (innerLoop) {
                    String[] objectSplit = lines[k].split(":");
                    String objectKey = objectSplit[0].replace("\"", "").trim();
                    if (objectSplit[1].contains(",")) {
                        object.put(objectKey,parseObject(objectSplit[1].replace(",", "")));
                        k++;
                    } else {
                        object.put(objectKey,parseObject(objectSplit[1]));
                        k++;
                        if (lines[k].contains("}")) {
                            innerLoop = false;
                        }
                    }
                }
                returnedObject.put(key,object);
                i = k;
            } else {
                returnedObject.put(key, parseObject(split[1].replace(",","")));
            }
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
                    System.out.println("Unknown error: "+ ex.getMessage());
                }
            }
        }
        return null;
    }
}
