//implementacion del planeta Makemake, se mata con 5 ataques

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Makemake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Makemake extends ActorBase implements Dañable
{
    /**
     * {@value #COMBUSTIBLE_CONTENIDO}
     */
    private static final int COMBUSTIBLE_CONTENIDO = 100;
    private double ESCALA_X = 0.9;
    private double ESCALA_Y = 0.9;
    protected static final int TAMAÑO_MINIMO_DE_Makemake = 10;
    protected static final int TAMAÑO_MAXIMO_DE_Makemake= 100;
    protected int vida;
    protected int tamaño;
    public Makemake() {
        this.vida = 150 + (int) (Math.random() * 50);
    }

    /**
     * Inicializa un Asteroiode con tamaño arbitrario, en el rango de 10 a
     * {@value #TAMAÑO_MAXIMO_DE_ASTEROIDE} puntos
     * 
     * @param tamañoInicial
     */public Makemake(int tamañoInicial) {
        this.tamaño = Math.max(TAMAÑO_MINIMO_DE_Makemake , Math.min(TAMAÑO_MAXIMO_DE_Makemake , tamañoInicial));
    }

    /**
     * Establece la imagen con la escala definida
     */
    @Override
    protected void actualizarImagen() {
        int tamCelda = getWorld().getCellSize();
        int ancho = Math.max(30, (tamCelda * vida) / obtenerTamañoMaximo());
        GreenfootImage image = getImage();
        if (this.vida <= 0)
            image.setTransparency(0);
        image.scale(ancho, ancho);
        setImage(image);

        GreenfootImage Makemake = getImage();
        image.scale((int) (tamCelda * ESCALA_X), (int) (tamCelda * ESCALA_Y));
        setImage(image);
    }

    /**
     * post: el Item desaparece del mundo
     * 
     * @return la cantidad de combustible que el Item proporciona
     */
    public int serRecogido() {
        getWorld().removeObject(this);
        Greenfoot.delay(20);
        return COMBUSTIBLE_CONTENIDO;
    }

    public void recibirDañoDe(Atacante atacante) {
        int daño = atacante.obtenerDaño();
        this.vida -= daño;
        actualizarImagen();
        explotar();
        if (this.vida <= 0) {
            getWorld().removeObject(this);
        }
    }

    protected void explotar() {
        Explosion x = new Explosion();
        getWorld().addObject(x, this.getX(), getY());
        x.animar();
        getWorld().removeObject(x);
    }

    //posiciones
    public int getPosicionXBarrera(){
        return this.getX();
    }

    public int getPosicionYBarrera(){
        return this.getY();
    }

    @Override
    protected void addedToWorld(World world) {
        actualizarImagen();
    }

    /**
     * {@value #TAMAÑO_MAXIMO_DE_ASTEROIDE}
     * 
     * @return el tamaño máximo que puede tener un Asteroide
     */
    protected int obtenerTamañoMaximo() {
        return TAMAÑO_MAXIMO_DE_Makemake ;
    }

    
    /**

    /**
     * {@link #tamaño}
     * 
     * @return el tamaño del Asteroide
     */
    public int obtenerTamaño() {
        return this.tamaño;
    }
}
