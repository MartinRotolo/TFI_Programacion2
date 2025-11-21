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
    private String aseguradora;   
    private String nroPoliza;      
    private CoberturaEnum cobertura;  
    private LocalDate vencimiento;   
    private Long vehiculoId;

    // Constructor vacío
    public SeguroVehicular() {
    }

    // Constructor completo 
    public SeguroVehicular(Long id, Boolean eliminado, String aseguradora, String nroPoliza, CoberturaEnum cobertura, LocalDate vencimiento) {
        this.id = id;
        this.eliminado = eliminado;
        this.aseguradora = aseguradora;
        this.nroPoliza = nroPoliza;
        this.cobertura = cobertura;
        this.vencimiento = vencimiento;
        
    }

    // Getters y setters 
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

    public CoberturaEnum getCobertura() {
        return cobertura;
    }

    public void setCobertura(CoberturaEnum cobertura) {
        this.cobertura = cobertura;
    }

    public LocalDate getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(LocalDate vencimiento) {
        this.vencimiento = vencimiento;
    }
    
    public Long getVehiculoId() { 
        return vehiculoId; }
    
    public void setVehiculoId(Long vehiculoId) { 
        this.vehiculoId = vehiculoId; }

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