import java.util.*;

/**
 * Esta classe implementa um menu em modo texto.
 */
public class Menu {

    // Interfaces auxiliares

    /** Functional interface para handlers. */
    public interface Handler {
        void execute();
    }

    /** Functional interface para pré-condições. */
    public interface PreCondition {
        boolean validate();
    }

    // Varíável de classe para suportar leitura

    private static Scanner is = new Scanner(System.in);

    // Variáveis de instância

    private String titulo;                  // Titulo do menu (opcional)
    private List<String> opcoes;            // Lista de opções
    private List<PreCondition> disponivel;  // Lista de pré-condições
    private List<Handler> handlers;         // Lista de handlers

    // Construtor
    /**
     * Constructor vazio para objectos da classe Menu.
     *
     * Cria um menu vazio, ao qual se podem adicionar opções.
     */
    public Menu() {
        this.titulo = "Menu";
        this.opcoes = new ArrayList<>();
        this.disponivel = new ArrayList<>();
        this.handlers = new ArrayList<>();
    }

    /**
     * Constructor para objectos da classe Menu.
     *
     * Cria um menu de opções sem event handlers.
     * Utilização de listas é útil para definir menus dinâmicos.
     *
     * @param titulo O titulo do menu
     * @param opcoes Uma lista de Strings com as opções do menu.
     */
    public Menu(String titulo, List<String> opcoes) {
        this.titulo = titulo;
        this.opcoes = new ArrayList<>(opcoes);
        this.disponivel = new ArrayList<>();
        this.handlers = new ArrayList<>();
        this.opcoes.forEach(s-> {
            this.disponivel.add(()->true);
            this.handlers.add(()->System.out.println("\nATENÇÃO: Opção não implementada!"));
        });
    }

    /**
     * Constructor para objectos da classe Menu.
     *
     * Cria um menu de opções sem event handlers.
     * Utilização de listas é útil para definir menus estáticos. P.e.:
     * Utilização de listas é útil para definir menus dinâmicos.
     *
     * @param opcoes Uma lista de Strings com as opções do menu.
     */
    public Menu(List<String> opcoes) { this("Menu", opcoes); }

    /**
     * Constructor para objectos da classe Menu.
     *
     * Cria um menu de opções sem event handlers.
     * Utilização de arrays é útil para definir menus estáticos. P.e.:
     *
     * new Menu(String[]{
     *     "Opção 1",
     *     "Opção 2",
     *     "Opção 3"
     * })
     *
     * @param titulo O titulo do menu
     * @param opcoes Um array de Strings com as opções do menu.
     */
    public Menu(String titulo, String[] opcoes) {
        this(titulo, Arrays.asList(opcoes));
    }

    /**
     * Constructor para objectos da classe Menu.
     *
     * Cria um menu de opções sem event handlers.
     * Utilização de arrays é útil para definir menus estáticos. P.e.:
     *
     * new Menu(String[]{
     *     "Opção 1",
     *     "Opção 2",
     *     "Opção 3"
     * })
     *
     * @param opcoes Um array de Strings com as opções do menu.
     */
    public Menu(String[] opcoes) {
        this(Arrays.asList(opcoes));
    }

    // Métodos de instância

    /**
     * Adicionar opções a um Menu.
     *
     * @param name A opção a apresentar.
     * @param p A pré-condição da opção.
     * @param h O event handler para a opção.
     */
    public void option(String name, PreCondition p, Handler h) {
        this.opcoes.add(name);
        this.disponivel.add(p);
        this.handlers.add(h);
    }

    /**
     * Correr o menu uma vez.
     */
    public void runOnce() {
        int op;
        show();
        op = readOption();
        // testar pré-condição
        if (op>0 && !this.disponivel.get(op-1).validate()) {
            System.out.println("Opção indisponível!");
        } else if (op>0) {
            // executar handler
            this.handlers.get(op-1).execute();
        }
    }

    /**
     * Correr o menu multiplas vezes.
     *
     * Termina com a opção 0 (zero).
     */
    public void run() {
        int op;
        do {
            show();
            op = readOption();
            // testar pré-condição
            if (op>0 && !this.disponivel.get(op-1).validate()) {
                System.out.println("Opção indisponível! Tente novamente.");
            } else if (op>0) {
                // executar handler
                this.handlers.get(op-1).execute();
            }
        } while (op != 0);
    }

    /**
     * Método que regista uma uma pré-condição numa opção do menu.
     *
     * @param i índice da opção (começa em 1)
     * @param b pré-condição a registar
     */
    public void setPreCondition(int i, PreCondition b) {
        this.disponivel.set(i-1,b);
    }

    /**
     * Método para registar um handler numa opção do menu.
     *
     * @param i indice da opção  (começa em 1)
     * @param h handlers a registar
     */
    public void setHandler(int i, Handler h) {
        this.handlers.set(i-1, h);
    }

    // Métodos auxiliares

    /** Apresentar o menu */
    private void show() {
        System.out.println("\n *** "+this.titulo+" *** ");
        for (int i=0; i<this.opcoes.size(); i++) {
            System.out.print(i+1);
            System.out.print(" - ");
            System.out.println(this.disponivel.get(i).validate()?this.opcoes.get(i):"---");
        }
        System.out.println("0 - Sair");
    }

    /** Ler uma opção válida */
    private int readOption() {
        int op;

        System.out.print("Opção: ");
        try {
            String line = is.nextLine();
            op = Integer.parseInt(line);
        }
        catch (NumberFormatException e) { // Não foi inscrito um int
            op = -1;
        }
        if (op<0 || op>this.opcoes.size()) {
            System.out.println("Opção Inválida!!!");
            op = -1;
        }
        return op;
    }
}









/** 

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
*/
