import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Exemplo de interface em modo texto.
 *
 * @author JFC
 * @version 20220919
 */
public class TextUI {

    private ISimulador model = new Simulador();

    // Menus da aplicação
    private Menu menu;

    // Scanner para leitura
    private Scanner scin;

    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    public void run() {
        this.menu.run();
        System.out.println("Até breve!...");
    }


    /**
     * Construtor.
     *
     * Cria os menus e a camada de negócio.
     */
    public TextUI() {
        // Criar o menu
        this.menu = new Menu(new String[]{
                "Criar Utilizador",
                "Login",
                "Sair"
        });


        
        //this.menu.setHandler(1, this::trataCriarUtilizador);
        this.menu.setHandler(2, this::trataLoginUtilizador);
        

        /* pre-condição */
        // menu.setPreCondition(4,() -> this.model.autenticado();

        /* Facade */
        // this.model = new Facade();

        scin = new Scanner(System.in);
    }

    /**
     * Criar Utilizador - NAO FUNCIONA E TEM ERROS
    
    private void trataCriarUtilizador() {
        try {

            List<Utilizador> users = new ArrayList<>();
            UtilizadoresDAO user;
            System.out.println("Nome de Utilizador: ");
            String username = scin.nextLine();
            
            // checks if for each user 
            if (!users.containsKey(username)) {
                System.out.println("Palavra-Passe: ");
                String password = scin.nextLine();

                user.put(new Utilizador(username,password));
                Simulador.adicionaUtilizador(user);

                System.out.println("Utilizador adicionado");
                
            } else {
                System.out.println("Esse username já existe!");
            }
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
     */

    /**
     * Login
     */
    private void trataLoginUtilizador() {
        try {
            
            System.out.println("Nome de Utilizador: ");
            String username = scin.nextLine();
            if (!model.validarRegistoUser(username)) {
                System.out.println("Palavra-Passe: ");
                String password = scin.nextLine();
                model.validarDadosUser(username,password);
                // check if the password is correct with the username given
                
                
            } else {
                
                System.out.println("Esse username não existe!");
            }
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }


    private void menuSimulador()
    {
        try {
            Simulador sim = new Simulador();
            String camp, nickname, driver, car;
            System.out.println("Indique o número de jogadores: ");
            int num = scin.nextInt();

            List<String> aJogadores = new ArrayList<>(num);
            Map<String, String> aEscolhaPilotos = new HashMap<>(num);
            Map<String, String> aEscolhaCarros = new HashMap<>(num);

            System.out.println("Selecione um campeonato: ");
            System.out.println(model.getCampeonatos());
            camp = scin.nextLine();
            if(model.existeCampeonato(camp))
            {
                System.out.println(" Campeonato inválido! ");
            }

            for(int i = 0; i<num; i++)
            {
                System.out.println(" Nickname: ");
                nickname = scin.nextLine();
                aJogadores.add(nickname);

                boolean flag = false;
                while(flag == false)
                {
                    System.out.println("Selecione um piloto: ");
                    System.out.println(model.getPilotos());
                    driver = scin.nextLine();
                    if(model.verificaExistenciaPiloto(driver))
                    {
                        System.out.println(" Piloto inválido! ");
                    }
                    else{
                        aEscolhaPilotos.put(nickname, driver);
                        flag = true;
                    }
                }
                
                flag = false;
                while(flag == false)
                {
                    System.out.println("Selecione um carro: ");
                    System.out.println(model.getCarros());
                    car = scin.nextLine();
                    if(model.verificaExistenciaCarro(car))
                    {
                        System.out.println(" Carro inválido! ");
                    }
                    else{
                        aEscolhaCarros.put(nickname, car);
                        flag = true;
                    }
                }


            }
            

            int id = sim.configuraCampeonato(camp, aJogadores, aEscolhaPilotos, aEscolhaCarros);
            //adicionaJogador(jogador, driver, car)

            sim.simulaCampeonato(id);
            
            
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
