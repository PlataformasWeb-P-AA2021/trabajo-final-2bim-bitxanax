package funcionalidades;

import entidades.PlanCelular;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static funcionalidades.Printer.print;
import static funcionalidades.Printer.println;

public class Application {
    Scanner keyboard = new Scanner(System.in);
    Serializador serializer = new Serializador("");
    Deserializador deserializador = new Deserializador("");
    PlansFactory plansFactory = new PlansFactory();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Application app = new Application();
        app.showMainMenu();
    }

    public void showMainMenu() throws IOException, ClassNotFoundException {
        while (true) {
            println("\n ╔════════Gestor planes═══════════╗");
            println(" ║ Escoga una opción:             ║");
            println(" ║   ► 1. Insertar datos          ║");
            println(" ║   ► 2. Mostrar datos           ║");
            println(" ║   ► 3. Salir                   ║");
            println(" ╙════════════════════════════════╝");
            print("   > ");
            String option = keyboard.nextLine();
            option.trim();
            switch (option) {
                case "1":
                    showPlansMenu("1", " Crear ");
                    break;
                case "2":
                    showPlansMenu("2", "Mostrar");
                    break;
                case "3":
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }


    public void showPlansMenu(String option, String title) throws IOException, ClassNotFoundException {
        while (true) {
            println(String.format("\n ╔═══════════════════ %s ════════════════════╗ ", title));
            println(" ║  Escoga una opción:                            ║ ");
            println(" ║   ►1. Plan post pago minutos                   ║");
            println(" ║   ►2. Plan post pago megas                     ║");
            println(" ║   ►3. Plan post pago minutos y megas           ║");
            println(" ║   ►4. Plan post pago minutos y megas económico ║ ");
            println(" ║   ►5. Mostrar todo                             ║ ");
            println(" ║   ►6. Volver al menú principal                 ║ ");
            println(" ╙════════════════════════════════════════════════╝");
            print("   > ");

            String option2 = keyboard.nextLine();
            option2.trim();
            if (option2.equals("6")) return;

            //Opción 1: Mostrar planes
            //Opción 2: Crear planes
            switch (option) {
                case "1":
                    if (option2.equals("5")) showByOption(option2);
                        else createByOption(option2);
                    break;
                case "2":
                    showByOption(option2);
                    break;
            }
        }
    }

    public void showByOption(String option) throws IOException, ClassNotFoundException {
        //Se obtienen los datos del archivo serelizado
        ArrayList<PlanCelular> planes = deserializador.deserializeData();

        //Cada plan de celular tiene un id único que lo diferencian uno de otro.
        //Según la opción que se escoja en el menú de opciones de planes. se determinará un id.
        String idToCheck = null;
        switch (option) {
            case "1":
                idToCheck = "postPagoMinutos";
                break;
            case "2":
                idToCheck = "postPagoMegas";
                break;
            case "3":
                idToCheck = "postPagoMinutosMegas";
                break;
            case "4":
                idToCheck = "postPagoMinutosMegasEconomico";
                break;
            case "5":
                idToCheck = "none";
                break;
            default:
                println("  Opción incorrecta.", "error");
                return;
        }

        //En caso de que se haya seleccionado un determinado plan, se filtrarán los planes almacenados que solo tengan
        //dicho id. (filtro: plan.getPlanId().equals(idToCheck))
        //Y en caso de que se haya escogido la opción de mostrar todos los planes, no se aplicará ningún filtro. Por lo que
        //en cada iteración, se mostrán los planes sin distinción alguna.

        for (PlanCelular plan : planes) {
            if (plan.getPlanId().equals(idToCheck) || idToCheck.equals("none")) {
                println("  =============================" + plan, "warning");
            }
        }
    }


    public void createByOption(String option) throws IOException, ClassNotFoundException {
        //Según la opción seleccionada del menú de opciones, se invocará a determinados métodos de la clase PlansFactory
        //los cuales se encargán de tomar los datos necesarios según el caso lo amerite.
        PlanCelular plan = null;
        switch (option) {
            case "1":
                plan = plansFactory.insertPlanPostPagoMinutos();
                break;
            case "2":
                plan = plansFactory.insertPlanPostPagoMegas();
                break;
            case "3":
                plan = plansFactory.insertPlanPostPagoMinutosMegas();
                break;
            case "4":
                plan = plansFactory.insertPlanPostPagoMinutosMegasEconomico();
                break;
            default:
                println("  Opción incorrecta.", "error");
                return;
        }
        //Por último, se serializan nuevamente los datos, pero con un nuevo plan creado.
        serializer.serializeData(plan);
    }


}
