-- data.sql (PostgreSQL) - datos de ejemplo para TP1 U10
-- IMPORTANTE: ejecutar conectado a la base 'tp1'.

DO $$
BEGIN
    IF current_database() <> 'tp1' THEN
        RAISE EXCEPTION 'Este script debe ejecutarse conectado a la base tp1 (actual: %)', current_database();
    END IF;
END $$;

INSERT INTO usuario (id, usuario, clave, nombre, apellido) VALUES (1, 'admin', 'admin', 'Admin', 'Sistema');
SELECT setval('usuario_id_seq', 1);

INSERT INTO rubro (id, denominacion, codigo, fechaAlta, fechaModificacion, usuarioCarga_id, usuarioModificacion_id)
VALUES (1, 'Rubro General', 1, CURRENT_DATE, CURRENT_DATE, 1, 1);
SELECT setval('rubro_id_seq', 1);

INSERT INTO marca (id, denominacion, codigo, fechaAlta, fechaModificacion, usuarioCarga_id, usuarioModificacion_id)
VALUES (1, 'Marca Generica', 1, CURRENT_DATE, CURRENT_DATE, 1, 1);
SELECT setval('marca_id_seq', 1);

INSERT INTO tipo_moneda (id, codigoAfip, denominacion, simbolo, fechaAlta, fechaModificacion, usuarioCarga_id, usuarioModificacion_id)
VALUES (1, 'PES', 'Pesos Argentinos', '$', CURRENT_DATE, CURRENT_DATE, 1, 1);
SELECT setval('tipo_moneda_id_seq', 1);

INSERT INTO punto_venta (id, numero, descripcion, tipoEmision, domicilioComercial, fechaAlta, fechaModificacion, usuarioCarga_id, usuarioModificacion_id)
VALUES (1, 1, 'Punto de Venta 1', 'CAE', 'Calle Falsa 123', CURRENT_DATE, CURRENT_DATE, 1, 1);
SELECT setval('punto_venta_id_seq', 1);

INSERT INTO condicion_iva (id, codigoAfip, denominacion, fechaAlta, fechaModificacion, usuarioCarga_id, usuarioModificacion_id)
VALUES (1, 1, 'IVA Responsable Inscripto', CURRENT_DATE, CURRENT_DATE, 1, 1);
SELECT setval('condicion_iva_id_seq', 1);

INSERT INTO articulo (id, codigo, denominacion, rubro_id, marca_id, fechaAlta, fechaModificacion, usuarioCarga_id, usuarioModificacion_id)
VALUES (1, 'ART001', 'Articulo de prueba', 1, 1, CURRENT_DATE, CURRENT_DATE, 1, 1);
SELECT setval('articulo_id_seq', 1);

INSERT INTO lista_precio (id, codigo, denominacion, fechaAlta, fechaModificacion, usuarioCarga_id, usuarioModificacion_id)
VALUES (1, 'LP01', 'Lista Precio General', CURRENT_DATE, CURRENT_DATE, 1, 1);
SELECT setval('lista_precio_id_seq', 1);

INSERT INTO lista_precio_articulo (id, listaPrecio_id, precioVenta, articulo_id, fechaAlta, fechaModificacion, usuarioCarga_id, usuarioModificacion_id)
VALUES (1, 1, 100.0, 1, CURRENT_DATE, CURRENT_DATE, 1, 1);
SELECT setval('lista_precio_articulo_id_seq', 1);

INSERT INTO factura_venta (id, numero, fechaEmision, condicionIva_id, tipoMoneda_id, puntoVenta_id, importeTotal, estado, fechaAlta, fechaModificacion, usuarioCarga_id, usuarioModificacion_id)
VALUES (1, 1, CURRENT_DATE, 1, 1, 1, 100.0, 'EMITIDA', CURRENT_DATE, CURRENT_DATE, 1, 1);
SELECT setval('factura_venta_id_seq', 1);

INSERT INTO factura_venta_detalle (id, factura_id, listaPrecioArticulo_id, descripcion, cantidad, precioUnitario, importeSubtotal)
VALUES (1, 1, 1, 'Detalle de prueba', 1.0, 100.0, 100.0);
SELECT setval('factura_venta_detalle_id_seq', 1);
