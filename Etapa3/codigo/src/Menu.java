import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Menu {

    public Menu() {
    }

    private void printMenuInicial() {
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Criar conta");
        System.out.println("2 - Login");
        System.out.println("3 - Sair");
        System.out.print("Opção -> ");
    }

    private int parseMenuInicial(Scanner scanner) throws IOException, ParseException {
        int option = scanner.nextInt();

        switch (option) {
            case 1 ->
            { 
                printCriarUtilizador();
            }
            case 2 -> { printUtilizador();
                currentMenu = parseUtilizador(scanner);
            }
            case 3 -> System.exit(0);
        }

        return currentMenu;
    }

    /**
     * Function that creates account
     *    - checks if username already exists in data base
     */
    private void printCriarUtilizador() {
        System.out.println(" Nome do utilizador: ");
        int username = scanner.nextString();
        if(username) // ver se existe algum username igual
        {
            System.out.println(" Nome do utilizador já existe!");
        }
        else{
            System.out.println(" Palavra-Passe: ");
            int password = scanner.nextString();
        }

        // introduzir username e password na bd

    }


    private void printUtilizador() {
        System.out.println(" Nome do utilizador: ");
    }

    private int parseUtilizador(Scanner scanner) throws IOException {
        int currentMenu = 1;
        int username = scanner.nextString();

        if(username) // if username is on the data base
        {           
            printPass();
            currentMenu = parsePass(scanner, username);
        }
        else{
            System.out.println("Nome de utilizador incorreto");
            return currentMenu = 0;
        }

        return currentMenu;
    }

    private void printPass() {
        System.out.println(" Palavra-Passe: ");
    }

    private int parsePass(Scanner scanner, String username) throws IOException {
        int currentMenu = 2;
        int password = scanner.nextString();

        if (password)// if password is on the data base e corresponde ao username
        {
            isAdministrador = False;
            // check if its admin or jogador
            if (isAdministrador == True) { 
                printAdmin(); 
                currentMenu = parseAdmin(scanner);
            } else { 
                printJogador(); 
                currentMenu = parseJogador(scanner);
            }

        } else{
            System.out.println("Palavra-Passe incorreta");
            printPass();
            return currentMenu = parsePass(scanner, username);
        }
        return currentMenu;
    }


    private void printAdmin() {
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Criar piloto");
        System.out.println("2 - Criar carro");
        System.out.println("3 - Criar circuito");
        System.out.println("4 - Criar campeonato");
        System.out.println("5 - Sair");
        System.out.print("Opção -> ");
    }

    private int parseAdmin(Scanner scanner) throws IOException, ParseException {
        int currentMenu = 3;
        int option = scanner.nextInt();
        switch (option) {
            case 1 :  break;
            case 2 :  break;
            case 3 :  break;
            case 4 :  break;
            case 5 : currentMenu = printMenuInicial(); parseMenuInicial(scanner); break;
            default: break;
        }
        return currentMenu;
    }

    private void printMenuJogador() {

    
        System.out.println("Escolha um campeonato:");

        // percorrer lista de campeonatos e escolher um

    // para cada jogador:

        System.out.println("Escolha um piloto:");

        // percorrer lista de pilotos e escolher um

        System.out.println("Escolha um carro:");

        // percorrer lista de carros e escolher um

    // começa simulação

    }
}
