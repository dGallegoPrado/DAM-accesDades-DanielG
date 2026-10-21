package paises;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {
    private static String readChars(RandomAccessFile fitxer, int nChars) throws IOException {
        StringBuilder b = new StringBuilder();
        char ch = ' ';
        for (int i = 0; i < nChars; i++) {
            ch = fitxer.readChar();
            if (ch != '\0')
                b.append(ch);
        }
        return b.toString();
    }

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        Pais p;
        String nom, capital, codiISO;
        int poblacio;
        int id;
        long pos = 0;
        ConfigXml config = new ConfigXml("configXml.xml");
		config.load();

        try (RandomAccessFile fitxer = new RandomAccessFile("paisos.dat", "rw")) {

			while (fitxer.getFilePointer() != fitxer.length()) {
                System.out.println("País: " + fitxer.readInt());
                nom = readChars(fitxer, 40);
                codiISO = readChars(fitxer, 3);
                capital = readChars(fitxer, 40);
                poblacio = fitxer.readInt();
                p = new Pais(nom, codiISO, capital);
                p.setPoblacio(poblacio);
                System.out.println(p);
            }

            System.out.println("Introdueix número de registre: ");
            id = sc.nextInt();
            sc.nextLine();

            // per accedir a un registre multipliquem per la mida de cada registre.
            pos = (id - 1) * 174;

            if (pos < 0 || pos >= fitxer.length())
                throw new IOException("Número de registre invàlid.");

            fitxer.seek(pos);
            fitxer.readInt(); // Saltem l'id

            nom = readChars(fitxer, 40);
            readChars(fitxer, 43); // Ens saltem el codi ISO i la capital
            poblacio = fitxer.readInt();

            System.out.println( "País: " + nom + ", població actual: " + poblacio);
            System.out.println("Introdueix la nova població: ");
            poblacio = sc.nextInt();
            sc.nextLine();

            if (poblacio >= 0) {
                pos = fitxer.getFilePointer() - 4; // tornem enrere per sobreescriure la població
                fitxer.seek(pos);
                fitxer.writeInt(poblacio);
            } else {
                System.err.println("La població ha de ser positiva.");
            }
            sc.close();

            System.out.println(ConfigXml.ansiBg(config.fons)+
				ConfigXml.ansiFg(config.color1)+"Pais: " + nom + " Població actual: " + poblacio);

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}