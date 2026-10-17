import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        DataStore store = new DataStore();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("mini-redis> ");
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\s+");
            String command = parts[0].toUpperCase();

            if (command.equals("EXIT") || command.equals("QUIT")) {
                System.out.println("Bye!");
                break;
            }
            
            switch (command) {
                case "SET":
                    if (parts.length < 3) {
                        System.out.println("Error: SET command requires a key and a value.");
                    }
                    else{
                        System.out.println(store.set(parts[1], parts[2]));
                    }
                    break;

                case "GET":
                    if (parts.length < 2) {
                        System.out.println("Error: You must enter a key value for the GET command.");
                    }
                    else{
                        System.out.println(store.get(parts[1]));
                    }
                    break;

                case "DEL":
                    if (parts.length < 2) {
                        System.out.println("Error: You must enter a key value for the DEL command.");
                    }
                    else{
                        System.out.println("(integer) " + store.del(parts[1]));
                    }
                    break;

                default:
                    System.out.println("Error: Unknown command.");
                    break;
            }
        }
        scanner.close();
    }
}
