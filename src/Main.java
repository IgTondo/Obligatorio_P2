import java.util.Scanner;
import com.opencsv.*;

public class Main {
    public static void main(String[] args) {







        Scanner sc = new Scanner(System.in);
        String op;

        do{
            System.out.println("Menú principal: ");
            System.out.println("\tSeleccione la opción que desee: ");
            System.out.println("\t1. Carga de datos");
            System.out.println("\t2. Ejecutar consultas");
            System.out.println("\t3. Salir");
            op = sc.next();
        }while (!op.equals("3"));

    }
}