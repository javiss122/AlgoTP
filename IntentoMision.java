//modificacion de la clase piloto
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
//se agrego direcciones 
    void avanzarAlNortePor(int casilleros) {
        for (int pasos = 0; pasos < casilleros; pasos++) {
            navePilotada.avanzarHacia(Direccion.NORTE);
        }
    }

    void avanzarAlOestePor(int casilleros) {
        for (int pasos = 0; pasos < casilleros; pasos++) {
            navePilotada.avanzarHacia(Direccion.OESTE);
        }
    }

    void avanzarAlSurPor(int casilleros) {
        for (int pasos = 0; pasos < casilleros; pasos++) {
            navePilotada.avanzarHacia(Direccion.SUR);
        }
    }

    void avanzarAlEstePor(int casilleros) {
        for (int pasos = 0; pasos < casilleros; pasos++) {
            navePilotada.avanzarHacia(Direccion.ESTE);
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

    void atacarHacia(NaveDeAtaque nave) {
        subirse(nave);
        nave.encenderMotores();
        nave.atacarHacia(Direccion.NORTE);
        nave.atacarHacia(Direccion.NORTE);
        nave.atacarHacia(Direccion.NORTE);
    }

     void llegarALaBaseNorte() {
        avanzarAlNortePor(5);
        destruirAsteroideHacia(Direccion.NORTE);
        avanzarAlNortePor(3);
    }


//en la clase piloto
 void mision1(NaveDeAtaque nave) {
        avanzarAlEstePor(6);

         while(!navePilotada.hayBarreraHacia(Direccion.ESTE)){
            navePilotada.avanzarHacia(Direccion.ESTE);
        };
        avanzarAlSurPor(3);;
        avanzarAlOestePor(6);
        avanzarAlNortePor(1);
        while(navePilotada.hayPortalHacia(Direccion.NORTE)){
            navePilotada.avanzarHacia(Direccion.NORTE);
        } ;

    }



//para andar debe estar declarado en la clase nave aliada lo siguiente : 
 public boolean hayBarreraHacia(Direccion direccion) {
        return super.hayActorHacia(Barrera.class, direccion);
    }

    public boolean hayPortalHacia(Direccion direccion) {
      return super.hayActorHacia(Portal.class, direccion);
    }
