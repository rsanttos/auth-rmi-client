package rmi.auth.enums;

public enum PapelEnum {
	
	ADMINISTRADOR(new String[] {"CADASTRAR_USUARIO", "LISTAR_USUARIO", "EDITAR_USUARIO", "DELETAR_USUARIO"}), 
	USUARIO_COMUM (new String[] {"LISTAR_USUARIO"});

	String[] recursos;

	private PapelEnum(String[] recursos) {
		this.recursos = recursos;
	}

	public String[] getRecursos() {
		return recursos;
	}

	public void setRecursos(String[] recursos) {
		this.recursos = recursos;
	}

}
