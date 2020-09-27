package rmi.auth.dto;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface para métodos que dizem respeito à autenticação de usuários
 * @author ramon
 *
 */
public interface AutenticacaoService extends Remote {
	
	public Boolean login(String nome, String senha) throws RemoteException;
	
}
