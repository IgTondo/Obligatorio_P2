import java.util.Scanner;
import entities.UMovieImpl;

public class Main {
    public static void main(String[] args) {
        UMovieImpl um = new UMovieImpl();
        Scanner sc = new Scanner(System.in);
        String op;

        do {
            System.out.println("Menú principal: ");
            System.out.println("\tSeleccione la opción que desee: ");
            System.out.println("\t1. Carga de datos");
            System.out.println("\t2. Ejecutar consultas");
            System.out.println("\t3. Salir");
            op = sc.nextLine().trim(); // ✅ MÁS SEGURO

            switch (op) {
                case "1":
                    System.out.println("Cargando los datos...");
                    long startTime = System.currentTimeMillis();
                    try {
                        um.cargaDatos();
                    } catch (Exception e) {
                        System.err.println("Error en cargaDatos(): " + e.getMessage());
                    }
                    long endTime = System.currentTimeMillis();
                    System.out.println("Carga de datos exitosa. Tiempo: " + (endTime - startTime) + " ms");
                    break;

                case "2":
                    menuConsultas(sc, um);
                    break;

                case "3":
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (!op.equals("3"));
    }

    static void menuConsultas(Scanner sc, UMovieImpl um) {
        String op;
        do {
            System.out.println("\nConsultas:");
            System.out.println("1. Top 5 de las películas que más calificaciones por idioma.");
            System.out.println("2. Top 10 de las películas que mejor calificación media tienen por parte de los usuarios.");
            System.out.println("3. Top 5 de las colecciones que más ingresos generaron.");
            System.out.println("4. Top 10 de los directores que mejor calificación tienen.");
            System.out.println("5. Actor con más calificaciones recibidas en cada mes del año.");
            System.out.println("6. Usuarios con más calificaciones por género");
            System.out.println("7. Volver");
            System.out.print("Ingrese opción: ");
            op = sc.nextLine().trim(); // ✅ MÁS SEGURO

            switch (op) {
                case "1":
                    System.out.println("Consulta 1 no implementada.");
                    break;
                case "2":
                    System.out.println("Ejecutando consulta 2...");
                    long t2i = System.currentTimeMillis();
                    um.topPeliculasMejorCalificacionMedia();
                    long t2f = System.currentTimeMillis();
                    System.out.println("Tiempo ejecución: " + (t2f - t2i) + " ms\n");
                    break;

                case "3":
                    System.out.println("Ejecutando consulta 3...");
                    um.topColeccionesMasIngresos();
                    break;

                case "4":
                    System.out.println("Ejecutando consulta 4...");
                    um.topDirectoresMejorCalificaciones();
                    break;

                case "5":
                    break;
                case "6":

                    break;

                case "7":
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (!op.equals("7"));
    }
}
