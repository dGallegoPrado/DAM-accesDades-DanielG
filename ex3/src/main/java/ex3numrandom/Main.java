    package ex3numrandom;
    import java.io.DataInputStream;
    import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
    import java.io.FileOutputStream;
    import java.io.IOException;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import java.util.Random;
import java.util.Scanner;

    public class Main {
        public static void main(String[] args) {
            Random random = new Random(); 
            int sumaAl=0;
            char letra1; 
            char letra2; 
            char letra3;
            Scanner sc = new Scanner(System.in);
            Path fitxerSecret = Paths.get("secret.bin");

            try (DataOutputStream escriptor = new DataOutputStream(new FileOutputStream(fitxerSecret.toFile()));) {
                
                for (int i = 0; i < 10; i++) {
                    int numeroAleatori = random.nextInt(4); //Random entre 0 a 3
                    sumaAl += numeroAleatori;
                    letra1 = (char) (random.nextInt(26)+97);
                    letra2 = (char) (random.nextInt(26)+97);
                    letra3 = (char) (random.nextInt(26)+97);

                    escriptor.writeInt(sumaAl);
                    escriptor.writeChar(letra1);
                    escriptor.writeChar(letra2);
                    escriptor.writeChar(letra3);
                    System.out.println( sumaAl +"-" + letra1 + letra2 + letra3);
                    
                }

            } catch (FileNotFoundException ex) {
                System.err.println(ex);
            } catch (IOException ex) {
                System.err.println(ex);
            }

                System.out.print("Introdueix un num secret: ");
                int numUsuari = sc.nextInt();
              

                System.out.print("Introdueix les 3 lletres secretes: ");
                String lletresUsuari = sc.next().trim();

                boolean trobat = false;

        try (DataInputStream lector = new DataInputStream(new FileInputStream(fitxerSecret.toFile()))) {           
            for (int i = 0; i < 10; i++) {
                int numFitxer = lector.readInt();
                char c1 = lector.readChar();
                char c2 = lector.readChar();
                char c3 = lector.readChar();

                String lletresFitxer = "" + c1 + c2 + c3;

                if (numUsuari == numFitxer && lletresUsuari.equalsIgnoreCase(lletresFitxer)) {
                    trobat = true;
                    break;
                }
            }

            if (trobat) {
                System.out.println("Codi correcte");
            } else {
                System.out.println("Codi incorrecte");
            }}//try 
                            catch (FileNotFoundException ex) {
                                System.err.println(ex);
                            } catch (IOException ex) {
                                System.err.println(ex);
                        }

        }
    }