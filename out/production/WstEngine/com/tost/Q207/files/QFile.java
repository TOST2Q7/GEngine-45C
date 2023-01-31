package com.tost.Q207.files;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class QFile {

    public static void writeToFile(String path, List<String> list) throws IOException {

        try {
            if (!(Files.exists(Path.of(path)))) {
                System.out.println("File create!");
                Files.createFile(Path.of(path));
            }
        }
        catch (Exception e) {}

        try(FileWriter writer = new FileWriter(path, false))
        {
            for (String i : list) {
                // запись всей строки
                writer.write(i);
                // запись по символам
                writer.append('\n');
            }

            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }



}
