/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segurosvehiculares.entities;

public class Vehiculo {

    private Long id;
    private Boolean eliminado;
    private String dominio; // Corregido (antes 'patente')
    private String marca;
    private String modelo;
    private Integer anio;
    private String nroChasis; // Añadido
    
    // Relación 1→1 unidireccional
    private SeguroVehicular seguro; 

    // Constructor vacío
    public Vehiculo() {
    }

    // Constructor completo (Corregido)
    public Vehiculo(Long id, Boolean eliminado, String dominio, String marca, String modelo, Integer anio, String nroChasis, SeguroVehicular seguro) {
        this.id = id;
        this.eliminado = eliminado;
        this.dominio = dominio;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.nroChasis = nroChasis;
        this.seguro = seguro;
    }

    // Getters y setters (Actualizados)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getNroChasis() {
        return nroChasis;
    }

    public void setNroChasis(String nroChasis) {
        this.nroChasis = nroChasis;
    }

    public SeguroVehicular getSeguro() {
        return seguro;
    }

    public void setSeguro(SeguroVehicular seguro) {
        this.seguro = seguro;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "id=" + id +
                ", dominio='" + dominio + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anio=" + anio +
                ", nroChasis='" + nroChasis + '\'' +
                ", eliminado=" + eliminado +
                ", seguro=" + (seguro != null ? seguro.toString() : "SIN SEGURO") +
                '}';
    }
}