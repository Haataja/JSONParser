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
 * For example:<p>
 * String text = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));<p>
 * Parser parser = new Parser();<p>
 * JSONObject object = parser.parse(text);<p>
 * Output JSONObject holds all data structure that was defined in the file. To get objects from
 * JSONObject call {@link JSONObject#get(String)}, e.g. boolean value = (boolean) object.get("key");
 *
 * @author Hanna Haataja, hanna.haataja@cs.tamk.fi
 * @version 1.0, 11/20/2018
 * @since 1.0
 */
public class Parser {
    private JSONObject returnedObject;

    /**
     * Parses String to one JSONObject that can hold JSONObjects and JSONArrays.
     *
     * @param text text that is parsed.
     * @return JSONObject that holds all the objects form the String.
     */
    public JSONObject parse(String text) {
        text = text.replace("\n","");
        text = text.substring(1, text.length() - 1);
        returnedObject = new JSONObject();
        String[] lines = text.split(",");
        for (int i = 0; i < lines.length; i++) {
            String[] split = lines[i].split(":");
            if (split.length > 2) {
                split[1] = split[1] + ":" + split[2];
            }
            String key = split[0].replace("\"", "").trim();
            if (split[1].contains("[")) {
                JSONArray array = new JSONArray();
                int j = i;
                lines[j] = lines[j].substring(split[0].length() + 1);
                lines[j] = lines[j].replace("[", "");
                boolean loop = true;
                while (loop) {
                    if (lines[j].contains("{")) {
                        lines[j] = lines[j].replace("{", "");
                        JSONObject object = new JSONObject();
                        boolean innerLoop = true;
                        int k = j;
                        while (innerLoop) {
                            if (lines[k].contains("}")) {
                                innerLoop = false;
                                lines[k] = lines[k].replace("}", "");
                                if (lines[k].contains("]")) {
                                    loop = false;
                                    lines[k] = lines[k].replace("]", "");
                                }
                            }
                            String[] objectSplit = lines[k].split(":");
                            String objectKey = objectSplit[0].replace("\"", "").trim();
                            object.put(objectKey, parseObject(objectSplit[1]));
                            k++;
                        }
                        array.add(object);
                        j = k;
                    } else {
                        if (lines[j].contains("]")) {
                            lines[j] = lines[j].replace("]", "");
                            loop = false;
                        }
                        array.add(parseObject(lines[j]));
                        j++;
                    }
                }
                returnedObject.put(key, array);
                i = j - 1;
            } else if (lines[i].contains("{")) {
                lines[i] = lines[i].substring(split[0].length() + 1);
                lines[i] = lines[i].replace("{", "");

                JSONObject object = new JSONObject();
                boolean innerLoop = true;
                int k = i;
                while (innerLoop) {
                    if (lines[k].contains("}")) {
                        innerLoop = false;
                        lines[k] = lines[k].replace("}", "");
                    }
                    String[] objectSplit = lines[k].split(":");
                    String objectKey = objectSplit[0].replace("\"", "").trim();
                    object.put(objectKey, parseObject(objectSplit[1]));
                    k++;
                }
                returnedObject.put(key, object);
                i = k - 1;
            } else {
                returnedObject.put(key, parseObject(split[1]));
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
                    } catch (Exception er) {
                        System.out.println("Unknown error: " + ex.getMessage());
                    }
                }
            }
        }
        return null;
    }
}
