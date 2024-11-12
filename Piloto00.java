public class Piloto00 extends PilotoBase {

    @Override
    public void subirse(NaveDeAtaque nave) {
        super.subirse(nave);
    }
    
    @Override
    public void bajarse() {
        super.bajarse();
    }

    void despegar() {
        navePilotada.encenderMotores();
    }
    
    void maniobrar(NaveDeAtaque nave) {
        subirse(nave);
        nave.encenderMotores();
        for (int i = 0; i < 10; i++){
            nave.avanzarHacia(Direccion.NORTE);
        }
        }
    

    
    void avanzarAlNortePor(int casilleros) {
        for (int pasos = 0; pasos < casilleros; pasos++) {
            navePilotada.avanzarHacia(Direccion.NORTE);
        }
    }

    int destruirAsteroideHacia(Direccion direccion) {
        int ataques = 0;
        while (navePilotada.hayAsteroideHacia(direccion)) {
            navePilotada.atacarHacia(direccion);
            ataques++;
        }
        return ataques;
    }
    void atacarHacia(NaveDeAtaque nave, Direccion direccion) {
        subirse(nave);
        nave.encenderMotores();
        nave.atacarHacia(direccion);
        nave.atacarHacia(direccion);
        nave.atacarHacia(direccion);
        }
        
    

    void llegarALaBaseNorte() {
        avanzarAlNortePor(5);
        destruirAsteroideHacia(Direccion.NORTE);
        avanzarAlNortePor(3);
    }
}
