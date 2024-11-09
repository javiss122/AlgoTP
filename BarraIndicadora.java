// en nave aliada implementamos el siguiente metodo
//falta  pullir y lograr que sean 2 
//copio nave de ataque con los nuevos cambios
//no lo modifico en la nave que tenemos porque aun falta que en el juego aparezcan las dos barras
//y para no cagarla 

import greenfoot.Color;
import greenfoot.GreenfootImage;

/**
 * Define características y comportamientos comunes a todas las Naves Aliadas de
 * la Batalla Espacial. Entre ellas, su uso de combustible.
 */
public abstract class NaveAliada extends NaveBase {
    /**
     * El combustible de la NaveAliada. Toda acción insume combustible
     */
    protected int combustible;
   //para la nueva barra  
  protected int combustible2;


    /**
     * Inicializa una NaveAliada con el {@link #obtenerCombustibleMaximo()}
     */
    public NaveAliada() {
        super();
        this.combustible = obtenerCombustibleMaximo();
        this.combustible2 = obtenerCombustibleMaximo(); //para la nueva barra
    }

    /**
     * Punto de extensión para obtener el combustible máximo para un tipo de
     * NaveAliada
     * 
     * @return la cantidad de combustible máximo que carga la NaveAliada
     */
    abstract int obtenerCombustibleMaximo();

    /**
     * Punto de extensión para obtener el combustible consumido por moverse
     * 
     * @return la cantidad de combustible que consume realizar un movimiento
     */
    abstract int obtenerConsumoPorMovimiento();

    /**
     * Permite a una NaveAliada recibir combustible. <br>
     * post: La NaveAliada recibe la cantidad de combustible deseado, limitado a la
     * carga máxima de la que puede disponer
     * 
     * @param cantidad es la cantidad de combustible que intenta recibir
     */
    public void recibirCombustible(int cantidad) {
        this.combustible = Math.min(this.combustible + cantidad, obtenerCombustibleMaximo());
        actualizarImagen();
        this.combustible2 = Math.min(this.combustible + cantidad, obtenerCombustibleMaximo());
        actualizarImagen(); //para la nueva barra
    }
    

    /**
     * Recibe un daño de un {@link Atacante} <br>
     * post: La NaveAliada obtendrá el impacto que el atacante le genere, y se
     * reflejará en forma de {@link #combustible} perdido.
     * @see Dañable#recibirDañoDe(Atacante)
     * 
     * @param atacante es quien realizó el ataque
     */
    public void recibirDañoDe(Atacante atacante) {
        int daño = atacante.obtenerDaño();
        this.combustible -= daño;
        this.combustible2 -= daño; //para la nueva barra
        actualizarImagen();
        Explosion.en(getWorld(), this.getX(), this.getY());
    }

    /**
     * post: la cantidad de {@link #combustible} se reduce en la cantidad
     * solicitada, o llega a cero si no alcanzase
     * 
     * @param cantidad es la cantidad de combustible que se consumirá
     */
    protected void consumirCombustible(int cantidad) {
        this.combustible -= Math.min(cantidad, this.combustible);
        actualizarImagen();
         this.combustible2 -= Math.min(cantidad, this.combustible);
        actualizarImagen(); //para la nueva barra
    }

    /**
     * post: carga la cantidad de {@link #combustible} solicitado, o llega a
     * {@link #obtenerCombustibleMaximo()} si éste se excediera del que puede llevar
     * 
     * @param combustible es la cantidad de combustible a cargar
     */
    public void cargarCombustible(int combustible) {
        this.combustible = Math.min(obtenerCombustibleMaximo(), this.combustible + combustible);
        actualizarImagen();
         this.combustible2 = Math.min(obtenerCombustibleMaximo(), this.combustible + combustible);
        actualizarImagen(); //para la nueva barra
    }

    /**
     * {@link #combustible}
     * 
     * @return la cantidad de combustible actual
     */
    public int obtenerCombustible() {
        return this.combustible;
        
    }
 /**
     * {@link #combustible}
     * 
     * @return la cantidad de combustible actual
     */
  //para la nueva barra  
  public int obtenerCombustible2() {
        return this.combustible2;
        
    }

    
    public double obtenerProporcionDeCombustible() {
        return 1.0 * combustible / obtenerCombustibleMaximo();
    }
//para la nueva barra
  public double obtenerProporcionDeCombustible2() {
        return 1.0 * combustible2 / obtenerCombustibleMaximo();
    }

    /**
     * @return el color de la barra indicadora de la {@link NaveAliada}
     */

    protected Color obtenerColorDeBarraIndicadora() {
        return Color.YELLOW;
   }
    /**
     * @return el color de la barra indicadora de la {@link NaveAliada}
     */
//para la nueva barra
    protected Color obtenerColorDeBarraIndicadora2() {
        return Color.RED;
   }
   
   
/**
     * Calcula la proporción de la barra indicadora en función al
     * {@link #combustible} y a {@link #obtenerCombustibleMaximo()}
     * 
     * @return la proporción de la barra indicadora a mostrar
     */
    protected double obtenerProporcionDeBarraIndicadora() {
        return 1.0  * combustible / obtenerCombustibleMaximo();
    }
/**
     * Calcula la proporción de la barra indicadora en función al
     * {@link #combustible} y a {@link #obtenerCombustibleMaximo()}
     * 
     * @return la proporción de la barra indicadora a mostrar
     */
  //para la nueva barra  
  protected double obtenerProporcionDeBarraIndicadora2() {
        return 1.0  * combustible2 / obtenerCombustibleMaximo();
    }

   //
   
public void act() {
    // Lógica de movimiento y otras acciones...

    // Dibuja la barra de combustible
    dibujarBarraCombustible();
}
//para la nueva barra
private void dibujarBarraCombustible() {
    double proporcion = obtenerProporcionDeBarraIndicadora2();
    GreenfootImage imagen = new GreenfootImage(100, 10); // Ancho y alto de la barra

    // Dibuja el fondo de la barra
    imagen.setColor(Color.RED);
    imagen.fillRect(0, 0, 100, 10);

    // Dibuja la parte de combustible
    imagen.setColor(obtenerColorDeBarraIndicadora2());
    int anchoCombustible2 = (int) (proporcion * 100);
    imagen.fillRect(0, 0, anchoCombustible2, 10);

    setImage(imagen); // Establece la imagen de la nave con la barra de combustible
}    
    //COLOR
    protected void updateImage() {
        GreenfootImage canvas = new GreenfootImage(baseImage.getWidth(),
                baseImage.getHeight() + getWorld().getCellSize() / 3);

        canvas.setColor(Color.BLACK);
        canvas.fillRect(2, baseImage.getHeight() - 2, getWorld().getCellSize() - 4, getWorld().getCellSize() / 3 - 4);
        canvas.setColor(Color.RED);

        canvas.fillRect(4, baseImage.getHeight(),
                (int) ((getWorld().getCellSize() - 6) * this.obtenerProporcionDeCombustible()) - 2, 8);

        canvas.rotate(360 - direccion.rotacion);

        canvas.drawImage(baseImage, 0, getWorld().getCellSize() / 6);
        setImage(canvas);
    }
    
    
