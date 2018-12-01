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
    private static String arrayInObject;
    private static String objectsInArray;
    private static String wild;
    private static Parser parser;

    @BeforeClass
    public static void before() {
        parser = new Parser();
        try {
            basic = new String(Files.readAllBytes(Paths.get("src\\test\\resources\\basic.json")));
            objectInObject = new String(Files.readAllBytes(Paths.get("src\\test\\resources\\objectInObject.json")));
            arrayInObject = new String(Files.readAllBytes(Paths.get("src\\test\\resources\\arrayInObject.json")));
            objectsInArray = new String(Files.readAllBytes(Paths.get("src\\test\\resources\\objectsInArray.json")));
            wild = new String(Files.readAllBytes(Paths.get("src\\test\\resources\\wild.json")));
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
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
        //Assert.assertEquals(object, parsed);
        Assert.assertTrue(parsed.equals(object));
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
        Assert.assertEquals(o, parsed);
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
        Assert.assertEquals(o,parsed);
    }

    @Test
    public void testWild() {
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


