import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class portal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class portal extends ActorBase
{
    //CREAR ATRIBUTO DE POSICION Y DESPUES UTILIZAR COMO COORDENADA PARA MOVER LA NAVE
    private double ESCALA_X = 0.7;
    private double ESCALA_Y = 0.9;

    /**
     * Establece la imagen con la escala definida
     */
    @Override
    protected void actualizarImagen() {
        int tamCelda = getWorld().getCellSize();
        GreenfootImage image = getImage();
        image.scale((int) (tamCelda * ESCALA_X), (int) (tamCelda * ESCALA_Y));
        setImage(image);
    }

    /**
     * post: el Item desaparece del mundo
     * 
     * @return la cantidad de combustible que el Item proporciona
     */
    public void entrarAlPortal() {
        Greenfoot.delay(20);
    }
}

        
       
