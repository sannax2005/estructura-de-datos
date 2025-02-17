package edades;
import java.util.*;

class Persona {
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String toString() {
        return nombre + " (" + edad + " años)";
    }
}

class CompararPorEdad implements Comparator<Persona> {

    public int compare(Persona p1, Persona p2) {
        
        return Integer.compare(p1.getEdad(), p2.getEdad());
    }
}

public class edades {
    public static void main(String[] args) {
       
        Persona[] personas = {
            new Persona("Santiago", 30),
            new Persona("Ana", 25),
            new Persona("Pedro", 35),
            new Persona("Luis", 20),
            new Persona("Maria", 40),
            new Persona("Carlos", 50),
            new Persona("Sofia", 15),
            new Persona("Laura", 28),
            new Persona("David", 33),
            new Persona("Raul", 22)
        };

        Arrays.sort(personas, new CompararPorEdad());

        System.out.println("Arreglo ordenado por edad:");
        for (Persona p : personas) {
            System.out.println(p);
        }
    }
}

