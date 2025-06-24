import java.util.Scanner;
import com.opencsv.*;
import entities.UMovieImpl;

public class Main {
    public static void main(String[] args) {
        UMovieImpl um = new UMovieImpl();
        Scanner sc = new Scanner(System.in);
        String op;
//        Runtime runtime = Runtime.getRuntime();
        do{
            System.out.println("Menú principal: ");
            System.out.println("\tSeleccione la opción que desee: ");
            System.out.println("\t1. Carga de datos");
            System.out.println("\t2. Ejecutar consultas");
            System.out.println("\t3. Salir");
            op = sc.next();

            switch (op){
                case "1":
                    //Llamada al método de carga de datos
                    System.out.println("Cargando los datos");
                    long startTime = System.currentTimeMillis();
                    try {
                        um.cargaDatos();
                    }catch (Exception e){
                        System.err.println(e.getMessage());
                    }
                    long finalTime = System.currentTimeMillis();
                    long timeElapsed = finalTime - startTime;
                    System.out.println("Carga de datos exitosa, tiempo de ejecución de la carga: "+timeElapsed+"ms");
//                    System.out.println(runtime.totalMemory()/1048576);
                    break;
                case "2":
                    menuConsultas(sc, um);
                    break;
                case "3":
                    break;
                default:
                    System.out.println("La opción ingresada no es válida");
            }
        }while (!op.equals("3"));



    }

    static void menuConsultas(Scanner sc, UMovieImpl um){
        String op;
        do {
            System.out.println("1. Top 5 de las películas que más calificaciones por idioma.");
            System.out.println("2. Top 10 de las películas que mejor calificación media tienen por parte de los usuarios.");
            System.out.println("3. Top 5 de las colecciones que más ingresos generaron.");
            System.out.println("4. Top 10 de los directores que mejor calificación tienen.");
            System.out.println("5. Actor con más calificaciones recibidas en cada mes del año.");
            System.out.println("6. Usuarios con más calificaciones por género");
            System.out.println("7. Salir");
            op = sc.next();

            switch (op){
                case "1":

                    break;
                case "2":
                    System.out.println("Ejecutando consulta");
                    long startTime = System.currentTimeMillis();
                    um.topPeliculasMejorCalificacionMedia();
                    long finalTime = System.currentTimeMillis();
                    long timeElapsed = finalTime - startTime;
                    System.out.println("Tiempo de ejecución de la consulta: "+timeElapsed+"ms\n");
                    break;
                case "3":
                    //call a la f3
                    break;
                case "4":
                    //call a la f4
                    break;
                case "5":
                    //call a la f5
                    break;
                case "6":
                    //call a la f6
                    break;
                case "7":
                    break;
                default:
                    System.out.println("La opción ingresada no es válida");
            }
        }while(!op.equals("7"));
    }
}