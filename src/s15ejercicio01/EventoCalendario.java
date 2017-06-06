/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s15ejercicio01;

/**
 *
 * @author alumno
 */
public class EventoCalendario {
    
    private int id;
    private String titulo;
    private int dia;
    private int mes;
    private int anio;

    public EventoCalendario() {
        this.id = 0;
        this.titulo = "";
        this.dia = 0;
        this.mes = 0;
        this.anio = 0;        
    }

    public EventoCalendario(int id, String titulo, 
                              int dia, int mes, int anio) {
        this.id = id;
        this.titulo = titulo;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
    
    
    
    
    
               
    
}
