import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mision1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mision1 extends Ceres {

    private int combustible;
    private static final int COMBUSTIBLE_CONTENIDO = 100;
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

    public int obtenerCombustible() {
        return combustible;
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
    private int mision;
    public void verificadorMision(){
        int palentasDestruidos;
        int vecesTeletransportado;

    }
   
