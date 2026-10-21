package llistardirectory;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {     
    public static void main(String[] args) {
        if (args.length == 1) { 
            StringBuilder sb = new StringBuilder("----");
            Path dir = Paths.get(args[0]); // a la variable dir le da la ruta

            System.out.println("Fitxers del directori " + dir); // muestra la ruta

            if (Files.isDirectory(dir)) { // Comprobamos que sea directorio ant.es del stream

                try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
                    for (Path fitxer : stream) {
                        
                        if (Files.isDirectory(fitxer)) {
                            sb.setCharAt(0, 'd');
                        } else {
                            sb.setCharAt(0, '-');
                        }
                        if (Files.isReadable(fitxer)) {
                            sb.setCharAt(1, 'r');
                        }
                        if (Files.isWritable(fitxer)) {
                            sb.setCharAt(2, 'w');
                        }
                        if (Files.isExecutable(fitxer)) {
                            sb.setCharAt(3, 'x');
                        }

                        System.out.println(sb.toString() + " " + fitxer.getFileName());
                    }
                } catch (IOException | DirectoryIteratorException ex) {
                    System.err.println(ex);
                }
            } else if (Files.isRegularFile(dir)){
                        
                        if (Files.isRegularFile(dir)) {
                            sb.setCharAt(0, 'f');
                        } 
                        if (Files.isReadable(dir)) {
                            sb.setCharAt(1, 'r');
                        }
                        if (Files.isWritable(dir)) {
                            sb.setCharAt(2, 'w');
                        }
                        if (Files.isExecutable(dir)) {
                            sb.setCharAt(3, 'x');
                        }

                        System.out.println(sb.toString() + " " + dir.getFileName());
                    }
                else{
                    System.out.println("Insereix una ruta exemple"); //C:\Users\Dani\Desktop
                }
            }
    } 
}