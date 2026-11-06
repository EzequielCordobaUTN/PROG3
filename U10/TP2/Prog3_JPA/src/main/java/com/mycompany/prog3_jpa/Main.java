package com.mycompany.prog3_jpa;

import com.mycompany.prog3_jpa.entity.Articulo;
import com.mycompany.prog3_jpa.entity.FacturaVenta;
import com.mycompany.prog3_jpa.entity.FacturaVentaDetalle;
import com.mycompany.prog3_jpa.entity.Marca;
import com.mycompany.prog3_jpa.entity.PuntoVenta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FacturacionPU");
        EntityManager em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("========== TP2 - Consultas JPQL ==========");
            runAll(em);
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
                    case "1": q01(em); break;
                    case "2": q02(em); break;
                    case "3": q03(em, "Rubro General"); break;
                    case "4": q04(em, LocalDate.now().minusDays(30), LocalDate.now().plusDays(30)); break;
                    case "5": q05(em, "EMITIDA", 10000.0); break;
                    case "6": q06(em, "a"); break;
                    case "7": q07(em); break;
                    case "8": q08(em); break;
                    case "9": q09(em, Arrays.asList(1, 2, 5)); break;
                    case "10": q10(em, "admin"); break;
                    case "11": q11(em, 1); break;
                    case "12": q12(em); break;
                    case "13": q13(em, "Marca Generica"); break;
                    case "14": q14(em); break;
                    case "15": q15(em, "20-12345678-9"); break;
                    case "16": q16(em); break;
                    case "17": q17(em, 5); break;
                    case "18": q18(em); break;
                    case "19": q19(em); break;
                    case "20": q20(em); break;
                    case "21": q21(em); break;
                    case "22": q22(em); break;
                    case "0": runAll(em); break;
                    case "q": case "exit": case "salir": running = false; break;
                    default: System.out.println("Opcion invalida."); break;
                }
                if (running && !opt.equals("0")) {
                    System.out.println();
                    System.out.print("Presione Enter para volver al menu...");
                    try { sc.nextLine(); } catch (java.util.NoSuchElementException eof) { running = false; }
                }
            }
            System.out.println("Saliendo. Adios.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
            sc.close();
        }
    }

    static void printMenu() {
        System.out.println();
        System.out.println("========== TP2 - Menu de consultas JPQL ==========");
        System.out.println(" 1  - Todas las facturas de venta");
        System.out.println(" 2  - Proyeccion numero/fecha/importeTotal");
        System.out.println(" 3  - Articulos de un rubro por denominacion");
        System.out.println(" 4  - Facturas en rango de fechas");
        System.out.println(" 5  - Facturas EMITIDA, importe>10000, no anuladas");
        System.out.println(" 6  - Clientes por texto o cuit 20-");
        System.out.println(" 7  - Estados distintos ordenados");
        System.out.println(" 8  - COUNT/SUM/AVG de facturas EMITIDA");
        System.out.println(" 9  - Puntos de venta numero IN (1,2,5)");
        System.out.println("10  - Facturas creadas por usuario 'admin'");
        System.out.println("11  - Detalles de facturas del PV numero 1");
        System.out.println("12  - Articulos con su marca (LEFT JOIN)");
        System.out.println("13  - Facturas con detalle de marca 'Marca Generica'");
        System.out.println("14  - Facturas con importe > promedio");
        System.out.println("15  - Facturas del cliente cuit '20-12345678-9'");
        System.out.println("16  - Facturas por punto de venta (GROUP BY)");
        System.out.println("17  - Usuarios con mas de 5 facturas (HAVING)");
        System.out.println("18  - Unidades y subtotal por marca");
        System.out.println("19  - Total facturado por condicion de IVA");
        System.out.println("20  - Marcas con articulo facturado (EXISTS)");
        System.out.println("21  - Articulos nunca facturados (NOT EXISTS)");
        System.out.println("22  - Categoria por importe (CASE)");
        System.out.println(" 0  - Ejecutar todas las consultas");
        System.out.println(" q  - Salir");
        System.out.print("Seleccione una opcion: ");
    }

    static void runAll(EntityManager em) {
        q01(em); q02(em); q03(em, "Rubro General");
        q04(em, LocalDate.now().minusDays(30), LocalDate.now().plusDays(30));
        q05(em, "EMITIDA", 10000.0); q06(em, "a"); q07(em); q08(em);
        q09(em, Arrays.asList(1, 2, 5)); q10(em, "admin"); q11(em, 1); q12(em);
        q13(em, "Marca Generica"); q14(em); q15(em, "20-12345678-9"); q16(em);
        q17(em, 5); q18(em); q19(em); q20(em); q21(em); q22(em);
        System.out.println();
        System.out.println("========== FIN TP2 ==========");
    }

    // 1. Lista completa de facturas de venta
    static void q01(EntityManager em) {
        section(1, "Todas las facturas de venta");
        List<FacturaVenta> res = em.createQuery("SELECT f FROM FacturaVenta f", FacturaVenta.class).getResultList();
        res.forEach(f -> System.out.println(f));
    }

    // 2. Proyeccion numero, fechaEmision, importeTotal
    static void q02(EntityManager em) {
        section(2, "Proyeccion numero/fecha/importeTotal");
        List<Object[]> res = em.createQuery(
                "SELECT f.numero, f.fechaEmision, f.importeTotal FROM FacturaVenta f", Object[].class).getResultList();
        for (Object[] r : res) System.out.println("numero=" + r[0] + " fecha=" + r[1] + " importe=" + r[2]);
    }

    // 3. Articulos de un rubro por denominacion
    static void q03(EntityManager em, String rubroDenom) {
        section(3, "Articulos del rubro '" + rubroDenom + "'");
        List<Articulo> res = em.createQuery(
                "SELECT a FROM Articulo a WHERE a.rubro.denominacion = :denom", Articulo.class)
                .setParameter("denom", rubroDenom).getResultList();
        res.forEach(a -> System.out.println(a));
    }

    // 4. Facturas en rango de fechas
    static void q04(EntityManager em, LocalDate desde, LocalDate hasta) {
        section(4, "Facturas entre " + desde + " y " + hasta);
        List<FacturaVenta> res = em.createQuery(
                "SELECT f FROM FacturaVenta f WHERE f.fechaEmision BETWEEN :desde AND :hasta", FacturaVenta.class)
                .setParameter("desde", desde).setParameter("hasta", hasta).getResultList();
        res.forEach(f -> System.out.println(f));
    }

    // 5. Facturas EMITIDA, importe>10000, fechaAnulacion IS NULL
    static void q05(EntityManager em, String estado, double minimo) {
        section(5, "Facturas " + estado + " importe>" + minimo + " no anuladas");
        List<FacturaVenta> res = em.createQuery(
                "SELECT f FROM FacturaVenta f WHERE f.estado = :estado AND f.importeTotal > :minimo AND f.fechaAnulacion IS NULL",
                FacturaVenta.class)
                .setParameter("estado", estado).setParameter("minimo", minimo).getResultList();
        res.forEach(f -> System.out.println(f));
    }

    // 6. Clientes cuya denominacion contiene texto (case-insensitive) OR cuit comienza con 20-
    static void q06(EntityManager em, String texto) {
        section(6, "Clientes con denominacion like '%" + texto + "%' o cuit 20-");
        List<Object> res = em.createQuery(
                "SELECT c FROM Cliente c " +
                        "WHERE LOWER(c.denominacion) LIKE LOWER(CONCAT('%', :texto, '%')) " +
                        "OR c.cuitCuil LIKE '20-%'")
                .setParameter("texto", texto).getResultList();
        res.forEach(System.out::println);
    }

    // 7. DISTINCT estados ordenados
    static void q07(EntityManager em) {
        section(7, "Estados distintos ordenados");
        List<String> res = em.createQuery(
                "SELECT DISTINCT f.estado FROM FacturaVenta f ORDER BY f.estado ASC", String.class).getResultList();
        res.forEach(System.out::println);
    }

    // 8. COUNT, SUM, AVG en un solo arreglo
    static void q08(EntityManager em) {
        section(8, "COUNT/SUM/AVG de facturas EMITIDA");
        Object[] r = (Object[]) em.createQuery(
                "SELECT COUNT(f), SUM(f.importeTotal), AVG(f.importeTotal) FROM FacturaVenta f WHERE f.estado = :estado")
                .setParameter("estado", "EMITIDA").getSingleResult();
        System.out.println("cantidad=" + r[0] + " suma=" + r[1] + " promedio=" + r[2]);
    }

    // 9. Puntos de venta cuyo numero IN lista
    static void q09(EntityManager em, List<Integer> numeros) {
        section(9, "Puntos de venta numero IN " + numeros);
        List<PuntoVenta> res = em.createQuery(
                "SELECT pv FROM PuntoVenta pv WHERE pv.numero IN :numeros", PuntoVenta.class)
                .setParameter("numeros", numeros).getResultList();
        res.forEach(pv -> System.out.println(pv));
    }

    // 10. Facturas creadas por usuario (navegacion usuarioCarga.usuario)
    static void q10(EntityManager em, String usuario) {
        section(10, "Facturas creadas por usuario '" + usuario + "'");
        List<FacturaVenta> res = em.createQuery(
                "SELECT f FROM FacturaVenta f WHERE f.usuarioCarga.usuario = :usuario", FacturaVenta.class)
                .setParameter("usuario", usuario).getResultList();
        res.forEach(f -> System.out.println(f));
    }

    // 11. Detalles de facturas emitidas por un punto de venta (INNER JOIN)
    static void q11(EntityManager em, int numeroPV) {
        section(11, "Detalles de facturas del PV numero " + numeroPV);
        List<FacturaVentaDetalle> res = em.createQuery(
                "SELECT d FROM FacturaVentaDetalle d INNER JOIN d.factura f WHERE f.puntoVenta.numero = :num", FacturaVentaDetalle.class)
                .setParameter("num", numeroPV).getResultList();
        res.forEach(d -> System.out.println(d));
    }

    // 12. LEFT JOIN articulos con marca (incluye sin marca)
    static void q12(EntityManager em) {
        section(12, "Articulos con su marca (LEFT JOIN)");
        List<Object[]> res = em.createQuery(
                "SELECT a.denominacion, m.denominacion FROM Articulo a LEFT JOIN a.marca m", Object[].class).getResultList();
        for (Object[] r : res) System.out.println("articulo=" + r[0] + " marca=" + r[1]);
    }

    // 13. Facturas que contienen detalle de articulo de una marca
    static void q13(EntityManager em, String marcaDenom) {
        section(13, "Facturas con detalle de marca '" + marcaDenom + "'");
        List<FacturaVenta> res = em.createQuery(
                "SELECT DISTINCT f FROM FacturaVenta f JOIN f.detalles d WHERE d.listaPrecioArticulo.articulo.marca.denominacion = :marca",
                FacturaVenta.class).setParameter("marca", marcaDenom).getResultList();
        res.forEach(f -> System.out.println(f));
    }

    // 14. Facturas con importeTotal mayor al promedio
    static void q14(EntityManager em) {
        section(14, "Facturas con importe > promedio");
        List<FacturaVenta> res = em.createQuery(
                "SELECT f FROM FacturaVenta f WHERE f.importeTotal > (SELECT AVG(f2.importeTotal) FROM FacturaVenta f2)",
                FacturaVenta.class).getResultList();
        res.forEach(f -> System.out.println(f));
    }

    // 15. Facturas asociadas a un cliente por CUIT/CUIL
    static void q15(EntityManager em, String cuit) {
        section(15, "Facturas del cliente cuit '" + cuit + "'");
        List<FacturaVenta> res = em.createQuery(
                "SELECT f FROM FacturaVenta f WHERE f.cliente.cuitCuil = :cuit", FacturaVenta.class)
                .setParameter("cuit", cuit).getResultList();
        res.forEach(f -> System.out.println(f));
    }

    // 16. Agrupamiento por punto de venta
    static void q16(EntityManager em) {
        section(16, "Facturas por punto de venta");
        List<Object[]> res = em.createQuery(
                "SELECT pv.descripcion, COUNT(f), SUM(f.importeTotal) FROM FacturaVenta f JOIN f.puntoVenta pv GROUP BY pv.descripcion",
                Object[].class).getResultList();
        for (Object[] r : res) System.out.println("pv=" + r[0] + " cant=" + r[1] + " total=" + r[2]);
    }

    // 17. Usuarios de carga con mas de N facturas
    static void q17(EntityManager em, long minFacturas) {
        section(17, "Usuarios con mas de " + minFacturas + " facturas");
        List<Object[]> res = em.createQuery(
                "SELECT u.nombre, COUNT(f) FROM FacturaVenta f JOIN f.usuarioCarga u GROUP BY u HAVING COUNT(f) > :min",
                Object[].class).setParameter("min", minFacturas).getResultList();
        for (Object[] r : res) System.out.println("usuario=" + r[0] + " cant=" + r[1]);
    }

    // 18. Agrupamiento por marca: denominacion, SUM(cantidad), SUM(subtotal)
    static void q18(EntityManager em) {
        section(18, "Unidades y subtotal por marca");
        List<Object[]> res = em.createQuery(
                "SELECT a.marca.denominacion, SUM(d.cantidad), SUM(d.importeSubtotal) " +
                        "FROM FacturaVentaDetalle d JOIN d.listaPrecioArticulo lpa JOIN lpa.articulo a " +
                        "GROUP BY a.marca.denominacion", Object[].class).getResultList();
        for (Object[] r : res) System.out.println("marca=" + r[0] + " cant=" + r[1] + " subtotal=" + r[2]);
    }

    // 19. Agrupamiento por condicion de IVA
    static void q19(EntityManager em) {
        section(19, "Total facturado por condicion de IVA");
        List<Object[]> res = em.createQuery(
                "SELECT c.denominacion, SUM(f.importeTotal) FROM FacturaVenta f JOIN f.condicionIva c GROUP BY c.denominacion",
                Object[].class).getResultList();
        for (Object[] r : res) System.out.println("condicion=" + r[0] + " total=" + r[1]);
    }

    // 20. Marcas con al menos un articulo facturado (EXISTS)
    static void q20(EntityManager em) {
        section(20, "Marcas con articulo facturado (EXISTS)");
        List<Marca> res = em.createQuery(
                "SELECT m FROM Marca m WHERE EXISTS (" +
                        "SELECT d FROM FacturaVentaDetalle d WHERE d.listaPrecioArticulo.articulo.marca = m)", Marca.class)
                .getResultList();
        res.forEach(m -> System.out.println(m));
    }

    // 21. Articulos nunca incluidos en detalle (NOT EXISTS)
    static void q21(EntityManager em) {
        section(21, "Articulos nunca facturados (NOT EXISTS)");
        List<Articulo> res = em.createQuery(
                "SELECT a FROM Articulo a WHERE NOT EXISTS (" +
                        "SELECT d FROM FacturaVentaDetalle d WHERE d.listaPrecioArticulo.articulo = a)", Articulo.class)
                .getResultList();
        res.forEach(a -> System.out.println(a));
    }

    // 22. CASE WHEN categoria por importe, ordenado desc
    static void q22(EntityManager em) {
        section(22, "Categoria por importe (CASE)");
        List<Object[]> res = em.createQuery(
                "SELECT f.numero, f.importeTotal, " +
                        "CASE WHEN f.importeTotal > 50000 THEN 'ALTO VALOR' " +
                        "WHEN f.importeTotal >= 10000 AND f.importeTotal <= 50000 THEN 'MEDIO VALOR' " +
                        "ELSE 'BAJO VALOR' END " +
                        "FROM FacturaVenta f ORDER BY f.importeTotal DESC", Object[].class).getResultList();
        for (Object[] r : res) System.out.println("numero=" + r[0] + " importe=" + r[1] + " categoria=" + r[2]);
    }

    static void section(int n, String titulo) {
        System.out.println();
        System.out.println("--- Consulta " + n + ": " + titulo + " ---");
    }
}
