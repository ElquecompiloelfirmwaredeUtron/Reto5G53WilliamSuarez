
package Modelo;

public class Sucursal {
    private int idSucursal;
    private String nombreSucursal;
    
    public Sucursal(){
        idSucursal = 0;
        nombreSucursal = "";
    }
    
    public Sucursal(int idSucursal, String nombreSucursal){
        this.idSucursal = idSucursal;
        this.nombreSucursal = nombreSucursal;
    }
    
    public int getIdSucursal() {
        return idSucursal;
    }
    
    public void setIdSucursal(int idSucursal){
        this.idSucursal = idSucursal;
    }
    
    public String getNombreSucursal(){
        return nombreSucursal;
    }
    public void setNombreSucursal(String nombreSucursal){
        this.nombreSucursal = nombreSucursal;
    }
    
    public String tosString(){
        return getNombreSucursal();
    }
    
}