package lectorrbyte;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        int b=0;
        Path path = Paths.get("prova.txt");

		try (BufferedReader lector = Files.newBufferedReader(path)) {
			// Llegeix byte a byte. -1 indica final de fitxer.
			while (b!=-1) {
				b = lector.read();
				if (b!=-1)
					System.out.print((char) b+" ");
			}
			System.out.println();
		} catch (FileNotFoundException e) { // error archivo no encontrado
			System.err.println(e);
		} catch (IOException e) { //error lectura o escriptura
			System.err.println(e);
		}

        byte[] contingut = new byte[100];
		int llegits;
		try (FileInputStream lector = new FileInputStream("prova.txt");) {
			// Llegeix tot el fitxer i ho guarda en un array.
			llegits = lector.read(contingut);
			if (llegits==-1) {
				System.out.println("S'ha arribat a final de fitxer.");
			} else {
				System.out.println("S'han llegit "+llegits+" bytes.");
			}
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
    }
}