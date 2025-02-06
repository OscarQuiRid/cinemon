/* 
package cinemon;

import java.util.Scanner;
import cinemon.controler.console.CineControler;
import cinemon.model.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cine cine = null;
        
        while (true) {
            try {
                System.out.println("Bienvenido al sistema de gestión del cine.");
                System.out.println("1. Crear cine");
                System.out.println("2. Añadir salas al cine");
                System.out.println("3. Añadir tiendas al cine");
                System.out.println("4. Ver información del cine");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                
                int opcion = Integer.parseInt(scanner.next());
                if (opcion < 1 || opcion > 5) {
                    System.out.println("Opción no válida. Intente nuevamente.");
                    continue;
                }

                switch (opcion) {
                    case 1:
                        // Crear cine
                        if (cine != null) {
                            System.out.println("Ya se ha creado un cine.");
                            break;
                        }
                        System.out.println("Crear nuevo cine.");
                        cine = CineControler.newCine(cine != null ? cine.getId() + 1 : 1, scanner);
                        System.out.println("Cine creado con éxito.");
                        break;

                    case 2:
                        // Añadir salas
                        if (cine == null) {
                            System.out.println("Primero debe crear un cine.");
                            break;
                        }
                        System.out.println("Añadir salas al cine.");
                        CineControler.addRooms(cine, scanner);
                        System.out.println("Salas añadidas con éxito.");
                        break;

                    case 3:
                        // Añadir tiendas
                        if (cine == null) {
                            System.out.println("Primero debe crear un cine.");
                            break;
                        }
                        System.out.println("Añadir tiendas al cine.");
                        CineControler.addShops(cine, scanner);
                        System.out.println("Tiendas añadidas con éxito.");
                        break;

                    case 4:
                        // Ver información del cine
                        if (cine == null) {
                            System.out.println("No se ha creado ningún cine aún.");
                            break;
                        }
                        System.out.println("Información del cine: ");
                        System.out.println("ID: " + cine.getId());
                        System.out.println("Nombre: " + cine.getNombre());
                        System.out.println("Direcciones: " + cine.getDirecciones());
                        System.out.println("Salas: " + cine.getSalas().size());
                        System.out.println("Tiendas: " + cine.getTiendas().size());
                        break;

                    case 5:
                        // Salir
                        System.out.println("Saliendo del sistema.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Opción no válida.");
                }

            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        }
    }
}*/
/*
 import cinemon.controler.console.CineControler;
import cinemon.model.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cine cine = null;

        while (true) {
            System.out.println("Bienvenido al sistema de cine");
            System.out.println("1. Crear cine");
            System.out.println("2. Agregar salas al cine");
            System.out.println("3. Agregar sesiones a las salas");
            System.out.println("4. Agregar productos a las tiendas");
            System.out.println("5. Agregar tiendas al cine");
            System.out.println("6. Ver información del cine");
            System.out.println("0. Salir");

            int opcion = getOpcion(scanner);

            switch (opcion) {
                case 1:
                    cine = CineControler.newCine(getNuevoId(), scanner);
                    break;

                case 2:
                    if (cine == null) {
                        System.out.println("Primero debes crear un cine.");
                        break;
                    }
                    CineControler.addRooms(cine, scanner);
                    break;

                case 3:
                    if (cine == null || cine.getSalas().isEmpty()) {
                        System.out.println("Primero debes agregar salas.");
                        break;
                    }
                    for (Sala sala : cine.getSalas()) {
                        CineControler.addSessions(sala, scanner, new ArrayList<>());
                    }
                    break;

                case 4:
                    if (cine == null) {
                        System.out.println("Primero debes crear un cine.");
                        break;
                    }
                    for (Sala sala : cine.getSalas()) {
                        CineControler.addProducts(getNuevoId(), EnumTipoProducto.SOUVENIR, scanner, new ArrayList<>());
                    }
                    break;

                case 5:
                    if (cine == null) {
                        System.out.println("Primero debes crear un cine.");
                        break;
                    }
                    CineControler.addShops(cine, scanner);
                    break;

                case 6:
                    if (cine == null) {
                        System.out.println("No has creado ningún cine aún.");
                        break;
                    }
                    mostrarInformacionCine(cine);
                    break;

                case 0:
                    System.out.println("Gracias por usar el sistema.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Métodos auxiliares

    private static int getNuevoId() {
        // Aquí, puedes manejar la generación de IDs según tus necesidades (de forma incremental o cualquier otro enfoque)
        return (int) (Math.random() * 1000); // Solo para ejemplos
    }

    private static int getOpcion(Scanner scanner) {
        int opcion;
        while (true) {
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                if (opcion < 0 || opcion > 6) {
                    System.out.println("Opción no válida. Intente de nuevo.");
                } else {
                    return opcion;
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número.");
            }
        }
    }

    private static void mostrarInformacionCine(Cine cine) {
        System.out.println("Información del cine:");
        System.out.println("Nombre: " + cine.getNombre());
        System.out.println("Dirección: " + String.join(", ", cine.getDirecciones()));

        System.out.println("\nSalas:");
        for (Sala sala : cine.getSalas()) {
            System.out.println("Sala " + sala.getId() + ": Tipo " + sala.getTipoSala());
            for (Session sesion : sala.getSessiones()) {
                System.out.println("  - Sesión: " + sesion.getFecha() + " " + sesion.getHoraInicio());
            }
        }

        System.out.println("\nTiendas:");
        for (Tienda tienda : cine.getTiendas()) {
            System.out.println("Tienda " + tienda.getId() + ": Tipo " + tienda.getTipoProducto());
            for (Productos producto : tienda.getProductos()) {
                System.out.println("  - Producto: " + producto.getNombre() + " Precio: " + producto.getPrecio());
            }
        }
    }
}
 */
/*
 package cinemon.view;

import cinemon.model.enums.EnumTipoSala;
import cinemon.model.enums.EnumTipoProducto;
import cinemon.model.enums.EnumTipoButaca;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import cinemon.controler.console.CineControler;
import cinemon.model.*;

public class Main {

    public static void nuevo_cine(ArrayList<Cine> cines, Scanner scanner) {
        Cine cine = CineControler.newCine(cines.size(), scanner);
        CineControler.addRooms(cine, scanner);
        CineControler.addShops(cine, scanner);
        cines.add(cine);
    }

    public static void main(String[] args) {
        ArrayList<Cine> cines = new ArrayList<>();
        ArrayList<Venta> ventas = new ArrayList<>();
        boolean run = true;

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        if (cines.size() <= 0) {
            System.out.println("Primero crearemos un cine: ");
            nuevo_cine(cines, scanner);
        }

        while (run) {
            mostrarMenu();
            int opt = leerOpcion(scanner);

            switch (opt) {
                case 1:
                    nuevo_cine(cines, scanner);
                    break;
                case 2:
                    nuevaVenta(cines, ventas, scanner);
                    break;
                case 3:
                    modificarCine(cines, scanner);
                    break;
                case 4:
                    mostrarInfo(cines, ventas);
                    break;
                case 5:
                    run = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("1.Añadir cine");
        System.out.println("2.Nueva venta");
        System.out.println("3.Modificar cine");
        System.out.println("4.Info ventas y cine");
        System.out.println("5.Salir");
    }

    private static int leerOpcion(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void nuevaVenta(ArrayList<Cine> cines, ArrayList<Venta> ventas, Scanner scanner) {
        Cine actual = seleccionarCine(cines, scanner);
        Sala sa_actual = seleccionarSala(actual, scanner);
        Session se_actual = seleccionarSesion(sa_actual, scanner);
        Butaca bu_actual = seleccionarButaca(se_actual, scanner);

        ventas.add(new Venta(ventas.size(), bu_actual, se_actual));
    }

    private static Cine seleccionarCine(ArrayList<Cine> cines, Scanner scanner) {
        System.out.println("Selecione cine: ");
        for (int i = 0; i < cines.size(); i++) {
            System.out.println((i + 1) + ". " + cines.get(i).getNombre());
        }
        while (true) {
            try {
                return cines.get(Integer.parseInt(scanner.next()) - 1);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Ese cine no existe o debe de introducir un numero");
            }
        }
    }

    private static Sala seleccionarSala(Cine cine, Scanner scanner) {
        System.out.println("Selecione sala: ");
        for (int i = 0; i < cine.getSalas().size(); i++) {
            System.out.println((i + 1) + ". " + cine.getSalas().get(i));
        }
        while (true) {
            try {
                return cine.getSalas().get(Integer.parseInt(scanner.next()) - 1);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Esa sala no existe o debe de introducir un numero");
            }
        }
    }

    private static Session seleccionarSesion(Sala sala, Scanner scanner) {
        System.out.println("Introduce la sessión: ");
        for (int i = 0; i < sala.getSessiones().size(); i++) {
            System.out.println((i + 1) + ". " + sala.getSessiones().get(i));
        }
        while (true) {
            try {
                return sala.getSessiones().get(Integer.parseInt(scanner.next()) - 1);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Esa sesión no existe o debe de introducir un numero");
            }
        }
    }

    private static Butaca seleccionarButaca(Session sesion, Scanner scanner) {
        System.out.println("Selecione butaca: ");
        for (ArrayList<Butaca> fila : sesion.getButacas()) {
            for (Butaca b : fila) {
                if (b.isReservado()) {
                    System.out.print("    ");
                } else {
                    System.out.print(" " + b.getId() + " ");
                }
            }
            System.out.println("\n");
        }
        while (true) {
            try {
                int id = Integer.parseInt(scanner.next()) - 1;
                for (ArrayList<Butaca> fila : sesion.getButacas()) {
                    List<Butaca> filtrados = fila.stream().filter(b -> id == b.getId()).toList();
                    if (filtrados.size() > 0) {
                        Butaca butaca = filtrados.get(0);
                        butaca.setReservado(true);
                        return butaca;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe de introducir un numero");
            }
        }
    }

    private static void modificarCine(ArrayList<Cine> cines, Scanner scanner) {
        Cine actual = seleccionarCine(cines, scanner);

        System.out.println("Selecione accion: ");
        System.out.println("1.Nueva tienda");
        System.out.println("2.Modificar tienda");
        System.out.println("3.Nueva sala");
        System.out.println("4.Modificar sala");

        int optcine = leerOpcion(scanner);

        switch (optcine) {
            case 1:
                CineControler.addShops(actual, scanner);
                break;
            case 2:
                modificarTienda(actual, scanner);
                break;
            case 3:
                CineControler.addRooms(actual, scanner);
                break;
            case 4:
                modificarSala(actual, scanner);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    private static void modificarTienda(Cine cine, Scanner scanner) {
        Tienda ti_actual = seleccionarTienda(cine, scanner);

        System.out.println("1.Agregar producto");
        System.out.println("2.Eliminar producto");

        int opttienda = leerOpcion(scanner);

        switch (opttienda) {
            case 1:
                ArrayList<Productos> tmp_productos = new ArrayList<>();
                CineControler.addProducts(ti_actual.getProductos().size(), ti_actual.getTipo(), scanner, tmp_productos);
                for (Productos p : tmp_productos) {
                    ti_actual.addProducto(p);
                }
                break;
            case 2:
                eliminarProducto(ti_actual, scanner);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    private static Tienda seleccionarTienda(Cine cine, Scanner scanner) {
        System.out.println("Selecione tienda");
        for (int i = 0; i < cine.getTiendas().size(); i++) {
            System.out.println((i + 1) + ". " + cine.getTiendas().get(i));
        }
        while (true) {
            try {
                return cine.getTiendas().get(Integer.parseInt(scanner.next()));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("No existe la tienda o debe de introducir un numero");
            }
        }
    }

    private static void eliminarProducto(Tienda tienda, Scanner scanner) {
        System.out.println("Selecione un producto");
        for (Productos p : tienda.getProductos()) {
            System.out.println(p.getId() + "." + p);
        }
        while (true) {
            try {
                tienda.removeProducto(Integer.parseInt(scanner.next()) - 1);
                break;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("No existe el producto o debe de introducir un numero");
            }
        }
    }

    private static void modificarSala(Cine cine, Scanner scanner) {
        Sala sa_actual = seleccionarSala(cine, scanner);

        System.out.println("1.Agregar sessiones");
        System.out.println("2.Eliminar sessiones");

        int optsala = leerOpcion(scanner);

        switch (optsala) {
            case 1:
                CineControler.addSessions(sa_actual, scanner, sa_actual.getSessiones().get(0).getButacas());
                break;
            case 2:
                eliminarSesion(sa_actual, scanner);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    private static void eliminarSesion(Sala sala, Scanner scanner) {
        System.out.println("Selecione una sessión: ");
        for (Session s : sala.getSessiones()) {
            System.out.println(s.getId() + "." + s);
        }
        while (true) {
            try {
                sala.eliminarSession(Integer.parseInt(scanner.next()) - 1);
                break;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("No existe la sesión o debe de introducir un numero");
            }
        }
    }

    private static void mostrarInfo(ArrayList<Cine> cines, ArrayList<Venta> ventas) {
        System.out.println("-----cines------");
        for (Cine v : cines) {
            System.out.println(v);
        }
        System.out.println("\n\n\n -----ventas------");
        for (Venta v : ventas) {
            System.out.println(v);
        }
    }
}
 */


 /*
  * quiero optimizar mi aplicacion, actualmente tengo la funcionalidad repartida en dos archivos el primero es el main el cual es un caos, el segundo es el cinecontroler el cual esta estructurado pero faltan cosas que estan en el main.
necesito crear funciones que cada una haga toda la faena para lo que fue creada y no tenerlo partido entre cinecontroler y main. 
actualmente esta diseñado para usarse a traves del terminal pero en un futuro proximo generaremos la parte grafica entonces quiero hacerlo todo en funciones para poder usarlo mejor.

  */