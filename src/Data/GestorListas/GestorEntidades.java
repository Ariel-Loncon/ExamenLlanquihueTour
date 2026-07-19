package Data.GestorListas;

import Data.GestorArchivotxt.GestorCliente;
import Data.GestorArchivotxt.GestorGuias;
import Data.GestorArchivotxt.GestorProveedor;
import Data.GestorArchivotxt.GestorServicios;
import Model.*;
import Model.Tours.PaseoLacustre;
import Model.Tours.RutaCultural;
import Model.Tours.ServivioTuristico;
import Model.Tours.TourGastronomico;

import java.util.ArrayList;

/**
 * Clase encargada de gestionar de forma polimórfica la colección de entidades registrables.
 * Aplica la verificación de tipos mediante instanceof para diferenciar comportamientos.
 */
public class GestorEntidades {
    private ArrayList<Registrable> baseDeDatos = new ArrayList<>();

    public void cargarTodo() {

        baseDeDatos.clear();

        baseDeDatos.addAll(new GestorCliente().readClienteText("resources/Cliente.txt"));
        baseDeDatos.addAll(new GestorGuias().readGuiasText("resources/Guias.txt"));
        baseDeDatos.addAll(new GestorProveedor().readProveedoresText("resources/Proveedores.txt"));
        baseDeDatos.addAll(new GestorServicios().readServiciosText("resources/Servicios.txt"));
    }

    public void agregar(Registrable item) {
        baseDeDatos.add(item);
    }

    public ArrayList<Registrable> getBaseDeDatos() {
        return baseDeDatos;
    }


    public void mostrarTodo() {
        for (Registrable item : baseDeDatos) {

            item.mostrarResumen();

            if (item instanceof Guia) {
                Guia g = (Guia) item;
                System.out.println(" Email: : " + g.getEmail());

            } else if (item instanceof Cliente) {
                Cliente c = (Cliente) item;
                System.out.println(" Orden de Compra " + c.getOrdenCompra() + "Tipo de Pago:" + c.getTipoDePago());

            } else if (item instanceof Proveedor) {
                Proveedor p = (Proveedor) item;
                System.out.println(" Area: " + p.getTipoServicio());

            } else if (item instanceof ServivioTuristico) {
                System.out.print("   [Verificación] -> Rol: Servicio Turístico Activo. ");

                if (item instanceof TourGastronomico) {
                    TourGastronomico tg = (TourGastronomico) item;
                    System.out.println("Paradas:" + tg.getNumeroParadas());
                } else if (item instanceof PaseoLacustre) {
                    PaseoLacustre pl = (PaseoLacustre) item;
                    System.out.println("Embarcación: " + pl.getTipoEmbarcacion());
                } else if (item instanceof RutaCultural) {
                    RutaCultural rt = (RutaCultural) item;
                    System.out.println("Lugar: " + rt.getLugarHistorico());
                }
            }
            System.out.println("--------------------------------------------------------------------------------");
        }
    }

}
