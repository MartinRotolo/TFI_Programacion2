/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segurosvehiculares.entities;

import java.time.LocalDate;

public class SeguroVehicular {

    private Long id;
    private Boolean eliminado;
    private String aseguradora;     // Corregido (basado en UML)
    private String nroPoliza;         // Corregido (basado en UML)
    private TipoCobertura cobertura;  // Corregido (usa el Enum TipoCobertura.java)
    private LocalDate vencimiento;      // Corregido (basado en UML)
    
    // (No hay referencia a Vehiculo, es unidireccional)

    // Constructor vacío
    public SeguroVehicular() {
    }

    // Constructor completo (Corregido)
    public SeguroVehicular(Long id, Boolean eliminado, String aseguradora, String nroPoliza, TipoCobertura cobertura, LocalDate vencimiento) {
        this.id = id;
        this.eliminado = eliminado;
        this.aseguradora = aseguradora;
        this.nroPoliza = nroPoliza;
        this.cobertura = cobertura;
        this.vencimiento = vencimiento;
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

    public String getAseguradora() {
        return aseguradora;
    }

    public void setAseguradora(String aseguradora) {
        this.aseguradora = aseguradora;
    }

    public String getNroPoliza() {
        return nroPoliza;
    }

    public void setNroPoliza(String nroPoliza) {
        this.nroPoliza = nroPoliza;
    }

    public TipoCobertura getCobertura() {
        return cobertura;
    }

    public void setCobertura(TipoCobertura cobertura) {
        this.cobertura = cobertura;
    }

    public LocalDate getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(LocalDate vencimiento) {
        this.vencimiento = vencimiento;
    }

    @Override
    public String toString() {
        return "SeguroVehicular{" +
                "id=" + id +
                ", aseguradora='" + aseguradora + '\'' +
                ", nroPoliza='" + nroPoliza + '\'' +
                ", cobertura=" + cobertura +
                ", vencimiento=" + vencimiento +
                ", eliminado=" + eliminado +
                '}';
    }
}