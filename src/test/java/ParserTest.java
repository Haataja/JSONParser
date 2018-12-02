import fi.tamk.tiko.read.Parser;
import fi.tamk.tiko.write.JSONArray;
import fi.tamk.tiko.write.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ParserTest {


    private static String basic;
    private static String objectInObject;
    private static String objectInObjectInObject;
    private static String arrayInObject;
    private static String objectsInArray;
    private static String arrayInArray;
    private static String objectInArrayInObject;
    private static String wild;
    private static Parser parser;
    private static JSONObject objectInArray;

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

    @Test
    public void testBasic() {
        JSONObject parsed = parser.parse(basic);
        JSONObject object = new JSONObject();
        object.put("key", "String");
        object.put("intKey", 44);
        object.put("doubleKey", 3.17);
        object.put("booleanKey", true);
        object.put("boolean", false);
        object.put("null", null);
        Assert.assertEquals(parsed, object);
    }

    @Test
    public void testObjectInObject() {
        JSONObject parsed = parser.parse(objectInObject);
        JSONObject object = new JSONObject();
        object.put("key", "String");
        object.put("intKey", 44);
        object.put("doubleKey", 3.17);
        object.put("booleanKey", true);
        object.put("boolean", false);
        object.put("null", null);
        JSONObject o = new JSONObject();
        o.put("new_key", object);
        Assert.assertEquals(parsed, o);
    }

    @Test
    public void testObjectInObjectInObject() {
        JSONObject parsed = parser.parse(objectInObjectInObject);
        JSONObject innerObject = new JSONObject();
        innerObject.put("key", null);
        innerObject.put("cat", "dog");
        JSONObject object = new JSONObject();
        object.put("key", innerObject);
        object.put("intKey", 44);
        object.put("doubleKey", 3.17);
        object.put("booleanKey", true);
        object.put("boolean", false);
        object.put("null", null);
        JSONObject o = new JSONObject();
        o.put("new_key", object);
        Assert.assertEquals(parsed, o);
    }

    @Test
    public void testArrayInObject() {
        JSONObject parsed = parser.parse(arrayInObject);
        JSONArray intArray = new JSONArray();
        JSONArray stringArray = new JSONArray();
        JSONArray doubleArray = new JSONArray();
        for (int i = 0; i < 10; i++) {
            intArray.add(i);
            stringArray.add("string " + i);
            doubleArray.add(i + 0.7);
        }

        JSONArray booleanArray = new JSONArray();
        booleanArray.add(true);
        booleanArray.add(false);

        JSONObject o = new JSONObject();
        o.put("int_array", intArray);
        o.put("string_array", stringArray);
        o.put("double_array", doubleArray);
        o.put("boolean_array", booleanArray);
        Assert.assertEquals(o, parsed);
    }

    @Test
    public void testObjectsInArray() {
        JSONObject parsed = parser.parse(objectsInArray);
        Assert.assertEquals(objectInArray, parsed);
    }

    @Test
    public void testArrayInArray() {
        JSONObject parsed = parser.parse(arrayInArray);
        JSONArray array1 = new JSONArray();
        array1.add("cat");
        array1.add("dog");
        array1.add("mouse");
        array1.add("moose");
        array1.add("rat");
        JSONArray array2 = new JSONArray();
        for (int i = 1; i < 5; i++) {
            array2.add(i);
        }
        JSONArray array3 = new JSONArray();
        array3.add(true);
        array3.add(null);
        JSONArray master = new JSONArray();
        master.add(array1);
        master.add(array2);
        master.add(array3);
        JSONObject object = new JSONObject();
        object.put("data", master);
        Assert.assertEquals(parsed, object);
    }

    @Test
    public void testArrayInArrayInArray() {
        JSONObject parsed = parser.parse(arrayInArray);
        JSONArray array0 = new JSONArray();
        array0.add(1.2);
        array0.add(1.1);
        JSONArray array1 = new JSONArray();
        array1.add(array0);
        array1.add("dog");
        array1.add("mouse");
        array1.add("moose");
        array1.add("rat");
        JSONArray array2 = new JSONArray();
        for (int i = 1; i < 5; i++) {
            array2.add(i);
        }
        JSONArray array3 = new JSONArray();
        array3.add(true);
        array3.add(array0);
        JSONArray master = new JSONArray();
        master.add(array1);
        master.add(array2);
        master.add(array3);
        JSONObject object = new JSONObject();
        object.put("data", master);
        Assert.assertEquals(parsed, object);
    }

    @Test
    public void testObjectInArrayInObject() {
        JSONObject parsed = parser.parse(objectInArrayInObject);
        JSONObject object = new JSONObject();
        objectInArray.put("qualifier", null);
        object.put("key", objectInArray);
        Assert.assertEquals(object, parsed);
    }

    @Test
    public void testArrayInObjectInArray() {
        JSONObject parsed = parser.parse(wild);
        JSONObject data = new JSONObject();
        JSONArray intArray = new JSONArray();
        for (int i = 0; i < 10; i++) {
            intArray.add(i);
        }
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        array.add(1.7);
        array.add("cat");
        array.add("mouse");
        array.add(44);
        object.put("key", array);
        object.put("intKey", 44);
        object.put("doubleKey", 3.17);
        object.put("booleanKey", true);
        object.put("boolean", false);
        object.put("null", null);
        intArray.add(object);
        data.put("int_array", intArray);
        data.put("boolean", true);
        JSONObject o = new JSONObject();
        o.put("data", data);

        Assert.assertEquals(o, parsed);
    }
}


