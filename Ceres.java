//implementacion de ceres el planeta, se mata con 7 ataques


import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ceres here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ceres extends ActorBase implements Dañable
{
    /**
     * {@value #COMBUSTIBLE_CONTENIDO}
     */
    private static final int COMBUSTIBLE_CONTENIDO = 100;
    protected static final int TAMAÑO_MINIMO_DE_Ceres = 10;
    protected static final int TAMAÑO_MAXIMO_DE_Ceres= 100;
    private double ESCALA_X = 0.9;
    private double ESCALA_Y = 0.9;
    protected int vida;
    private int combustible;
    protected int tamaño;
    public Ceres() {
        this.vida = 200 + (int) (Math.random() * 50);
        this.combustible = 0; // Inicializa el combustible en 0

    }

    /**
     * Inicializa un Asteroiode con tamaño arbitrario, en el rango de 10 a
     * {@value #TAMAÑO_MAXIMO_DE_ASTEROIDE} puntos
     * 
     * @param tamañoInicial
     */public Ceres(int tamañoInicial) {
        this.tamaño = Math.max(TAMAÑO_MINIMO_DE_Ceres , Math.min(TAMAÑO_MAXIMO_DE_Ceres , tamañoInicial));
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

        GreenfootImage Ceres = getImage();
        image.scale((int) (tamCelda * ESCALA_X), (int) (tamCelda * ESCALA_Y));
        setImage(image);
    }

    public void atacarAsteroides() {

        World world = getWorld();
        Asteroide[] asteroides = new Asteroide[2]; // Suponemos que hay un máximo de 2 asteroides para atacar
        int asteroidesAtacados = 0;
        // Ataca hasta dos asteroides disponibles
        for (Asteroide asteroide : world.getObjects(Asteroide.class)) {
            if (asteroidesAtacados < 2) {
                asteroides[asteroidesAtacados] = asteroide;
                asteroidesAtacados++;
            } else {
                break; // Solo  hasta 2 asteroides
            }
        }
        // Si se atacaron asteroides, Ceres recibe combustible
        if (asteroidesAtacados  > 0) {
            combustible += COMBUSTIBLE_CONTENIDO; // Aumenta el combustible
        }
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

    
    private int mision;
    public void verificadorMision(){
        int palentasDestruidos;
        int vecesTeletransportado;

    }

    public int obtenerCombustible() {
        return this.combustible;

    }

    protected double obtenerProporcionDeBarraIndicadora() {
        return 1.0  * combustible;

    }

    /**
     * @return el color de la barra indicadora de la {@link NaveAliada}
     */

    protected Color obtenerColorDeBarraIndicadora() {
        return Color.BLUE;
    }

    public void recibirCombustible(int cantidad) {
        this.combustible = (this.combustible + cantidad);
        actualizarImagen();

    }

    protected void consumirCombustible(int cantidad) {
        this.combustible -= Math.min(cantidad, this.combustible);
        actualizarImagen();

    }

    public void cargarCombustible(int combustible) {
        this.combustible = (this.combustible + combustible);
        actualizarImagen();

    }

    public void derecha() {
        setLocation(getX() + 1, getY());
    }

    public void izquierda() {
        setLocation(getX() - 1, getY());
    }

    public void arriba() {
        setLocation(getX(), getY() - 1);
    }

    public void abajo() {
        setLocation(getX(), getY() + 1);
    }

    /**
     * {@value #TAMAÑO_MAXIMO_DE_ASTEROIDE}
     * 
     * @return el tamaño máximo que puede tener un Asteroide
     */
    protected int obtenerTamañoMaximo() {
        return TAMAÑO_MAXIMO_DE_Ceres ;
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

