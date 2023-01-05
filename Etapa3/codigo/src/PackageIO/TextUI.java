package PackageIO;

import PackageCarro.Carro;
import PackageCarro.CarroDAO;
import PackageCarro.ModoMotor;
import PackageCarro.Pneu;
import PackageFacade.ISimulador;
import PackageFacade.Simulador;
import PackagePiloto.Piloto;
import PackageUtilizador.Utilizador;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        scin.useDelimiter("\n");
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
            utilizador.set_username(scin.next());

            if(!model.validarRegistoUser(utilizador.get_username()))
            {
                System.out.println(model.validarRegistoUser(utilizador.get_username()));
                System.out.println("Username não existe na base de dados!");
            }
            else {
                System.out.println("Palavra-Passe: ");
                utilizador.set_password(scin.next());
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
        System.out.println("Classificação atual");
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
        desclassificados.forEach(System.out::println);
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
            List<Piloto> pilotos = model.getPilotos();
            List<Carro> carros = model.getCarros();
            System.out.println("Selecione um campeonato: ");
            System.out.println(model.getCampeonatos());
            scin.nextLine();
            camp = scin.next();
            System.out.println(camp);
            if(!model.existeCampeonato(camp))
            {
                System.out.println(" Campeonato inválido! ");
            }
            else{

                for(int i = 0; i<num; i++)
                {
                    System.out.println(" Nickname: ");
                    scin.nextLine();
                    nickname = scin.next();
                    aJogadores.add(nickname);
                    boolean flag = false;
                    while(!flag)
                    {
                        System.out.println("Selecione um piloto: ");
                        System.out.println(pilotos);
                        scin.nextLine();
                        driver = scin.next();
                        if(!model.verificaExistenciaPiloto(driver))
                        {
                            System.out.println(" Piloto inválido! ");
                        }
                        else{
                            System.out.println("Piloto " + driver + " adicionado");
                            aEscolhaPilotos.put(nickname, driver);
                            flag = true;
                        }
                    }

                    flag = false;
                    while(!flag)
                    {
                        System.out.println("Selecione um carro: ");
                        System.out.println(carros);
                        scin.nextLine();
                        car = scin.next();
                        String car2 = car.replaceAll(" ",",");
                        if(!model.verificaExistenciaCarro(car2))
                        {
                            System.out.println(" Carro inválido! ");
                        }
                        else{
                            System.out.println("Carro " + car + " adicionado");
                            aEscolhaCarros.put(nickname, car2);
                            flag = true;
                        }
                    }
                }

                System.out.println("A configurar campeonato");
                int id = model.configuraCampeonato(camp, aJogadores, aEscolhaPilotos, aEscolhaCarros);
                //adicionaJogador(jogador, driver, car)
                System.out.println("Id do campeonato " + id);
                System.out.println("Campeonato Configurado, a começar simulação!!");

                Map<String,Integer> classificacoes = model.simulaCampeonato(id);
                List<String> classiSort = new ArrayList<>(classificacoes.keySet());
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
                        String password = scin.next();
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
    public static boolean pretendeAfinar(String jogador)
    {
        System.out.println("O jogador" + jogador+ " pretende afinar?");
        System.out.println("1 - Sim");
        System.out.println("0 - Não");
        return scin.nextInt() == 1;
    }
}
