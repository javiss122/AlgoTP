import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class ceres here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ceres extends Planetas
{
    /**
     * Act - do whatever the ceres wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
        //cambiar para el size
     public ceres() {
        super(40); 
    }
 @Override
public void recibirDañoDe(Atacante atacante) {
    super.recibirDañoDe(atacante);

    if (this.tamaño <= 0) {
        System.out.println("El planeta ha sido destruido, intentando aplicar buff...");
        
        // Verifica si el mundo está disponible
        if (getWorld() != null) {
            World mundo = getWorld();
            List<NaveDeAtaque> naves = mundo.getObjects(NaveDeAtaque.class);

            // Si la nave está en el mundo, aplica el buff
            if (!naves.isEmpty()) {
                NaveDeAtaque nave = naves.get(0);
                nave.activarCombustibleInfinito();
                System.out.println("Buff activado en la nave.");
            } else {
                System.out.println("La nave no está en el mundo, no se pudo aplicar el buff.");
            }

            // Retrasar la eliminación del planeta
            Greenfoot.delay(1); // Agregar un pequeño retraso antes de eliminar el planeta
            getWorld().removeObject(this);
            System.out.println("Planeta eliminado del mundo.");
        } else {
            System.out.println("El mundo es null, no se puede aplicar el buff.");
        }
    }
}




}

