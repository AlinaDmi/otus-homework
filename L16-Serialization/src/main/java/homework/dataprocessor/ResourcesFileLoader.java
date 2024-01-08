package homework.dataprocessor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import homework.model.Measurement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class ResourcesFileLoader implements Loader {
    private final String filename;
    private static final Type REVIEW_TYPE = new TypeToken<List<Measurement>>() {
    }.getType();

    public ResourcesFileLoader(String fileName) {
        this.filename = fileName;
    }

    @Override
    public List<Measurement> load() {
        // читает файл, парсит и возвращает результат

        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return gson.fromJson(reader, REVIEW_TYPE);
    }
}
