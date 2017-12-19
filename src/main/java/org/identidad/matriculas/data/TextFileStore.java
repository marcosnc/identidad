package org.identidad.matriculas.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.identidad.matriculas.utils.Rutinas.formatDate;

public class TextFileStore implements Store {

    private final Path textFile;

    public TextFileStore(Path textFile) {
        this.textFile = textFile;
        if (!Files.exists(textFile)) {
            try {
                Files.createFile(textFile);
            } catch (IOException e) {
                System.err.println("No puedo crear el archivo de datos. " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void add(String matricula, Date fecha) {
        String newLine = String.format("%s - %s\n", matricula, formatDate(fecha));
        try {
            synchronized (this) {
                Files.write(textFile, newLine.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.err.println("No puedo escribir en el archivo de datos. " + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public List<String> query(String filter) {
        try {
            synchronized (this) {
                return Files.lines(textFile).filter(line -> line.contains(filter)).collect(Collectors.toList());
            }
        } catch (IOException e) {
            System.err.println("No puedo leer del archivo de datos. " + e.getMessage());
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
