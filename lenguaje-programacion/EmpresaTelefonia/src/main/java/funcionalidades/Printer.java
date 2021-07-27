package funcionalidades;

public class Printer {

    //La clase printer nos será de ayuda para acortar las líneas de código que quedan tras usar el conocido
    // System.out.println(text)

    //Imprimir sin salto de línea
    public static void print(String text) {
        System.out.print(text);
    }

    //Imprimir con salto de línea
    public static void println(String text) {
        System.out.println(text);
    }

    //Imprimir el toString de un Objeto
    public static void println(Object object) {
        System.out.println(object);
    }

    //Imprimir con texto de determinados colores
    public static String println(String text, String type) {
        String color = null;
        if ("done".equals(type)) color = "\u001B[32m";
        if ("error".equals(type)) color = "\u001B[31m";
        if ("warning".equals(type)) color = "\u001B[33m";
        println(color + text + "\u001B[0m");
        return color;
    }
}
