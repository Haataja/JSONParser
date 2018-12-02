import fi.tamk.tiko.write.JSONArray;
import fi.tamk.tiko.write.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class JSONObjectTest extends BaseTest {


    @Test
    public void testBasic() {
        JSONObject object = new JSONObject();
        object.put("key", "String");
        object.put("intKey", 44);
        object.put("doubleKey", 3.17);
        object.put("booleanKey", true);
        object.put("boolean", false);
        object.put("null", null);
        Assert.assertEquals(object.toJsonString(), basic);
    }

    @Test
    public void testObjectInObject() {
        JSONObject object = new JSONObject();
        object.put("key", "String");
        object.put("intKey", 44);
        object.put("doubleKey", 3.17);
        object.put("booleanKey", true);
        object.put("boolean", false);
        object.put("null", null);
        JSONObject o = new JSONObject();
        o.put("new_key", object);
        Assert.assertEquals(objectInObject, o.toJsonString());
    }

    @Test
    public void testObjectInObjectInObject() {
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
        Assert.assertEquals(objectInObjectInObject, o.toJsonString());
    }

    @Test
    public void testArrayInObject() {
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
        Assert.assertEquals(arrayInObject, o.toJsonString());
    }

    @Test
    public void testObjectsInArray() {
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
        Assert.assertEquals(objectsInArray, o.toJsonString());
    }

    @Test
    public void testArrayInObjectInArray() {
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
        Assert.assertEquals(wild, o.toJsonString());
    }

    @Test
    public void testObjectInArrayInObject() {
        JSONObject object = new JSONObject();
        objectInArray.put("qualifier", null);
        object.put("key", objectInArray);
        Assert.assertEquals(object.toJsonString(), objectInArrayInObject);
    }

    @Test
    public void testArrayInArrayInArray() {
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
        Assert.assertEquals(arrayInArrayInArray, object.toJsonString());
    }
    @Test
    public void testArrayInArray() {
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
        Assert.assertEquals(arrayInArray, object.toJsonString());
    }


}
