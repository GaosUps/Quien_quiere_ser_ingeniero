package io.github.gaosups.qqi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class Jugador {

	private final UUID jugadorUUID;

	private String nombre;
	private int puntos;
	private boolean estado;

	public Jugador(String nombre, int puntos, boolean estado) {
		this.jugadorUUID = UUID.randomUUID();
		this.nombre = nombre;
		this.puntos = puntos;
		this.estado = estado;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final Jugador jugador)) {
			return false;
		}
		return Objects.equals(getJugadorUUID(), jugador.getJugadorUUID());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getJugadorUUID());
	}
}
