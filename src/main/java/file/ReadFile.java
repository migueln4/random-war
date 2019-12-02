package file;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;

@Data
public class ReadFile {

    private Object obj;
    private JsonObject jsonObject;
    private JsonArray participantes;
    private JsonParser parser;
    private Gson gson;
    private File file;
    private List<String> participantesList;

    public ReadFile() {
        this.parser = new JsonParser();
        this.gson = new Gson();
        this.participantesList = new ArrayList<>();
        this.file = getFileReader.apply("participantes.json");
        readingFile();
    }


    private void readingFile() {
        System.out.println("COMIENZA A LEERSE EL ARCHIVO...");
        try {
            this.obj = parser.parse(new FileReader(file));
            this.jsonObject = (JsonObject) obj;
            this.participantes = jsonObject.getAsJsonArray("participantes");
            Type type = new TypeToken<List<String>>() {
            }.getType();
            this.participantesList = gson.fromJson(participantes, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Function<String, File> getFileReader = filename -> {
        ClassLoader cl = getClass().getClassLoader();
        return new File(Objects.requireNonNull(cl.getResource(filename)).getFile());
    };

    public void imprimirTodo() {
        System.out.println("Imprimiendo...");
        for(String str : this.participantesList) {
            System.out.println("- "+str);
        }
    }

}
