import greenfoot.*;

public class NaveDeAtaque extends NaveAliada implements Atacante {
    
    /**
     * Representa el estado de los motores de la {@link NaveDeAtaque}.
     */
    protected boolean motoresEncendidos = false;
    protected PilotoBase piloto;
    private runner enemigo; // Declaración de la variable enemigo // AGREGADO PARA LA NAVE RUNNER

    /**
     * Inicializa una nueva NaveDeAtaque con los motores apagados
     */
    public NaveDeAtaque() {
        super();
    }

    /**
     * Inicializa una nueva NaveDeAtaque con los motores apagados. Este constructor
     * es empleado mayormente para la creación de escenarios.
     * 
     * @param direccion es la orientación con la que se creará la NaveDeAtaque
     * @param carga     es la carga de combustible inicial de la NaveDeAtaque
     */
    public NaveDeAtaque(Direccion direccion, int carga, NaveDeAtaqueEnemiga enemigo) {
        super();
        setDireccion(direccion);
        this.combustible = carga;

    }
    public void setEnemigo(runner enemigo) {
        this.enemigo = enemigo; // AGREGADO PARA LA NAVE RUNNER
    }

    /**
     * pre: posee combustible {@link NaveAliada#combustible} y los motores se
     * encuentran apagados {@link NaveDeAtaque#motoresEncendidos} <br/>
     * post: encenderá sus motores
     */
    public void encenderMotores() {
        if (this.combustible > 0 && !this.motoresEncendidos) {
            this.motoresEncendidos = true;
            Greenfoot.playSound("engine-on.wav");
            int tamCelda = getWorld().getCellSize();
            imagenBase = new GreenfootImage("weaponized-ship-on.png");
            imagenBase.scale((int) (tamCelda * ESCALA_X), (int) (tamCelda * ESCALA_Y));
            actualizarImagen();
        }
    }

    /**
     * pre: los motores se encuentran encendidos
     * {@link NaveDeAtaque#motoresEncendidos} <br/>
     * post: apagará sus motores
     */
    public void apagarMotores() {
        if (this.motoresEncendidos) {
            this.motoresEncendidos = false;
            Greenfoot.playSound("engine-off.wav");
            int tamCelda = getWorld().getCellSize();
            imagenBase = new GreenfootImage("weaponized-ship.png");
            imagenBase.scale((int) (tamCelda * ESCALA_X), (int) (tamCelda * ESCALA_Y));
            actualizarImagen();
        }
    }

    /**
     * {@inheritDoc}
     */
    protected boolean puedeActuar() {
        return super.puedeActuar() && this.motoresEncendidos;
    }

    /**
     * {@inheritDoc} <br>
     * post: si se agota el {@link NaveAliada#combustible}, se apagarán los motores
     */
    protected void consumirCombustible(int cantidad) {
        super.consumirCombustible(cantidad);
        if (combustible <= 0) {
            this.apagarMotores();
        }
    }

    /**
     * pre: La NaveDeAtaque {@link #puedeActuar()} <br>
     * post: El {@link NaveAliada#combustible} se reducirá en
     * {@link #obtenerConsumoPorAtaque()}. Si en la dirección deseada hay un
     * {@link Dañable}, éste recibirá {@link #obtenerDaño()}.
     * 
     * @param direccion
     */
    public void atacarHacia(Direccion direccion) {
        if (!puedeActuar()) {
            return;
        }
        this.direccion = direccion;
        actualizarImagen();
        setRotation(direccion.rotacion);
        Greenfoot.delay(20);
        consumirCombustible(obtenerConsumoPorAtaque());

        Actor actor = getOneObjectAtOffset(this.direccion.dx, this.direccion.dy, Actor.class);
        if (!(actor instanceof Dañable)) {
            return;
        }
        Dañable objetivo = (Dañable) actor;
        if (objetivo != null) {
            Greenfoot.playSound("laser-shot.wav");
            objetivo.recibirDañoDe(this);}
        }
    

    /**
     * @see NaveAliada#moverHacia(Direccion)
     */
    public void avanzarHacia(Direccion direccion) {
    super.moverHacia(direccion);
    System.out.println("Moviendo NaveDeAtaque");
    if (enemigo != null) {
        enemigo.muevelo();
        System.out.println("Moviendo NaveDeAtaqueEnemiga");
    } else {
        System.out.println("problema, enemigo null");
    }
}

    

    /**
     * {@inheritDoc}
     */
    @Override
    public int obtenerCombustible() {
        return super.obtenerCombustible();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    int obtenerCombustibleMaximo() {
        return 150;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int obtenerDaño() {
        return 35;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    int obtenerConsumoPorMovimiento() {
        return 7;
    }

    /**
     * @return la cantidad de combustible que consume realizar un ataque
     */
    int obtenerConsumoPorAtaque() {
        return 10;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean estaEnElBorde() {
        return super.estaEnElBorde();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hayVacioHacia(Direccion direccion) {
        return super.hayVacioHacia(direccion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hayAsteroideHacia(Direccion direccion) {
        return super.hayAsteroideHacia(direccion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hayItemHacia(Direccion direccion) {
        return super.hayItemHacia(direccion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hayNaveHacia(Direccion direccion) {
        return super.hayNaveHacia(direccion);
    }
    
    /**
     * pre: La NaveDeAtaque {@link #puedeActuar()} <br>
     * post: Obtiene la salud de una NaveDeAtaqueEnemiga o Asteroide, en
     * cierta Direccion.
     * 
     * @param direccion
     * @return la salud de una nave enemiga o el tamaño de un asteroide
     */
    public int escanearIndicadorHacia(Direccion direccion) {
        int valor = 0;
        if (hayNaveHacia(direccion)) {
            NaveDeAtaqueEnemiga nave = (NaveDeAtaqueEnemiga) getOneObjectAtOffset(direccion.dx, direccion.dy, NaveDeAtaqueEnemiga.class);
            valor = nave.obtenerSalud();
        } else if (hayAsteroideHacia(direccion)) {
            Asteroide asteroide = (Asteroide) getOneObjectAtOffset(direccion.dx, direccion.dy, Asteroide.class);
            valor = asteroide.obtenerTamaño();
        }
        return valor;        
    }
    
    public void recibirPiloto(PilotoBase piloto) {
        this.piloto = piloto;
        actualizarImagen();
    }
    
    public void bajarPiloto() {
        this.piloto = null;
        actualizarImagen();
    }
    
    @Override
    protected void actualizarImagen() {
        int tamCelda = getWorld().getCellSize();
        GreenfootImage image = getImage();
        image.scale((int) (tamCelda * ESCALA_X), (int) (tamCelda * ESCALA_Y));
        setImage(image);

        MyGreenfootImage canvas = new MyGreenfootImage(imagenBase.getWidth(),
                imagenBase.getHeight() + getWorld().getCellSize() / 3);

        canvas.setColor(Color.BLACK);
        canvas.fillRect(4, imagenBase.getHeight() - 2, getWorld().getCellSize() - 6, 12);
        canvas.setColor(obtenerColorDeBarraIndicadora());

        canvas.fillRect(6, imagenBase.getHeight(),
            (int) ((getWorld().getCellSize() - 10) * obtenerProporcionDeBarraIndicadora()), 8);

        canvas.rotate(360 - direccion.rotacion);

        canvas.drawImage(imagenBase, 0, getWorld().getCellSize() / 6);
        setImage(canvas);


        if(this.piloto != null){
          canvas.highlight(this.piloto.getAura());
        };

    }
}

// barrera 
public boolean hayBarreraHacia(Direccion direccion) {
        return super.hayActorHacia(Barrera.class, direccion);
    }
 public boolean moverHaciaBarrera2(Direccion direccion) {
        // Verifica si la nave puede actuar y si tiene suficiente combustible
        if (!puedeActuar() || this.combustible < obtenerConsumoPorMovimiento()) {
            return false; // No puede actuar o no tiene suficiente combustible
        }

        // Verifica si hay una barrera en la dirección deseada
        if (hayBarreraHacia(direccion)) {
            return false; // No se puede mover si hay una barrera
        }

        // Llama al método de la clase base para mover la nave
        if (!super.moverHacia(direccion)) {
            return false; // Si no se puede mover, retorna false
        }
       
       
        // Consume el combustible correspondiente
        consumirCombustible(obtenerConsumoPorMovimiento());

        // Verifica si hay un ítem en la nueva posición y recógelo
        Item item = (Item) getOneObjectAtOffset(0, 0, Item.class);
        if (item != null) {
            this.cargarCombustible(item.serRecogido());
        }

        return true; // Movimiento exitoso
    }

    public boolean hayBarreraHacia2(Direccion direccion) {
        return super.hayActorHacia(Barrera.class, direccion);
    }
 public void Barrera ( NaveDeAtaque nave, Direccion direccion){
        while(! nave.hayBarreraHacia(Direccion.ESTE)){
            nave.avanzarHacia(direccion);
        };
    }
