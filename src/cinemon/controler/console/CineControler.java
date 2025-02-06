package cinemon.controler.console;

import cinemon.model.enums.EnumTipoSouvenir;
import cinemon.model.enums.EnumTipoSala;
import cinemon.model.enums.EnumTipoProducto;
import cinemon.model.enums.EnumTipoButaca;
import cinemon.model.enums.EnumTipoAlimento;
import cinemon.model.enums.EnumTamanio;
import cinemon.model.enums.EnumAlergeno;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cinemon.model.*;

public class CineControler {

    public static Cine newCine (int id, Scanner scanner){
        while (true) {
            try {
                System.out.println("Nombre del cine:");
                String nombre = scanner.next();
                ArrayList<String> direciones = new ArrayList<>();

                System.out.println("Pais del cine:");
                String pais = scanner.next();
                if (!pais.matches("[a-zA-Z ]+")) {
                    throw new CineControlerException("El país solo puede contener letras.");
                }
                direciones.add(pais);

                System.out.println("Provincia del cine:");
                String provincia = scanner.next();
                if (!provincia.matches("[a-zA-Z ]+")) {
                    throw new CineControlerException("La provincia solo puede contener letras.");
                }
                direciones.add(provincia);

                System.out.println("Municipio del cine:");
                String municipio = scanner.next();
                if (!municipio.matches("[a-zA-Z ]+")) {
                    throw new CineControlerException("El municipio solo puede contener letras.");
                }
                direciones.add(municipio);

                System.out.println("Calle del cine:");
                direciones.add(scanner.next());

                System.out.println("Numero de edificio:");
                String numero = scanner.next();
                if (!numero.matches("[0-9]+")) {
                    throw new CineControlerException("El número solo puede contener dígitos.");
                }
                direciones.add(numero);

                return new Cine(id, nombre, direciones, null, null);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void addRooms(Cine cine, Scanner scanner){
        while (true) {
            try {
                ArrayList<Sala> salas2 = new ArrayList<>();
                boolean mas_salas = true;

                while (mas_salas) {
                    System.out.println("Ahora vamos a crear una sala: ");
                    System.out.println("Selecione el tipo de sala: ");
                    for (int i = 0 ; i < EnumTipoSala.values().length; i++){
                        System.out.println(i+1 + "." + EnumTipoSala.values()[i]);
                    }

                    int tipoVal;
                    try {
                        tipoVal = Integer.parseInt(scanner.next())-1;
                        if (tipoVal < 0 || tipoVal >= EnumTipoSala.values().length) {
                            throw new CineControlerException("Tipo de sala no válido.");
                        }
                    } catch (NumberFormatException e) {
                        throw new CineControlerException("Debe introducir un número válido para el tipo de sala.");
                    }
                    EnumTipoSala tipoSala = EnumTipoSala.values()[tipoVal];

                    System.out.println("Numero de filas: ");
                    int filas;
                    try {
                        filas = Integer.parseInt(scanner.next())-1;
                    } catch (NumberFormatException e) {
                        throw new CineControlerException("El número de filas debe ser un número.");
                    }

                    System.out.println("Numero de columnas: ");
                    int columnas;
                    try {
                        columnas = Integer.parseInt(scanner.next())-1;
                    } catch (NumberFormatException e) {
                        throw new CineControlerException("El número de columnas debe ser un número.");
                    }

                    ArrayList<ArrayList<Butaca>> butacas = new ArrayList<>();
                    for (int fila = 1; fila <= (filas+1) ; fila++){
                        ArrayList<Butaca> butacas2 = new ArrayList<>();
                        for (int columna = 1; columna <= (columnas+1) ; columna ++){
                            String id = Integer.toString(fila) + Integer.toString(columna);
                            System.out.println("Creando butaca con el id: "+ id);
                            butacas2.add(new Butaca(Integer.parseInt(id), EnumTipoButaca.values()[tipoVal], false, false));
                        }
                        butacas.add(butacas2);
                    }

                    int idsala = 0;
                    ArrayList<Sala> salasActuales = cine.getSalas();
                    if (salasActuales != null){
                        idsala = salasActuales.size();
                    }
                    Sala sala = new Sala(salas2.size() + idsala, tipoSala, null, tipoSala.getPrecio());
                    addSessions(sala,scanner,butacas);
                    salas2.add(sala);

                    System.out.println("Desea añadir otra sala [s/n]");
                    String respuesta = scanner.next().toLowerCase();
                    if (!respuesta.equals("s") && !respuesta.equals("n")) {
                        throw new CineControlerException("Debe introducir 's' para sí o 'n' para no.");
                    }
                    mas_salas = respuesta.equals("s");
                }

                ArrayList<Sala> salasActuales = cine.getSalas();
                if (salasActuales != null){
                    salas2.addAll(salasActuales);
                }
                cine.setSalas(salas2);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void addSessions(Sala sala , Scanner scanner, ArrayList<ArrayList<Butaca>> butacas){
    while (true) {
        try {
            ArrayList<Session> sessiones  = new ArrayList<>();
            System.out.println("Ahora vamos a añadir las sessiones de la sala con su respectiva pelicula / serie: ");
            boolean mas_sessiones = true;

            while (mas_sessiones) {
                Proyecion pelicula;

                System.out.println("Fecha en formato dd/MM/yyyy :");
                String fecha = scanner.next();
                if (!fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
                    throw new CineControlerException("La fecha debe estar en el formato dd/MM/yyyy.");
                }

                System.out.println("Hora de inicio en formato HH:mm :");
                String hora_inicio = scanner.next();
                if (!hora_inicio.matches("\\d{2}:\\d{2}")) {
                    throw new CineControlerException("La hora de inicio debe estar en el formato HH:mm.");
                }
                String fechaHoraInicio = fecha + " " + hora_inicio;

                System.out.println("Hora de final en formato HH:mm :");
                String hora_final = scanner.next();
                if (!hora_final.matches("\\d{2}:\\d{2}")) {
                    throw new CineControlerException("La hora de final debe estar en el formato HH:mm.");
                }
                String fechaHoraFinal = fecha + " " + hora_final;

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime inicio;
                LocalDateTime fin;
                try {
                    inicio = LocalDateTime.parse(fechaHoraInicio, formatter);
                    fin = LocalDateTime.parse(fechaHoraFinal, formatter);
                } catch (DateTimeParseException e) {
                    throw new CineControlerException("Error al analizar la fecha y hora: " + e.getMessage());
                }

                System.out.println("Es una serie [s/n]: ");
                boolean is_serie = scanner.next().toLowerCase().equals("s");

                System.out.println("Duración: ");
                int duracion;
                try {
                    duracion = Integer.parseInt(scanner.next());
                } catch (NumberFormatException e) {
                    throw new CineControlerException("La duración debe ser un número entero.");
                }

                System.out.println("Titulo ");
                String titulo = scanner.next();

                System.out.println("Director");
                String director = scanner.next();
                if (!director.matches("[a-zA-Z ]+")) {
                    throw new CineControlerException("El director solo puede contener letras.");
                }

                System.out.println("Sipnosis");
                String sipnosis = scanner.next();

                System.out.println("Tema");
                String tema = scanner.next();
                if (!tema.matches("[a-zA-Z ]+")) {
                    throw new CineControlerException("El tema solo puede contener letras.");
                }

                System.out.println("Recomenciones");
                String recomendaciones = scanner.next();

                System.out.println("Genero");
                String genero = scanner.next();
                if (!genero.matches("[a-zA-Z ]+")) {
                    throw new CineControlerException("El género solo puede contener letras.");
                }

                if (is_serie){
                    System.out.println("Temporadas: ");
                    int temporadas;
                    try {
                        temporadas = Integer.parseInt(scanner.next());
                    } catch (NumberFormatException e) {
                        throw new CineControlerException("Las temporadas deben ser un número entero.");
                    }

                    System.out.println("Episodio: ");
                    int episodios;
                    try {
                        episodios = Integer.parseInt(scanner.next());
                    } catch (NumberFormatException e) {
                        throw new CineControlerException("Los episodios deben ser un número entero.");
                    }

                    pelicula = new Serie(
                        sessiones.size(), duracion, titulo, director,
                        sipnosis, tema, recomendaciones, genero, temporadas,
                        episodios
                    );
                } else {
                    pelicula = new Proyecion(sessiones.size(), duracion, titulo, director,
                    sipnosis, tema, recomendaciones, genero);
                }

                int idsesion = 0;
                ArrayList<Session> sessionesActuales = sala.getSessiones();
                if (sessionesActuales != null){
                    idsesion = sessionesActuales.size();
                }
                sessiones.add(new Session(sessiones.size() + idsesion, fechaHoraInicio, fechaHoraFinal, pelicula, fecha, new ArrayList<ArrayList<Butaca>>(butacas)));

                System.out.println("Desea añadir otra session [s/n]");
                String respuesta = scanner.next().toLowerCase();
                if (!respuesta.equals("s") && !respuesta.equals("n")) {
                    throw new CineControlerException("Debe introducir 's' para sí o 'n' para no.");
                }
                mas_sessiones = respuesta.equals("s");
            }

            ArrayList<Session> sessionesActuales = sala.getSessiones();
            if (sessionesActuales != null){
                sessiones.addAll(sessionesActuales);
            }
            sala.setSessiones(sessiones);
            break;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

    public static void addProducts(int id, EnumTipoProducto ta , Scanner scanner, ArrayList<Productos> productos){
        while (true) {
            try {
                boolean mas_productos = true;

                while (mas_productos) {
                    System.out.println("Ahora vamos a añadir productos a la tienda: ");

                    switch (ta) {
                        case SOUVENIR:
                            System.out.println("Nombre del souvenir: ");
                            String souvenir_nombre = scanner.next();
                            System.out.println("Precio del souvenir: ");
                            double souvenir_precio;
                            try {
                                souvenir_precio = Double.parseDouble(scanner.next());
                            } catch (NumberFormatException e) {
                                throw new CineControlerException("El precio del souvenir debe ser un número.");
                            }
                            System.out.println("Stock del souvenir: ");
                            int souvenir_stock;
                            try {
                                souvenir_stock = Integer.parseInt(scanner.next());
                            } catch (NumberFormatException e) {
                                throw new CineControlerException("El stock del souvenir debe ser un número entero.");
                            }
                            System.out.println("Tipo del souvenir: ");
                            for (int i = 0 ; i < EnumTipoSouvenir.values().length; i++){
                                System.out.println(i+1 + "." + EnumTipoSouvenir.values()[i]);
                            }
                            EnumTipoSouvenir souvenir_tipo;
                            try {
                                souvenir_tipo = EnumTipoSouvenir.values()[Integer.parseInt(scanner.next())-1];
                            } catch (Exception e) {
                                throw new CineControlerException("Tipo de souvenir no válido.");
                            }

                            System.out.println("Edicion limitada [s/n]: ");
                            Boolean souvenir_limitado = scanner.next().toLowerCase().equals("s");
                            System.out.println("Franquicia del souvenir: ");
                            String souvenir_franqucia = scanner.next();
                            Souvenir souvenir = new Souvenir(id, souvenir_nombre, souvenir_precio,
                            souvenir_stock, souvenir_tipo, souvenir_limitado, souvenir_franqucia);
                            productos.add(souvenir);
                            break;

                        case ALIMENTO:
                            System.out.println("Nombre del alimento: ");
                            String alimento_nombre = scanner.next();
                            System.out.println("Precio del alimento: ");
                            double alimento_precio;
                            try {
                                alimento_precio = Double.parseDouble(scanner.next());
                            } catch (NumberFormatException e) {
                                throw new CineControlerException("El precio del alimento debe ser un número.");
                            }
                            System.out.println("Stock del alimento: ");
                            int alimento_stock;
                            try {
                                alimento_stock = Integer.parseInt(scanner.next());
                            } catch (NumberFormatException e) {
                                throw new CineControlerException("El stock del alimento debe ser un número entero.");
                            }
                            System.out.println("Tipo del alimento: ");
                            for (int i = 0 ; i < EnumTipoAlimento.values().length; i++){
                                System.out.println(i+1 + "." + EnumTipoAlimento.values()[i]);
                            }
                            EnumTipoAlimento alimento_tipo;
                            try {
                                alimento_tipo = EnumTipoAlimento.values()[Integer.parseInt(scanner.next())-1];
                            } catch (Exception e) {
                                throw new CineControlerException("Tipo de alimento no válido.");
                            }

                            System.out.println("Tamaño del alimento: ");
                            for (int i = 0 ; i < EnumTamanio.values().length; i++){
                                System.out.println(i+1 + "." + EnumTamanio.values()[i]);
                            }
                            EnumTamanio alimento_tamanio;
                            try {
                                alimento_tamanio = EnumTamanio.values()[Integer.parseInt(scanner.next())-1];
                            } catch (Exception e) {
                                throw new CineControlerException("Tamaño del alimento no válido.");
                            }
                            System.out.println("Vegano[s/n]");
                            boolean vegan = scanner.next().toLowerCase().equals("s");

                            ArrayList<EnumAlergeno> alergenos = new ArrayList<>();
                            System.out.println("¿Tiene alergenos? [s/n]: ");
                            boolean mas_alergenos = scanner.next().toLowerCase().equals("s");
                            while (mas_alergenos) {
                                System.out.println("Alergeno: ");
                                for (int i = 0 ; i < EnumAlergeno.values().length; i++){
                                    System.out.println(i+1 + "." + EnumAlergeno.values()[i]);
                                }
                                EnumAlergeno alimento_Alergeno;
                                try {
                                    alimento_Alergeno = EnumAlergeno.values()[Integer.parseInt(scanner.next())-1];
                                } catch (Exception e) {
                                    throw new CineControlerException("Alergeno no válido.");
                                }
                                alergenos.add(alimento_Alergeno);
                                System.out.println("Desea añadir otro alergeno [s/n]");
                                mas_alergenos = scanner.next().toLowerCase().equals("s");
                            }

                            Alimento alimento = new Alimento(id + productos.size(), alimento_nombre, alimento_precio, alimento_stock, alimento_tipo,
                            alimento_tamanio, vegan, alergenos);
                            productos.add(alimento);
                            break;

                        default:
                            throw new CineControlerException("Tipo de producto no reconocido");
                    }

                    System.out.println("Desea añadir otro producto [s/n]");
                    String respuesta = scanner.next().toLowerCase();
                    if (!respuesta.equals("s") && !respuesta.equals("n")) {
                        throw new CineControlerException("Debe introducir 's' para sí o 'n' para no.");
                    }
                    mas_productos = respuesta.equals("s");
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void addShops(Cine cine , Scanner scanner){
        while (true) {
            try {
                boolean mas_tiendas = true;
                ArrayList<Tienda> tiendas = new ArrayList<>();

                while (mas_tiendas) {
                    ArrayList<Productos> productos = new ArrayList<>();
                    System.out.println("Ahora vamos a crear las tiendas");
                    System.out.println("Tipo de articulos a vender: ");
                    System.out.println("1.Souvenirs");
                    System.out.println("2.Alimentos");
                    EnumTipoProducto ta = EnumTipoProducto.values()[Integer.parseInt(scanner.next())-1];

                    int idtienda = 0;
                    ArrayList<Tienda> tiendasActuales = cine.getTiendas();
                    if (tiendasActuales != null){
                        idtienda = tiendasActuales.size();
                    }
                    int idprod = productos.size();
                    if (tiendasActuales != null){
                        idprod = tiendasActuales.size();
                    }

                    addProducts(idprod, ta, scanner, productos);
                    tiendas.add(new Tienda(tiendas.size() + idtienda, productos, ta));

                    System.out.println("Desea añadir otra tienda [s/n]");
                    String respuesta = scanner.next().toLowerCase();
                    if (!respuesta.equals("s") && !respuesta.equals("n")) {
                        throw new CineControlerException("Debe introducir 's' para sí o 'n' para no.");
                    }
                    mas_tiendas = respuesta.equals("s");
                }

                ArrayList<Tienda> tiendasActuales = cine.getTiendas();
                if (tiendasActuales != null){
                    tiendas.addAll(tiendasActuales);
                }
                cine.setTiendas(tiendas);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

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
}

class CineControlerException extends Exception {
    public CineControlerException(String message) {
        super(message);
    }

    public CineControlerException(String message, Throwable cause) {
        super(message, cause);
    }
}