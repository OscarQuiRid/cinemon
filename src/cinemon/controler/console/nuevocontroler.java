package cinemon.controler.console;

public class nuevocontroler {
    
}


/*
 package cinemon.controler.console;

import cinemon.model.*;
import cinemon.model.enums.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CineControler {

    public static void nuevoCine(ArrayList<Cine> cines, Scanner scanner) {
        Cine cine = newCine(cines.size(), scanner);
        addRooms(cine, scanner);
        addShops(cine, scanner);
        cines.add(cine);
    }

    public static void nuevaVenta(ArrayList<Cine> cines, ArrayList<Venta> ventas, Scanner scanner) {
        int idventa = ventas.size();
        boolean novalido = true;
        Cine actual = seleccionarCine(cines, scanner);
        Sala sa_actual = seleccionarSala(actual, scanner);
        Session se_actual = seleccionarSesion(sa_actual, scanner);
        Butaca bu_actual = seleccionarButaca(se_actual, scanner);

        ventas.add(new Venta(idventa, bu_actual, se_actual));
    }

    public static void modificarCine(ArrayList<Cine> cines, Scanner scanner) {
        Cine actual = seleccionarCine(cines, scanner);
        System.out.println("Selecione accion: ");
        System.out.println("1.Nueva tienda");
        System.out.println("2.Modificar tienda");
        System.out.println("3.Nueva sala");
        System.out.println("4.Modificar sala");

        int optcine = 0;
        try {
            optcine = Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            optcine = -1;
        }

        switch (optcine) {
            case 1:
                addShops(actual, scanner);
                break;
            case 2:
                modificarTienda(actual, scanner);
                break;
            case 3:
                addRooms(actual, scanner);
                break;
            case 4:
                modificarSala(actual, scanner);
                break;
            default:
                break;
        }
    }

    public static void infoVentasYCine(ArrayList<Cine> cines, ArrayList<Venta> ventas) {
        System.out.println("-----cines------");
        for (Cine v : cines) {
            System.out.println(v);
        }
        System.out.println("\n\n\n -----ventas------");
        for (Venta v : ventas) {
            System.out.println(v);
        }
    }

    private static Cine seleccionarCine(ArrayList<Cine> cines, Scanner scanner) {
        System.out.println("Selecione cine: ");
        int num = 1;
        for (Cine c : cines) {
            System.out.println(num + ". " + c.getNombre());
            num++;
        }
        while (true) {
            try {
                return cines.get(Integer.parseInt(scanner.next()) - 1);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Debe de introducir un numero válido");
            }
        }
    }

    private static Sala seleccionarSala(Cine cine, Scanner scanner) {
        System.out.println("Selecione sala: ");
        int num = 1;
        for (Sala s : cine.getSalas()) {
            System.out.println(num + "." + s);
            num++;
        }
        while (true) {
            try {
                return cine.getSalas().get(Integer.parseInt(scanner.next()) - 1);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Debe de introducir un numero válido");
            }
        }
    }

    private static Session seleccionarSesion(Sala sala, Scanner scanner) {
        System.out.println("Introduce la sessión: ");
        int num = 1;
        for (Session se : sala.getSessiones()) {
            System.out.println(num + "." + se);
            num++;
        }
        while (true) {
            try {
                return sala.getSessiones().get(Integer.parseInt(scanner.next()) - 1);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Debe de introducir un numero válido");
            }
        }
    }

    private static Butaca seleccionarButaca(Session session, Scanner scanner) {
        System.out.println("Selecione butaca: ");
        for (ArrayList<Butaca> fila : session.getButacas()) {
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
                for (ArrayList<Butaca> fila : session.getButacas()) {
                    List<Butaca> filtrados = fila.stream().filter(b -> id == b.getId()).toList();
                    if (filtrados.size() > 0) {
                        Butaca bu_actual = filtrados.get(0);
                        bu_actual.setReservado(true);
                        return bu_actual;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe de introducir un numero");
            }
        }
    }

    private static void modificarTienda(Cine cine, Scanner scanner) {
        Tienda ti_actual = seleccionarTienda(cine, scanner);
        System.out.println("1.Agregar producto");
        System.out.println("2.Eliminar producto");

        int opttienda = 0;
        try {
            opttienda = Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            opttienda = -1;
        }

        switch (opttienda) {
            case 1:
                ArrayList<Productos> tmp_productos = new ArrayList<>();
                addProducts(ti_actual.getProductos().size(), ti_actual.getTipo(), scanner, tmp_productos);
                for (Productos p : tmp_productos) {
                    ti_actual.addProducto(p);
                }
                break;
            case 2:
                eliminarProducto(ti_actual, scanner);
                break;
            default:
                break;
        }
    }

    private static Tienda seleccionarTienda(Cine cine, Scanner scanner) {
        System.out.println("Selecione tienda");
        int num = 1;
        for (Tienda t : cine.getTiendas()) {
            System.out.println(num + "." + t);
            num++;
        }
        while (true) {
            try {
                return cine.getTiendas().get(Integer.parseInt(scanner.next()) - 1);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Debe de introducir un numero válido");
            }
        }
    }

    private static void eliminarProducto(Tienda tienda, Scanner scanner) {
        System.out.println("Selecione un producto");
        int num = 1;
        for (Productos t : tienda.getProductos()) {
            System.out.println(t.getId() + "." + t);
        }
        while (true) {
            try {
                tienda.removeProducto(Integer.parseInt(scanner.next()) - 1);
                break;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Debe de introducir un numero válido");
            }
        }
    }

    private static void modificarSala(Cine cine, Scanner scanner) {
        Sala sa_actual = seleccionarSala(cine, scanner);
        System.out.println("1.Agregar sessiones");
        System.out.println("2.Eliminar sessiones");

        int optsala = 0;
        try {
            optsala = Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            optsala = -1;
        }

        switch (optsala) {
            case 1:
                addSessions(sa_actual, scanner, sa_actual.getSessiones().get(0).getButacas());
                break;
            case 2:
                eliminarSesion(sa_actual, scanner);
                break;
            default:
                break;
        }
    }

    private static void eliminarSesion(Sala sala, Scanner scanner) {
        System.out.println("Selecione una sessión: ");
        int num = 1;
        for (Session t : sala.getSessiones()) {
            System.out.println(t.getId() + "." + t);
        }
        while (true) {
            try {
                sala.eliminarSession((Integer.parseInt(scanner.next()) - 1));
                break;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Debe de introducir un numero válido");
            }
        }
    }

    // Métodos existentes de CineControler (newCine, addRooms, addSessions, addProducts, addShops) se mantienen sin cambios
}
 */

 /*
  nuevo main
  
 */