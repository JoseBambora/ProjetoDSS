package PackageIO;

import PackageCarro.CarroDAO;
import PackageCarro.ModoMotor;
import PackageCarro.Pneu;
import PackageFacade.ISimulador;
import PackageFacade.Simulador;
import PackageUtilizador.Utilizador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Interface em modo texto.
 *
 * 
 * @version 2023
 */
public class TextUI {

    private static ISimulador model = new Simulador();

    // Menus da aplicação
    private Menu menu;

    // Scanner para leitura
    private static Scanner scin;

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
                "Login"
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
                PackageFacade.Simulador.adicionaUtilizador(user);

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
            Utilizador utilizador = new Utilizador();
            System.out.println("Nome de Utilizador: ");
            utilizador.set_username(scin.nextLine());

            if(!model.validarRegistoUser(utilizador.get_username()))
            {
                System.out.println(model.validarRegistoUser(utilizador.get_username()));
                System.out.println("Username não existe na base de dados!");
            }
            else {
                System.out.println("Palavra-Passe: ");
                utilizador.set_password(scin.nextLine());
                if(!model.validarDadosUser(utilizador, utilizador.get_password()))
                {
                    System.out.println("Password incorreta!");
                }
                else {
                    System.out.println(" Login feito com sucesso! ");
                    menuSimulador();
                }
            }

        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void printClassifacao(List<String> classificacoes)
    {
        for(int i = 0; i < classificacoes.size();)
        {
            String name = classificacoes.get(i);
            i++;
            System.out.println(i + "º: " + name);
        }
    }

    public static void printDesclassificados(List<String> desclassificados)
    {
        System.out.println("Desclassificados:");
        for(int i = 0; i < desclassificados.size();)
        {
            String name = desclassificados.get(i);
            System.out.println(name);
        }
    }


    private void menuSimulador()
    {
        try
        {
            String camp, nickname, driver, car;
            System.out.println("Indique o número de jogadores: ");
            int num = scin.nextInt();

            List<String> aJogadores = new ArrayList<>(num);
            Map<String, String> aEscolhaPilotos = new HashMap<>(num);
            Map<String, String> aEscolhaCarros = new HashMap<>(num);

            System.out.println("Selecione um campeonato: ");
            System.out.println(model.getCampeonatos());
            camp = scin.nextLine();

            if(!model.existeCampeonato(camp))
            {
                System.out.println(" Campeonato inválido! ");
            }
            else{

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
                        if(!model.verificaExistenciaPiloto(driver))
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
                        if(!model.verificaExistenciaCarro(car))
                        {
                            System.out.println(" Carro inválido! ");
                        }
                        else{
                            aEscolhaCarros.put(nickname, car);
                            flag = true;
                        }
                    }


                }


                int id = model.configuraCampeonato(camp, aJogadores, aEscolhaPilotos, aEscolhaCarros);
                //adicionaJogador(jogador, driver, car)

                Map<String,Integer> classificacoes = model.simulaCampeonato(id);List<String> classiSort = new ArrayList<>(classificacoes.keySet());
                classiSort.sort((s1,s2) -> classificacoes.get(s2) - classificacoes.get(s1));
                System.out.println("Classificação");
                System.out.println("     Nome      | Pontuação");
                for(int i = 0; i < classiSort.size(); )
                {
                    String name = i + "º" + classiSort.get(i);
                    if(name.length() < 15)
                    {
                        String esp = " ".repeat(15 - name.length());
                        name += esp;
                    }
                    else
                        name = name.substring(0,15);
                    i++;
                    System.out.println(name + " | " + classificacoes.get(name));
                }
                for(String nome : classificacoes.keySet())
                {
                    Utilizador utilizador = new Utilizador();
                    utilizador.set_username((nome));
                    if(model.isJogador(nome))
                    {
                        System.out.println("Jogador: " + nome + ", qual a password?");
                        String password = scin.nextLine();
                        if(!model.validarDadosUser(utilizador,password))
                            model.atualizaPontuacaoGlobal(id,nome);
                    }
                }

            }


        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
    public static float getPac()
    {
        System.out.println("Qual o pac? (0.0 - 1.0)");
        return scin.nextFloat();
    }
    public static Pneu getPneus()
    {
        List<Pneu> pneus = model.getPneus();
        pneus.forEach(System.out::println);
        int id = scin.nextInt();
        return model.getPneu(id);
    }
    public static ModoMotor getModo()
    {
        List<ModoMotor> modo = model.getModos();
        modo.forEach(System.out::println);
        int id = scin.nextInt();
        return model.getModo(id);
    }
    public static boolean pretendeAfinar()
    {
        System.out.println("Pretende afinar?");
        System.out.println("1 - Sim");
        System.out.println("0 - Não");
        return scin.nextInt() == 1;
    }
}
