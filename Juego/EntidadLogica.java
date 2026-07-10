package Juego;

import Fabricas.Sprites;

public interface EntidadLogica {

	public Sprites getSprites();
	public int getX();
	public int getY();
	public int getDireccion();
	public abstract int getAnchoSprite();
    public abstract int getAltoSprite();
	public boolean esVisible();
	
}