package com.mycompany.act1;

import com.mycompany.act1.entity.Consulta;
import com.mycompany.act1.entity.HistoriaClinica;
import com.mycompany.act1.entity.Medico;
import com.mycompany.act1.entity.Medicamento;
import com.mycompany.act1.entity.Paciente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Act1PU");
        EntityManager em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("========== Act1 - JPA Avanzada ==========");
            persistirDatosEjemplo(em);
            ejecutarTodas(em);
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
                    case "2": q02(em, 1L); break;
                    case "3": q03(em, 1L); break;
                    case "4": q04(em); break;
                    case "5": q05(em); break;
                    case "6": q06(em, "OSDE"); break;
                    case "7": q07(em); break;
                    case "8": q08(em); break;
                    case "0": ejecutarTodas(em); break;
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
        System.out.println("========== Act1 - Menu de consultas ==========");
        System.out.println(" 1  - Pacientes mayores de 30 anios");
        System.out.println(" 2  - Consultas de un medico especifico (id=1)");
        System.out.println(" 3  - Medicamentos asociados a un paciente (id=1)");
        System.out.println(" 4  - Consultas con diagnostico y nombre del paciente");
        System.out.println(" 5  - Promedio de edad de los pacientes");
        System.out.println(" 6  - Pacientes con obra social especifica (OSDE)");
        System.out.println(" 7  - Medicos y cantidad de consultas atendidas");
        System.out.println(" 8  - Pacientes con descripcion de historia clinica");
        System.out.println(" 0  - Ejecutar todas las consultas");
        System.out.println(" q  - Salir");
        System.out.print("Seleccione una opcion: ");
    }

    static void ejecutarTodas(EntityManager em) {
        q01(em); q02(em, 1L); q03(em, 1L); q04(em); q05(em); q06(em, "OSDE"); q07(em); q08(em);
        System.out.println();
        System.out.println("========== FIN Act1 ==========");
    }

    static void persistirDatosEjemplo(EntityManager em) {
        try {
            em.getTransaction().begin();

            Paciente p1 = new Paciente();
            p1.setNombre("Juan");
            p1.setApellido("Perez");
            p1.setEdad(35);
            p1.setObraSocial("OSDE");
            em.persist(p1);

            Paciente p2 = new Paciente();
            p2.setNombre("Maria");
            p2.setApellido("Gomez");
            p2.setEdad(28);
            p2.setObraSocial("Swiss Medical");
            em.persist(p2);

            Paciente p3 = new Paciente();
            p3.setNombre("Carlos");
            p3.setApellido("Lopez");
            p3.setEdad(45);
            p3.setObraSocial("OSDE");
            em.persist(p3);

            Medico m1 = new Medico();
            m1.setNombre("Gregory");
            m1.setApellido("House");
            m1.setEspecialidad("Diagnostico");
            em.persist(m1);

            Medico m2 = new Medico();
            m2.setNombre("Meredith");
            m2.setApellido("Grey");
            m2.setEspecialidad("Cirugia");
            em.persist(m2);

            Consulta c1 = new Consulta();
            c1.setFecha(LocalDate.of(2024, 1, 10));
            c1.setDiagnostico("Gripe");
            c1.setPaciente(p1);
            c1.setMedico(m1);
            em.persist(c1);

            Consulta c2 = new Consulta();
            c2.setFecha(LocalDate.of(2024, 2, 15));
            c2.setDiagnostico("Apendicitis");
            c2.setPaciente(p2);
            c2.setMedico(m2);
            em.persist(c2);

            Consulta c3 = new Consulta();
            c3.setFecha(LocalDate.of(2024, 3, 20));
            c3.setDiagnostico("Control");
            c3.setPaciente(p1);
            c3.setMedico(m1);
            em.persist(c3);

            Consulta c4 = new Consulta();
            c4.setFecha(LocalDate.of(2024, 4, 5));
            c4.setDiagnostico("Fractura");
            c4.setPaciente(p3);
            c4.setMedico(m2);
            em.persist(c4);

            HistoriaClinica h1 = new HistoriaClinica();
            h1.setDescripcion("Historia clinica de Juan");
            h1.setPaciente(p1);
            p1.setHistoriaClinica(h1);
            em.persist(h1);

            HistoriaClinica h2 = new HistoriaClinica();
            h2.setDescripcion("Historia clinica de Maria");
            h2.setPaciente(p2);
            p2.setHistoriaClinica(h2);
            em.persist(h2);

            Medicamento med1 = new Medicamento();
            med1.setNombre("Ibuprofeno");
            med1.setPaciente(p1);
            em.persist(med1);

            Medicamento med2 = new Medicamento();
            med2.setNombre("Amoxicilina");
            med2.setPaciente(p2);
            em.persist(med2);

            Medicamento med3 = new Medicamento();
            med3.setNombre("Paracetamol");
            med3.setPaciente(p1);
            em.persist(med3);

            em.getTransaction().commit();
            System.out.println("Datos de ejemplo persistidos.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        }
    }

    // 1. Pacientes mayores de 30 anios
    static void q01(EntityManager em) {
        section(1, "Pacientes mayores de 30 anios");
        List<Paciente> res = em.createQuery("SELECT p FROM Paciente p WHERE p.edad > 30", Paciente.class).getResultList();
        res.forEach(System.out::println);
    }

    // 2. Consultas de un medico especifico
    static void q02(EntityManager em, Long medicoId) {
        section(2, "Consultas del medico id=" + medicoId);
        List<Consulta> res = em.createQuery(
                "SELECT c FROM Consulta c WHERE c.medico.id = :id", Consulta.class)
                .setParameter("id", medicoId).getResultList();
        res.forEach(System.out::println);
    }

    // 3. Medicamentos asociados a un paciente
    static void q03(EntityManager em, Long pacienteId) {
        section(3, "Medicamentos del paciente id=" + pacienteId);
        List<Medicamento> res = em.createQuery(
                "SELECT m FROM Medicamento m WHERE m.paciente.id = :id", Medicamento.class)
                .setParameter("id", pacienteId).getResultList();
        res.forEach(System.out::println);
    }

    // 4. Consultas con diagnostico y nombre del paciente
    static void q04(EntityManager em) {
        section(4, "Consultas con diagnostico y paciente");
        List<Object[]> res = em.createQuery(
                "SELECT c.diagnostico, p.nombre FROM Consulta c JOIN c.paciente p", Object[].class).getResultList();
        for (Object[] r : res) System.out.println("diagnostico=" + r[0] + " paciente=" + r[1]);
    }

    // 5. Promedio de edad de los pacientes
    static void q05(EntityManager em) {
        section(5, "Promedio de edad de pacientes");
        Object r = em.createQuery("SELECT AVG(p.edad) FROM Paciente p").getSingleResult();
        System.out.println("promedio=" + r);
    }

    // 6. Pacientes con obra social especifica
    static void q06(EntityManager em, String obra) {
        section(6, "Pacientes con obra social '" + obra + "'");
        List<Paciente> res = em.createQuery(
                "SELECT p FROM Paciente p WHERE p.obraSocial = :obra", Paciente.class)
                .setParameter("obra", obra).getResultList();
        res.forEach(System.out::println);
    }

    // 7. Medicos y cantidad de consultas atendidas
    static void q07(EntityManager em) {
        section(7, "Medicos y cantidad de consultas");
        List<Object[]> res = em.createQuery(
                "SELECT m, COUNT(c) FROM Medico m LEFT JOIN m.consultas c GROUP BY m", Object[].class).getResultList();
        for (Object[] r : res) System.out.println(r[0] + " -> " + r[1]);
    }

    // 8. Pacientes con descripcion de historia clinica
    static void q08(EntityManager em) {
        section(8, "Pacientes con historia clinica");
        List<Object[]> res = em.createQuery(
                "SELECT p, p.historiaClinica.descripcion FROM Paciente p", Object[].class).getResultList();
        for (Object[] r : res) System.out.println(r[0] + " -> " + r[1]);
    }

    static void section(int n, String titulo) {
        System.out.println();
        System.out.println("--- Consulta " + n + ": " + titulo + " ---");
    }
}

