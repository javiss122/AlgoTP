//implementacion de ceres el planeta
//con algo de mision
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ceres here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ceres extends ActorBase
{
    /**
     * {@value #COMBUSTIBLE_CONTENIDO}
     */
    private static final int COMBUSTIBLE_CONTENIDO = 100;

    private double ESCALA_X = 0.9;
    private double ESCALA_Y = 0.9;
    protected int vida;
    private int combustible;

    public Ceres() {
        this.vida = 51 + (int) (Math.random() * 50);
        this.combustible = 0; // Inicializa el combustible en 0

    }

    public Ceres(int vida) {
        this.vida = vida;
        this.combustible = 0; // Inicializa el combustible en 0
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

    protected int obtenerTamañoMaximo() {
        return 100;
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

    
    
    //abstract int obtenerCombustibleMaximo();

    //abstract int obtenerConsumoPorMovimiento();
}
