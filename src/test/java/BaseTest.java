import fi.tamk.tiko.read.Parser;
import fi.tamk.tiko.write.JSONArray;
import fi.tamk.tiko.write.JSONObject;
import org.junit.BeforeClass;

import java.nio.file.Files;
import java.nio.file.Paths;

public class BaseTest {

    protected static String basic;
    protected static String objectInObject;
    protected static String objectInObjectInObject;
    protected static String arrayInObject;
    protected static String objectsInArray;
    protected static String arrayInArray;
    protected static String arrayInArrayInArray;
    protected static String objectInArrayInObject;
    protected static String wild;
    protected static Parser parser;
    protected static JSONObject objectInArray;

    @BeforeClass
    public static void before() {
        parser = new Parser();
        try {
            basic = new String(Files.readAllBytes(Paths.get("src\\test\\resources\\basic.json")));
            objectInObject = new String(Files.readAllBytes(Paths.get("src\\test\\resources\\objectInObject.json")));
            arrayInObject = new String(Files.readAllBytes(Paths.get("src\\test\\resources\\arrayInObject.json")));
            objectsInArray = new String(Files.readAllBytes(Paths.get("src\\test\\resources\\objectsInArray.json")));
            wild = new String(Files.readAllBytes(Paths.get("src\\test\\resources\\wild.json")));
            objectInObjectInObject = new String(Files.readAllBytes(Paths.get("src\\test\\resources\\objectInObjectInObject.json")));
            arrayInArray = new String(Files.readAllBytes(Paths.get("src\\test\\resources\\arrayInArray.json")));
            arrayInArrayInArray = new String(Files.readAllBytes(Paths.get("src\\test\\resources\\arrayInArrayInArray.json")));
            objectInArrayInObject = new String(Files.readAllBytes(Paths.get("src\\test\\resources\\objectInArrayInObject.json")));
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }

        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();
        object.put("key", "String");
        object.put("intKey", 44);
        object.put("doubleKey", 3.17);
        object.put("booleanKey", true);
        object.put("boolean", false);
        object.put("null", null);
        array.add(object);
        array.add(object);
        array.add(object);
        JSONObject o = new JSONObject();
        o.put("data", array);
        objectInArray = o;
    }
}
