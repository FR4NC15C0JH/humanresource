package com.humanresource.domain.enumerator;

public enum Profiles {
	
	ROLE_ADMIN("ROLE_ADMIN"),
	ROLE_CUSTOMER("ROLE_CUSTOMER");
	
	private String descricao;

	private Profiles(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Profiles getByDescricao(String descricao) {
		for (Profiles item : values())
			if(item.getDescricao().equalsIgnoreCase(descricao.trim()))
				return item;
		
		return null;
	}
	
	
}
