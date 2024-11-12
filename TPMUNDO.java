import greenfoot.*;

public class TPMUNDO extends MundoBase {
    
    public TPMUNDO() {
        super(aleatorioEntre(10, 18), aleatorioEntre(8, 14));        
        generarTodo();
    }

    private static int aleatorioEntre(int desde, int hasta) {
        return desde + (int) (Math.random() * (hasta - desde));
    }
    
    @Override
    protected void generarPOIs() {
        int ancho = getWidth();
        int alto = getHeight();

        int xNaveAliada = 0;
        int yNaveAliada = alto-1;
        
        marcarCelda(xNaveAliada, yNaveAliada, new Color(0, 0, 200, 150));
           
        marcarCelda(ancho-2, alto/10, new Color(178, 34, 34, 150));

    }
    
    private void generarTodo() {
    int ancho = getWidth();
    int alto = getHeight();

    int xNaveAliada = 0;
    int yNaveAliada = alto ;
    int xPortal = ancho / 2;
    int yPortal = 3 * alto / 5;
    int yNaveEnemiga = 4 * alto / 5;
    
    NaveDeAtaque naveAliada = new NaveDeAtaque();
    agregar(naveAliada, xNaveAliada, yNaveAliada);
    agregar(new portal2(), ancho-15, alto-6);
    agregar(new portal(), ancho-2, alto-2);
    for (int i = 0; i <alto; i++){
     agregar(new Asteroide(),ancho/2 , alto-1-i);
    
    }
    Planetas ceres = new ceres();  // O el tipo de planeta que desees
    agregar(ceres, ancho / 4, alto / 3);
    
    runner naveEnemiga = new runner(Direccion.NORTE);
    agregar(naveEnemiga, ancho-2, alto/10);
    
    naveAliada.setEnemigo(naveEnemiga); // AGREGADO PARA LA NAVE RUNNER
    
    }
}

