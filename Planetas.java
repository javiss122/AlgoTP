import greenfoot.*;

/**
 * Un Planetas es un obstáculo que puede ser destruido por una
 * {@link NaveDeAtaque}. Esto llevará una cierta cantidad de ataques
 */
public class Planetas extends ActorBase implements Dañable {
       
    protected int tamaño;

    public Planetas() {
        this(51 + (int) (Math.random() * 50));
    }

    /**
     * Inicializa un Asteroiode con tamaño arbitrario, en el rango de 10 a
     * {@value #TAMAÑO_MAXIMO_DE_ASTEROIDE} puntos
     * 
     * @param tamañoInicial
     */
    public Planetas(int tamaño) {
    this.tamaño = tamaño;
   
}

    /**
     * post: el Planetas reducirá su tamaño conforme la potencia del ataque. <br>
     * post: el Planetas será eliminado del tablero si su tamaño llega a 0. <br>
     * 
     * @see Dañable#recibirDañoDe(Atacante)
     */
    public void recibirDañoDe(Atacante atacante) {
        int daño = atacante.obtenerDaño();
        this.tamaño -= daño;
        actualizarImagen();
        Explosion.en(getWorld(), this.getX(), this.getY());
        if (this.tamaño <= 0) {
            getWorld().removeObject(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actualizarImagen() {
    GreenfootImage image = getImage();
    
    int anchoOriginal = image.getWidth();
    int altoOriginal = image.getHeight();
    
    int nuevoAncho = (anchoOriginal * tamaño) / 180;  
    int nuevoAlto = (altoOriginal * tamaño) / 180;    

    nuevoAncho = Math.max(nuevoAncho, 10);  
    nuevoAlto = Math.max(nuevoAlto, 10);
    
    image.scale(nuevoAncho, nuevoAlto);
    
    setImage(image);
    setRotation((int) (Math.random() * 360));
}

   

    /**
     * {@link #tamaño}
     * 
     * @return el tamaño del Planetas
     */
    public int obtenerTamaño() {
        return this.tamaño;
    }
}
