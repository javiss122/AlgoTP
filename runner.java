import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class runner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class runner extends NaveEnemiga
{
   
    public runner(Direccion direccion) {
        super(direccion);
        GreenfootImage nuevaImagen = new GreenfootImage("nave-runner.png");
        setImage(nuevaImagen);
    }
    
    public void reaccionarAlMoverse() {
        
                System.out.println("La nave enemiga se ha movido.");

    }
    // AGREGADO PARA LA NAVE RUNNER
    public void muevelo() {
        // Define movimiento de la nave
        int numero = (int) (Math.random() * 4);
        if (numero == 0){ 
            moverHacia(Direccion.NORTE); 
        }
        else if (numero == 1){
            moverHacia(Direccion.SUR); 
        }
        else if (numero == 2){
            moverHacia(Direccion.ESTE); 

        }
        else {
            moverHacia(Direccion.OESTE); 

        }
        
    }
}
