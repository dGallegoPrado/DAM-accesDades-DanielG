package exercici2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int contador=0;

        if (args.length == 1) {
            Path dir = Paths.get(args[0]);
            System.out.println("Fitxers del directori " + dir);

            if (Files.isDirectory(dir)) {
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) { //lee linea a linea
                    for (Path fitxer : stream) {
                        System.out.println(fitxer.getFileName()); 
                    }

                    System.out.println("Introdueix el nom d'un fitxer");
                    String nomFitxer = sc.nextLine();
                    sc.close(); //Cerrar Scanner

                    Path abrirArchivo = dir.resolve(nomFitxer); //ruta que hace referencia a la ruta del archivo que ha preguntado el usuario

                    try (BufferedReader lector = new BufferedReader(new FileReader(abrirArchivo.toFile()))) {
                        String s;
                        while ((s = lector.readLine()) != null) //lee mientras no sea el final 
                            System.out.println(s); //Escribe las lineas
                    }

                    try (FileReader lector = new FileReader(abrirArchivo.toFile())) { //lee  caracter a caracter
                        int lectura;
                        char ch;

                        while ((lectura = lector.read()) != -1) { //lee mientras no se acaben los caracteres  
                            ch = (char) lectura;
                            System.out.println(ch);
                                //if (ch=='a' || ch=='A') { //si los caracteres son a o A suma 1
                                //    contador++;
                               // }
                        }
                        System.out.println("Hi han en total:"+contador+" lletres a");
                    }

                } catch (IOException | DirectoryIteratorException ex) {
                    System.err.println(ex);
                }
            } else {
                System.err.println(dir.toString() + " no és un directori");
            }
        } else {
            System.err.println("Ús: java LlistarDirectori <directori>");
        }
    }
}