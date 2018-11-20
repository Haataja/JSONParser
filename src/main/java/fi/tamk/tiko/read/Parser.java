package fi.tamk.tiko.read;

import fi.tamk.tiko.write.JSONArray;
import fi.tamk.tiko.write.JSONObject;

/**
 * <h1>JSON parser</h1>
 * The reading component of the JSON-parser library. Parser has only one job
 * to read {@link String} and break it down to {@link JSONObject} that contains
 * primitive values, {@link JSONObject}s and {@link JSONArray}s.
 * <p>
 * To use parser read JSON-file any way you want and convert it to {@link String} to
 * pass to parse-method.
 * <p>
 * For example:
 * String text = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
 * Parser parser = new Parser();
 * JSONObject object = parser.parse(text);
 * Output JSONObject holds all data structure that was defined in the file. To get objects from
 * JSONObject call {@link JSONObject#get(String)}, e.g. boolean value = (boolean) object.get("key");
 *
 * @author Hanna Haataja <hanna.haataja@cs.tamk.fi>
 * @version 1.0, 11/20/2018
 * @since 1.0
 */
public class Parser {
    private JSONObject returnedObject;

    /**
     * Parses String to one JSONObject that can hold JSONObjects and JSONArrays.
     * @param text text that is parsed.
     * @return JSONObject that holds all the objects form the String.
     */
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
                    try {
                        Boolean valueBoolean = Boolean.parseBoolean(value);
                        return valueBoolean;
                    }catch (Exception er){
                        System.out.println("Unknown error: "+ ex.getMessage());
                    }
                }
            }
        }
        return null;
    }
}
