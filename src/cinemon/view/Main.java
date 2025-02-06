package cinemon.view;

import cinemon.controler.console.CineControler;
import cinemon.model.Cine;
import cinemon.model.Venta;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Cine> cines = new ArrayList<>();
        ArrayList<Venta> ventas = new ArrayList<>();
        boolean run = true;

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        if (cines.size() <= 0) {
            System.out.println("Primero crearemos un cine: ");
            CineControler.nuevoCine(cines, scanner);
        }

        while (run) {
            System.out.println("1.Añadir cine");
            System.out.println("2.Nueva venta");
            System.out.println("3.Modificar cine");
            System.out.println("4.Info ventas y cine");
            System.out.println("5.Salir");

            int opt;
            try {
                opt = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                opt = -1;
            }

            switch (opt) {
                case 1:
                    CineControler.nuevoCine(cines, scanner);
                    break;
                case 2:
                    CineControler.nuevaVenta(cines, ventas, scanner);
                    break;
                case 3:
                    CineControler.modificarCine(cines, scanner);
                    break;
                case 4:
                    CineControler.infoVentasYCine(cines, ventas);
                    break;
                default:
                    run = false;
                    break;
            }
        }
    }
}