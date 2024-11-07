//en la calse NaveDeAtaque 
//se implementa el siguiente codigo para que la nave no puedan atravcesarla
//de momento solo tenemos true o false
//falta pulir

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
