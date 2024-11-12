import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Victoria here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Victoria extends Actor
{       public Victoria() {
        // Crear una imagen para el cartel de victoria
        GreenfootImage image = new GreenfootImage(600, 100); // Tamaño de la imagen
        image.setColor(Color.BLACK); // Color de fondo
        image.fill(); // Rellenar el fondo
        
        // Establecer el color del texto
        image.setColor(Color.WHITE);
        image.drawString("¡Victoria! Has destruido a la nave enemiga.", 50, 50); // Texto en la imagen
        
        setImage(image); // Establecer la imagen del actor
    }

    public void act() {
        // Aquí puedes agregar lógica adicional si es necesario
        // Por ejemplo, puedes hacer que el cartel desaparezca después de un tiempo
    }
}

