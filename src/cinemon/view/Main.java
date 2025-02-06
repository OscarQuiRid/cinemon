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

        int idventa = 0;
        boolean novalido = true;
        int num = 1;

        if (cines.size() <= 0) {
            System.out.println("Primero crearemos un cine: ");
            nuevo_cine(cines, scanner);
        }

        while (run) {
            Cine actual = cines.get(0);
            Sala sa_actual = new Sala(idventa, EnumTipoSala.STANDAR, null, 0);
            Session se_actual = actual.getSalas().get(0).getSessiones().get(0);
            Butaca bu_actual = new Butaca(0, EnumTipoButaca.standar, false, false);
            Tienda ti_actual = new Tienda(0, null, EnumTipoProducto.ALIMENTO);
            ArrayList<Productos> tmp_productos = new ArrayList<>();

            System.out.println("1.Añadir cine");
            System.out.println("2.Nueva venta");
            System.out.println("3.Modificar cine");
            System.out.println("4.Info ventas y cine");
            System.out.println("5.Salir");

            int opt, optcine, opttienda, optsala = 0;
            try {
                opt = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                opt = -1;
            }

            switch (opt) {
                case 1:
                    nuevo_cine(cines, scanner);
                    break;

                case 2:
                    System.out.println("Selecione cine: ");
                    num = 1;
                    for (Cine c : cines) {
                        System.out.println(num + ". " + c.getNombre());
                        num++;
                    }
                    novalido = true;
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

                    System.out.println("Selecione session: ");
                    novalido = true;
                    while (novalido) {
                        try {
                            System.out.println("Selecione sala: ");
                            num = 1;
                            for (Sala s : actual.getSalas()) {
                                System.out.println(num + "." + s);
                                num++;
                            }
                            sa_actual = actual.getSalas().get(Integer.parseInt(scanner.next()) - 1);
                            System.out.println("Introduce la sessión: ");
                            num = 1;
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

                    ventas.add(new Venta(idventa, bu_actual, se_actual));
                    idventa++;
                    break;

                case 3:
                    System.out.println("Selecione cine: ");
                    num = 1;
                    for (Cine c : cines) {
                        System.out.println(num + ". " + c.getNombre());
                        num++;
                    }
                    novalido = true;
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

                    System.out.println("Selecione accion: ");
                    System.out.println("1.Nueva tienda");
                    System.out.println("2.Modificar tienda");
                    System.out.println("3.Nueva sala");
                    System.out.println("4.Modificar sala");

                    optcine = 0;
                    try {
                        optcine = Integer.parseInt(scanner.next());
                    } catch (NumberFormatException e) {
                        optcine = -1;
                    }

                    switch (optcine) {
                        case 1:
                            CineControler.addShops(actual, scanner);
                            break;
                        case 2:
                            num = 1;
                            novalido = true;
                            while (novalido) {
                                try {
                                    System.out.println("Selecione tienda");
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

                            System.out.println("1.Agregar producto");
                            System.out.println("2.Eliminar producto");

                            opttienda = 0;
                            try {
                                opttienda = Integer.parseInt(scanner.next());
                                novalido = false;
                            } catch (NumberFormatException e) {
                                opttienda = -1;
                            }

                            switch (opttienda) {
                                case 1:
                                    tmp_productos.clear();
                                    CineControler.addProducts(ti_actual.getProductos().size(), ti_actual.getTipo(), scanner, tmp_productos);
                                    for (Productos p : tmp_productos) {
                                        ti_actual.addProducto(p);
                                    }
                                    break;
                                case 2:
                                    num = 1;
                                    novalido = true;
                                    while (novalido) {
                                        try {
                                            System.out.println("Selecione un producto");
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
                            CineControler.addRooms(actual, scanner);
                            break;
                        case 4:
                            num = 1;
                            novalido = true;
                            while (novalido) {
                                try {
                                    System.out.println("Selecione la sala");
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

                            System.out.println("1.Agregar sessiones");
                            System.out.println("2.Eliminar sessiones");

                            optsala = 0;
                            try {
                                optsala = Integer.parseInt(scanner.next());
                                novalido = false;
                            } catch (NumberFormatException e) {
                                optsala = -1;
                            }

                            switch (optsala) {
                                case 1:
                                    CineControler.addSessions(sa_actual, scanner, sa_actual.getSessiones().get(0).getButacas());
                                    break;
                                case 2:
                                    num = 1;
                                    novalido = true;
                                    while (novalido) {
                                        try {
                                            System.out.println("Selecione una sessión: ");
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
                    run = false;
                    break;
            }
        }
    }
}
/* */