package com.mycompany.prog3_jpa;

import com.mycompany.prog3_jpa.entity.Articulo;
import com.mycompany.prog3_jpa.entity.CondicionIva;
import com.mycompany.prog3_jpa.entity.FacturaVenta;
import com.mycompany.prog3_jpa.entity.FacturaVentaDetalle;
import com.mycompany.prog3_jpa.entity.ListaPrecio;
import com.mycompany.prog3_jpa.entity.ListaPrecioArticulo;
import com.mycompany.prog3_jpa.entity.Marca;
import com.mycompany.prog3_jpa.entity.PuntoVenta;
import com.mycompany.prog3_jpa.entity.Rubro;
import com.mycompany.prog3_jpa.entity.TipoMoneda;
import com.mycompany.prog3_jpa.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FacturacionPU");
        EntityManager em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("========== TP1 - JPA/Hibernate ==========");
            persistirFacturaEjemplo(em);
            boolean running = true;
            while (running) {
                printMenu();
                String opt;
                try {
                    opt = sc.nextLine().trim().toLowerCase();
                } catch (java.util.NoSuchElementException eof) {
                    running = false;
                    break;
                }
                System.out.println();
                switch (opt) {
                    case "1":
                        persistirFacturaEjemplo(em);
                        break;
                    case "2":
                        listarFacturas(em);
                        break;
                    case "q": case "exit": case "salir":
                        running = false;
                        break;
                    default:
                        System.out.println("Opcion invalida.");
                        break;
                }
                if (running && !opt.equals("1")) {
                    System.out.println();
                    System.out.print("Presione Enter para volver al menu...");
                    try { sc.nextLine(); } catch (java.util.NoSuchElementException eof) { running = false; }
                }
            }
            System.out.println("Saliendo. Adios.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
            sc.close();
        }
    }

    static void printMenu() {
        System.out.println();
        System.out.println("========== TP1 - Menu ==========");
        System.out.println(" 1  - Persistir factura de ejemplo");
        System.out.println(" 2  - Listar facturas de venta");
        System.out.println(" q  - Salir");
        System.out.print("Seleccione una opcion: ");
    }

    static void persistirFacturaEjemplo(EntityManager em) {
        try {
            em.getTransaction().begin();

            Usuario usuario = new Usuario();
            usuario.setUsuario("admin");
            usuario.setClave("admin");
            usuario.setNombre("Admin");
            usuario.setApellido("Sistema");
            em.persist(usuario);

            LocalDate hoy = LocalDate.now();

            PuntoVenta pv = new PuntoVenta();
            pv.setNumero(1);
            pv.setDescripcion("Punto de Venta 1");
            pv.setTipoEmision("CAE");
            pv.setDomicilioComercial("Calle Falsa 123");
            pv.setFechaAlta(hoy);
            pv.setFechaModificacion(hoy);
            pv.setUsuarioCarga(usuario);
            pv.setUsuarioModificacion(usuario);
            em.persist(pv);

            TipoMoneda tm = new TipoMoneda();
            tm.setCodigoAfip("PES");
            tm.setDenominacion("Pesos Argentinos");
            tm.setSimbolo("$");
            tm.setFechaAlta(hoy);
            tm.setFechaModificacion(hoy);
            tm.setUsuarioCarga(usuario);
            tm.setUsuarioModificacion(usuario);
            em.persist(tm);

            CondicionIva ci = new CondicionIva();
            ci.setCodigoAfip(1);
            ci.setDenominacion("IVA Responsable Inscripto");
            ci.setFechaAlta(hoy);
            ci.setFechaModificacion(hoy);
            ci.setUsuarioCarga(usuario);
            ci.setUsuarioModificacion(usuario);
            em.persist(ci);

            Rubro rubro = new Rubro();
            rubro.setDenominacion("Rubro General");
            rubro.setCodigo(1);
            rubro.setFechaAlta(hoy);
            rubro.setFechaModificacion(hoy);
            rubro.setUsuarioCarga(usuario);
            rubro.setUsuarioModificacion(usuario);
            em.persist(rubro);

            Marca marca = new Marca();
            marca.setDenominacion("Marca Generica");
            marca.setCodigo(1);
            marca.setFechaAlta(hoy);
            marca.setFechaModificacion(hoy);
            marca.setUsuarioCarga(usuario);
            marca.setUsuarioModificacion(usuario);
            em.persist(marca);

            Articulo articulo = new Articulo();
            articulo.setCodigo("ART001");
            articulo.setDenominacion("Articulo de prueba");
            articulo.setRubro(rubro);
            articulo.setMarca(marca);
            articulo.setFechaAlta(hoy);
            articulo.setFechaModificacion(hoy);
            articulo.setUsuarioCarga(usuario);
            articulo.setUsuarioModificacion(usuario);
            em.persist(articulo);

            ListaPrecio lp = new ListaPrecio();
            lp.setCodigo("LP01");
            lp.setDenominacion("Lista Precio General");
            lp.setFechaAlta(hoy);
            lp.setFechaModificacion(hoy);
            lp.setUsuarioCarga(usuario);
            lp.setUsuarioModificacion(usuario);
            em.persist(lp);

            ListaPrecioArticulo lpa = new ListaPrecioArticulo();
            lpa.setListaPrecio(lp);
            lpa.setArticulo(articulo);
            lpa.setPrecioVenta(100.0);
            lpa.setFechaAlta(hoy);
            lpa.setFechaModificacion(hoy);
            lpa.setUsuarioCarga(usuario);
            lpa.setUsuarioModificacion(usuario);
            em.persist(lpa);

            FacturaVenta fv = new FacturaVenta();
            fv.setNumero(1L);
            fv.setFechaEmision(hoy);
            fv.setCondicionIva(ci);
            fv.setTipoMoneda(tm);
            fv.setPuntoVenta(pv);
            fv.setImporteTotal(100.0);
            fv.setEstado("EMITIDA");
            fv.setFechaAlta(hoy);
            fv.setFechaModificacion(hoy);
            fv.setUsuarioCarga(usuario);
            fv.setUsuarioModificacion(usuario);

            FacturaVentaDetalle det = new FacturaVentaDetalle();
            det.setListaPrecioArticulo(lpa);
            det.setDescripcion("Detalle de prueba");
            det.setCantidad(1.0);
            det.setPrecioUnitario(100.0);
            det.setImporteSubtotal(100.0);
            fv.addDetalle(det);

            em.persist(fv);

            em.getTransaction().commit();
            System.out.println("FacturaVenta persistida con id=" + fv.getId());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        }
    }

    static void listarFacturas(EntityManager em) {
        System.out.println("--- Facturas de venta ---");
        List<FacturaVenta> res = em.createQuery("SELECT f FROM FacturaVenta f", FacturaVenta.class).getResultList();
        res.forEach(f -> System.out.println(f));
    }
}
