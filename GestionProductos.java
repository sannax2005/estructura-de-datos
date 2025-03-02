package Productos;

import java.util.Arrays;
import java.util.Scanner;

class Producto implements Comparable<Producto> {
    private String codigo;
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(String codigo, String nombre, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    
    public int compareTo(Producto otro) {
        return Double.compare(this.precio, otro.precio);
    }

    public int compararPorCantidad(Producto otro) {
        return Integer.compare(this.cantidad, otro.cantidad);
    }

   
    public String toString() {
        return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", cantidad=" + cantidad + "]";
    }
}

public class GestionProductos {
    private static Producto[] productos = new Producto[10];
    private static int contador = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        agregarProducto("P001", "Producto A", 10.5, 20);
        agregarProducto("P002", "Producto B", 20.5, 15);
        agregarProducto("P003", "Producto C", 15.0, 30);

        System.out.println("Productos iniciales:");
        mostrarProductos();

        System.out.println("\nOrdenando productos por precio (Burbuja):");
        ordenarPorPrecioBurbuja();
        mostrarProductos();

        System.out.println("\nOrdenando productos por cantidad (Inserción):");
        ordenarPorCantidadInsercion();
        mostrarProductos();

        System.out.println("\nIngrese un nuevo producto:");
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); 
        agregarProducto(codigo, nombre, precio, cantidad);
        
        System.out.println("\nLista de productos actualizada:");
        ordenarPorPrecioBurbuja();
        mostrarProductos();

        System.out.print("\nIngrese el precio del producto a buscar (búsqueda binaria): ");
        double precioBuscar = scanner.nextDouble();
        int indice = buscarProductoPorPrecio(precioBuscar);
        if (indice >= 0) {
            System.out.println("Producto encontrado: " + productos[indice]);
        } else {
            System.out.println("Producto no encontrado.");
        }

        scanner.close();
    }

    public static void agregarProducto(String codigo, String nombre, double precio, int cantidad) {
        if (contador < productos.length) {
            productos[contador] = new Producto(codigo, nombre, precio, cantidad);
            contador++;
        } else {
            System.out.println("No hay espacio para más productos.");
        }
    }

    public static void mostrarProductos() {
        for (int i = 0; i < contador; i++) {
            System.out.println(productos[i]);
        }
    }

    public static void ordenarPorPrecioBurbuja() {
        for (int i = 0; i < contador - 1; i++) {
            for (int j = 0; j < contador - 1 - i; j++) {
                if (productos[j].compareTo(productos[j + 1]) > 0) {
                    Producto temp = productos[j];
                    productos[j] = productos[j + 1];
                    productos[j + 1] = temp;
                }
            }
        }
    }

    public static void ordenarPorCantidadInsercion() {
        for (int i = 1; i < contador; i++) {
            Producto clave = productos[i];
            int j = i - 1;
            while (j >= 0 && productos[j].compararPorCantidad(clave) > 0) {
                productos[j + 1] = productos[j];
                j--;
            }
            productos[j + 1] = clave;
        }
    }

    public static int buscarProductoPorPrecio(double precio) {
        ordenarPorPrecioBurbuja();
        return Arrays.binarySearch(productos, 0, contador, new Producto("", "", precio, 0));
    }
}
