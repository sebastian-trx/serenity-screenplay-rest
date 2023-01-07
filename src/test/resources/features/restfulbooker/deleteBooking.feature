#language: es

Característica: eliminar una reserva

  Escenario: el usuario quiere eliminar una reserva
    Dado que el usuario esta registrado
    Cuando el usuario crea una reserva
    Y el usuario elimina la reserva
    Entonces el sistema responde con un status code 201