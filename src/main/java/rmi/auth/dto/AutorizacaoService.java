package rmi.auth.dto;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface que possui métodos para controle de autorizações que o usuário possui
 * @author ramon
 *
 */
public interface AutorizacaoService extends Remote {
	
	public Boolean acessaRecurso(String nome, String recurso) throws RemoteException;
	
}
