package me.maploop.main;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class DataRetriever {
    private final static File pwFile = new File("pw_.mapdata");

    public static JSONObject fetchAll() {
        makeFile();

        try (FileReader fr = new FileReader(pwFile)) {
            return (JSONObject) new JSONParser().parse(fr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject fetch(String name) {
        makeFile();
        return (JSONObject) fetchAll().get(name);
    }

    public static void save(String id, JSONObject obj) {
        makeFile();
        JSONObject existing = fetchAll();
        existing.put(id, obj);
        try (FileWriter fw = new FileWriter(pwFile)) {
            fw.write(existing.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void makeFile() {
        if (!pwFile.exists()) {
            try {
                pwFile.createNewFile();
                try (FileWriter fw = new FileWriter(pwFile)) {
                    fw.write("{}");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
