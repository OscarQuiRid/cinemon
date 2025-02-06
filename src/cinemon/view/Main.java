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
    // Método para crear un nuevo cine
    public static void nuevo_cine(ArrayList<Cine> cines, Scanner scanner) {
        // Crear un nuevo cine usando el controlador
        Cine cine = CineControler.newCine(cines.size(), scanner);
        // Añadir salas al cine
        CineControler.addRooms(cine, scanner);
        // Añadir tiendas al cine
        CineControler.addShops(cine, scanner);
        // Agregar el cine a la lista de cines
        cines.add(cine);
    }

    public static void main(String[] args) {
        // Listas para almacenar cines y ventas
        ArrayList<Cine> cines = new ArrayList<>();
        ArrayList<Venta> ventas = new ArrayList<>();
        boolean run = true;

        // Crear un scanner para la entrada de datos
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        // Variables para el control de ventas y opciones
        int idventa = 0;
        boolean novalido = true;
        int num = 1;

        // Si no hay cines, crear uno primero
        if (cines.size() <= 0) {
            System.out.println("Primero crearemos un cine: ");
            nuevo_cine(cines, scanner);
        }

        // Bucle principal de ejecución
        while (run) {
            Cine actual = cines.get(0); // Selecciona el primer cine
            Sala sa_actual = new Sala(idventa, EnumTipoSala.STANDAR, null, 0); // Sala por defecto
            Session se_actual = actual.getSalas().get(0).getSessiones().get(0); // Sesión por defecto
            Butaca bu_actual = new Butaca(0, EnumTipoButaca.standar, false, false); // Butaca por defecto
            Tienda ti_actual = new Tienda(0, null, EnumTipoProducto.ALIMENTO); // Tienda por defecto
            ArrayList<Productos> tmp_productos = new ArrayList<>(); // Lista temporal de productos

            // Menú de opciones
            System.out.println("1.Añadir cine");
            System.out.println("2.Nueva venta");
            System.out.println("3.Modificar cine");
            System.out.println("4.Info ventas y cine");
            System.out.println("5.Salir");

            // Leer la opción seleccionada
            int opt, optcine, opttienda, optsala = 0;
            try {
                opt = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                opt = -1;
            }

            // Evaluar la opción seleccionada
            switch (opt) {
                case 1:
                    // Crear un nuevo cine
                    nuevo_cine(cines, scanner);
                    break;

                case 2:
                    // Nueva venta
                    System.out.println("Selecione cine: ");
                    num = 1;
                    // Mostrar lista de cines
                    for (Cine c : cines) {
                        System.out.println(num + ". " + c.getNombre());
                        num++;
                    }
                    novalido = true;
                    // Seleccionar cine
                    while (novalido) {
                        try {
                            actual = cines.get(Integer.parseInt(scanner.next()) - 1);
                            novalido = false;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Ese cine no existe");
                        } catch (NumberFormatException e) {
                            System.out.println("Debe de introducir un numero");
                        }
                    }

                    // Seleccionar sala y sesión
                    System.out.println("Selecione session: ");
                    novalido = true;
                    while (novalido) {
                        try {
                            System.out.println("Selecione sala: ");
                            num = 1;
                            // Mostrar salas disponibles
                            for (Sala s : actual.getSalas()) {
                                System.out.println(num + "." + s);
                                num++;
                            }
                            sa_actual = actual.getSalas().get(Integer.parseInt(scanner.next()) - 1);
                            System.out.println("Introduce la sessión: ");
                            num = 1;
                            // Mostrar sesiones disponibles
                            for (Session se : sa_actual.getSessiones()) {
                                System.out.println(num + "." + se);
                                num++;
                            }
                            se_actual = sa_actual.getSessiones().get(Integer.parseInt(scanner.next()) - 1);
                            novalido = false;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Esa sala o sessión no existe");
                        } catch (NumberFormatException e) {
                            System.out.println("Debe de introducir un numero");
                        }
                    }

                    // Seleccionar butaca
                    System.out.println("Selecione butaca: ");
                    for (ArrayList<Butaca> fila : se_actual.getButacas()) {
                        for (Butaca b : fila) {
                            if (b.isReservado()) {
                                System.out.print("    ");
                            } else {
                                System.out.print(" " + b.getId() + " ");
                            }
                        }
                        System.out.println("\n");
                    }

                    novalido = true;
                    while (novalido) {
                        try {
                            int id = Integer.parseInt(scanner.next()) - 1;
                            for (ArrayList<Butaca> fila : se_actual.getButacas()) {
                                List<Butaca> filtrados = fila.stream().filter(b -> id == b.getId()).toList();
                                if (filtrados.size() > 0) {
                                    bu_actual = filtrados.get(0);
                                    bu_actual.setReservado(true);
                                    novalido = false;
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Debe de introducir un numero");
                        }
                    }

                    // Registrar la venta
                    ventas.add(new Venta(idventa, bu_actual, se_actual));
                    idventa++;
                    break;

                case 3:
                    // Modificar cine
                    System.out.println("Selecione cine: ");
                    num = 1;
                    // Mostrar lista de cines
                    for (Cine c : cines) {
                        System.out.println(num + ". " + c.getNombre());
                        num++;
                    }
                    novalido = true;
                    // Seleccionar cine
                    while (novalido) {
                        try {
                            actual = cines.get(Integer.parseInt(scanner.next()) - 1);
                            novalido = false;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Ese cine no existe");
                        } catch (NumberFormatException e) {
                            System.out.println("Debe de introducir un numero");
                        }
                    }

                    // Menú de opciones para modificar el cine
                    System.out.println("Selecione accion: ");
                    System.out.println("1.Nueva tienda");
                    System.out.println("2.Modificar tienda");
                    System.out.println("3.Nueva sala");
                    System.out.println("4.Modificar sala");

                    // Leer la opción seleccionada
                    optcine = 0;
                    try {
                        optcine = Integer.parseInt(scanner.next());
                    } catch (NumberFormatException e) {
                        optcine = -1;
                    }

                    // Evaluar la opción seleccionada
                    switch (optcine) {
                        case 1:
                            // Añadir nueva tienda
                            CineControler.addShops(actual, scanner);
                            break;
                        case 2:
                            // Modificar tienda
                            num = 1;
                            novalido = true;
                            while (novalido) {
                                try {
                                    System.out.println("Selecione tienda");
                                    // Mostrar tiendas
                                    for (Tienda t : actual.getTiendas()) {
                                        System.out.println(num + "." + t);
                                    }
                                    ti_actual = actual.getTiendas().get(Integer.parseInt(scanner.next()));
                                    novalido = false;
                                } catch (NumberFormatException e) {
                                    System.out.println("Debe de introducir un numero");
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println("No existe la tienda");
                                }
                            }

                            // Menú de opciones para modificar productos en la tienda
                            System.out.println("1.Agregar producto");
                            System.out.println("2.Eliminar producto");

                            // Leer la opción seleccionada
                            opttienda = 0;
                            try {
                                opttienda = Integer.parseInt(scanner.next());
                                novalido = false;
                            } catch (NumberFormatException e) {
                                opttienda = -1;
                            }

                            // Evaluar la opción seleccionada
                            switch (opttienda) {
                                case 1:
                                    // Agregar productos a la tienda
                                    tmp_productos.clear();
                                    CineControler.addProducts(ti_actual.getProductos().size(), ti_actual.getTipo(), scanner, tmp_productos);
                                    for (Productos p : tmp_productos) {
                                        ti_actual.addProducto(p);
                                    }
                                    break;
                                case 2:
                                    // Eliminar productos de la tienda
                                    num = 1;
                                    novalido = true;
                                    while (novalido) {
                                        try {
                                            System.out.println("Selecione un producto");
                                            // Mostrar productos de la tienda
                                            for (Productos t : ti_actual.getProductos()) {
                                                System.out.println(t.getId() + "." + t);
                                            }
                                            ti_actual.removeProducto(Integer.parseInt(scanner.next()) - 1);
                                            novalido = false;
                                        } catch (NumberFormatException e) {
                                            System.out.println("Debe de introducir un numero");
                                        } catch (IndexOutOfBoundsException e) {
                                            System.out.println("No existe el producto");
                                        }
                                    }
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 3:
                            // Añadir nueva sala
                            CineControler.addRooms(actual, scanner);
                            break;
                        case 4:
                            // Modificar sala
                            num = 1;
                            novalido = true;
                            while (novalido) {
                                try {
                                    System.out.println("Selecione la sala");
                                    // Mostrar salas
                                    for (Sala t : actual.getSalas()) {
                                        System.out.println(num + "." + t);
                                    }
                                    sa_actual = actual.getSalas().get(Integer.parseInt(scanner.next()) - 1);
                                    novalido = false;
                                } catch (NumberFormatException e) {
                                    System.out.println("Debe de introducir un numero");
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println("No existe la sala");
                                }
                            }

                            // Menú de opciones para modificar sesiones en la sala
                            System.out.println("1.Agregar sessiones");
                            System.out.println("2.Eliminar sessiones");

                            // Leer la opción seleccionada
                            optsala = 0;
                            try {
                                optsala = Integer.parseInt(scanner.next());
                                novalido = false;
                            } catch (NumberFormatException e) {
                                optsala = -1;
                            }

                            // Evaluar la opción seleccionada
                            switch (optsala) {
                                case 1:
                                    // Añadir sesiones a la sala
                                    CineControler.addSessions(sa_actual, scanner, sa_actual.getSessiones().get(0).getButacas());
                                    break;
                                case 2:
                                    // Eliminar sesiones de la sala
                                    num = 1;
                                    novalido = true;
                                    while (novalido) {
                                        try {
                                            System.out.println("Selecione una sessión: ");
                                            // Mostrar sesiones de la sala
                                            for (Session t : sa_actual.getSessiones()) {
                                                System.out.println(t.getId() + "." + t);
                                            }
                                            sa_actual.eliminarSession((Integer.parseInt(scanner.next()) - 1));
                                            novalido = false;
                                        } catch (NumberFormatException e) {
                                            System.out.println("Debe de introducir un numero");
                                        } catch (IndexOutOfBoundsException e) {
                                            System.out.println("No existe la sesión");
                                        }
                                    }
                                    break;
                                default:
                                    break;
                            }
                            break;

                        default:
                            break;
                    }
                    break;

                case 4:
                    // Mostrar información de cines y ventas
                    System.out.println("-----cines------");
                    for (Cine v : cines) {
                        System.out.println(v);
                    }
                    System.out.println("\n\n\n -----ventas------");
                    for (Venta v : ventas) {
                        System.out.println(v);
                    }
                    break;

                default:
                    // Salir del programa
                    run = false;
                    break;
            }
        }
    }
}