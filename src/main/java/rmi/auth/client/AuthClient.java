package rmi.auth.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import rmi.auth.dominio.Usuario;
import rmi.auth.dto.AutenticacaoService;
import rmi.auth.dto.AutorizacaoService;
import rmi.auth.dto.UsuarioService;
import rmi.auth.enums.PapelEnum;

public class AuthClient {

	public static String SERVER_PATH = "rmi://localhost:";
	public static String SERVER_PORT = "1900";

	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String args[]) {
		try {

			System.out.println("====== CLIENTE DE AUTENTICAÇÃO INICIALIZADO ======");
	        menu();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void menu() throws MalformedURLException, RemoteException, NotBoundException {

		UsuarioService usuarioService = (UsuarioService) Naming.lookup(SERVER_PATH + SERVER_PORT + "/usuario");
		AutenticacaoService autenticacaoService = (AutenticacaoService) Naming.lookup(SERVER_PATH + SERVER_PORT + "/autenticacao");
		AutorizacaoService autorizacaoService = (AutorizacaoService) Naming.lookup(SERVER_PATH + SERVER_PORT + "/autorizacao");
		System.out.println();
		System.out.println("====== ESCOLHA UMA DAS OPÇÕES DO MENU (ATRAVÉS DO NÚMERO) ======");
		System.out.println("====== 1 -> Realizar login");
		System.out.println("====== 2 -> Cadastrar usuário");
		System.out.println("====== 3 -> Listar usuários");
		System.out.println("====== 4 -> Verificar se usuário possui autorização para acessar recurso");
		System.out.println("====== 5 -> Sair");
        String opcaoEscolhida = scanner.nextLine();
        operacionalizaMenu(opcaoEscolhida, autenticacaoService, autorizacaoService, usuarioService);
        System.out.println();
	}
	
	private static void realizarLogin(AutenticacaoService autenticacaoService) throws RemoteException {
		System.out.println("====== REALIZAR LOGIN ======");
		System.out.println("====== Informe o nome: ");
		String nome = scanner.nextLine();
		System.out.println("====== Informe a senha: ");
		String senha = scanner.nextLine();
		Boolean autenticado = autenticacaoService.login(nome, senha);

		if(autenticado) {
			System.out.println("----- USUÁRIO AUTENTICADO COM SUCESSO -----");
		} else {
			System.out.println("----- ACESSO NÃO AUTORIZADO. VERIFIQUE OS DADOS INFORMADOS. -----");			
		}
	}
	
	private static void listarUsuarios(UsuarioService usuarioService) throws RemoteException {
		System.out.println("====== LISTAR USUÁRIOS ======");
		List<Usuario> usuarios = usuarioService.listar();
		if(!Objects.isNull(usuarios)) {
			usuarios.forEach(usuario -> {
				System.out.println("----- Nome: " + usuario.getNome());
				System.out.println("----- Senha: " + usuario.getSenha());
				System.out.println("----- Papel: " + usuario.getPapel());
				System.out.println("---------------------------------------------------------");
			});
		} else {
			System.out.println("----- NENHUM USUÁRIO CADASTRADO. -----");				
		}
	}

	private static void cadastrarUsuario(UsuarioService usuarioService) throws RemoteException {
		System.out.println("====== CADASTRAR NOVO USUÁRIO ======");
		System.out.println("====== Informe o nome: ");
		String nome = scanner.nextLine();
		System.out.println("====== Informe a senha: ");
		String senha = scanner.nextLine();
		System.out.println("====== Informe o papel do usuário (exatamente como mostrado nas opções): ");
		System.out.println("====== ADMINISTRADOR ");
		System.out.println("====== USUARIO_COMUM ");
		String papel = scanner.nextLine();
		Boolean sucesso = usuarioService.salvar(nome, senha, PapelEnum.valueOf(papel));
		if(sucesso) {
			System.out.println("----- USUÁRIO CADASTRADO COM SUCESSO! -----");			
		} else {
			System.out.println("----- OCORREU UM ERRO INESPERADO. TENTE NOVAMENTE. -----");
		}
	}
	
	private static void acessarRecurso(AutorizacaoService autorizacaoService) throws RemoteException {
		System.out.println("====== VERIFICAR ACESSO ======");
		System.out.println("====== Informe o nome do usuario: ");
		String nome = scanner.nextLine();
		System.out.println("====== Informe o recurso que deseja acessar (exatamente como mostrado nas opções): ");
		System.out.println("====== CADASTRAR_USUARIO ");
		System.out.println("====== LISTAR_USUARIO ");
		System.out.println("====== EDITAR_USUARIO ");
		System.out.println("====== DELETAR_USUARIO ");
		String recurso = scanner.nextLine();
		Boolean autorizado = autorizacaoService.acessaRecurso(nome, recurso);
		if(autorizado) {
			System.out.println("----- USUÁRIO AUTORIZADO! -----");			
		} else {
			System.out.println("----- USUÁRIO NÃO AUTORIZADO! -----");
		}
	}
	
	private static void operacionalizaMenu(String opcaoEscolhida, AutenticacaoService autenticacaoService, AutorizacaoService autorizacaoService, UsuarioService usuarioService) throws RemoteException, MalformedURLException, NotBoundException {
		switch(opcaoEscolhida) {
			case "1":
				realizarLogin(autenticacaoService);
				break;
			case "2":
				cadastrarUsuario(usuarioService);
				break;
			case "3":
				listarUsuarios(usuarioService);
				break;
			case "4":
				acessarRecurso(autorizacaoService);
				break;
			case "5":
				System.out.println("----- ATÉ A PRÓXIMA. TCHAU! -----");
				System.exit(0);
			default:
				System.out.println("----- OPÇÃO INVÁLIDA. ESCOLHA NOVAMENTE -----");
		}
		menu();
	}
}
