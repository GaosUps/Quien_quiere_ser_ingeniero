package com.juego.qqi.modelo;

public class Jugador {
    private int id_jugador;

    private String juagdor;
    private int puntos;
    private boolean estado;
    public Jugador(int id_jugador, String juagdor, int puntos, boolean estado) {
        this.id_jugador = id_jugador;
        this.juagdor = juagdor;
        this.puntos = puntos;
        this.estado = estado;
    }
    public int getId_jugador() {
        return id_jugador;
    }

    public String getJuagdor() {
        return juagdor;
    }

    public int getPuntos() {
        return puntos;
    }

    public boolean isEstado() {
        return estado;
    }
    public void setId_jugador(int id_jugador) {
        this.id_jugador = id_jugador;
    }

    public void setJuagdor(String juagdor) {
        this.juagdor = juagdor;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
