package rmi.auth.dto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import rmi.auth.dominio.Usuario;
import rmi.auth.enums.PapelEnum;

public interface UsuarioService extends Remote {
	Boolean salvar(String login, String senha, PapelEnum papel) throws RemoteException;
	List<Usuario> listar() throws RemoteException;
	
}
