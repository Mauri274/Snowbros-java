package Fabricas;

import java.util.Map;

public class Sprites {
	
	protected Map<Integer,SpriteInfo> mapeoImagenEstado;
	protected int estadoActual;


	public Sprites (Map<Integer,SpriteInfo> mapeoEstadoImagen, int estadoActual) {
		this.mapeoImagenEstado = mapeoEstadoImagen;
		this.estadoActual = estadoActual;
	}
    
    public SpriteInfo getSpriteInfo() {
		return mapeoImagenEstado.get(estadoActual);
	}
	
	public void setEstadoActual(int estadoActual) {
		this.estadoActual = estadoActual;
	}

	public int getEstadoActual() {
		return estadoActual;
	}
}