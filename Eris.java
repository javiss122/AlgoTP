//implementacion del planeta Eris, se mata con 3 ataques

//Falta implementar la recompensa

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Eris here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Eris extends ActorBase implements Dañable
{
    /**
     * {@value #COMBUSTIBLE_CONTENIDO}
     */
    private static final int COMBUSTIBLE_CONTENIDO = 100;
    protected static final int TAMAÑO_MINIMO_DE_ERIS = 10;
    protected static final int TAMAÑO_MAXIMO_DE_ERIS = 100;
    private double ESCALA_X = 0.9;
    private double ESCALA_Y = 0.9;
    protected int vida;
    protected int tamaño;
    private int combustible;
    private int MAX_COMBUSTIBLE = 100;

    public Eris() {
        this.vida = 100 + (int) (Math.random() * 50);
        this.combustible = 0;
    }

    /**
     * Inicializa un Asteroiode con tamaño arbitrario, en el rango de 10 a
     * {@value #TAMAÑO_MAXIMO_DE_ASTEROIDE} puntos
     * 
     * @param tamañoInicial
     */public  Eris(int tamañoInicial) {
        this.tamaño = Math.max(TAMAÑO_MINIMO_DE_ERIS , Math.min(TAMAÑO_MAXIMO_DE_ERIS , tamañoInicial));
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

        GreenfootImage Eris = getImage();
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
        return TAMAÑO_MAXIMO_DE_ERIS ;
    }

    /**
     * {@link #tamaño}
     * 
     * @return el tamaño del Asteroide
     */
    public int obtenerTamaño() {
        return this.tamaño;
    }

    // Método que se llama cuando el planeta es destruido
    public void serDestruidoPor(Atacante atacante) {
        // Devuelve 100% de combustible a la nave
        recibirCombustible(100);

        // Elimina el planeta del mundo
        getWorld().removeObject(this);
    }

    public void recibirCombustible(int cantidad) {
        this.combustible += cantidad;
        // Asegúrate de no exceder un límite máximo si es necesario
        this.combustible = Math.min(this.combustible, MAX_COMBUSTIBLE);
    }
//para devoolver combustible aun no funciona
    int recolectar(int cantidad) {
        cantidad = Math.min(cantidad, this.vida);
        cantidad = Math.min(cantidad, 100);
        this.vida -= cantidad;

        actualizarImagen();
        explotar();
        if (this.vida <= 0) {
            getWorld().removeObject(this);
        }

        return cantidad;
    }

}
