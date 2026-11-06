-- schema.sql (PostgreSQL) - TP1 U10 ORM/JPA
-- Generado segun el modelo de clases del PDF.
-- IMPORTANTE: ejecutar conectado a la base 'tp1' (no a 'postgres' ni otra).
-- Guard de seguridad: aborta si no estas en la base 'tp1'.

DO $$
BEGIN
    IF current_database() <> 'tp1' THEN
        RAISE EXCEPTION 'Este script debe ejecutarse conectado a la base tp1 (actual: %)', current_database();
    END IF;
END $$;

DROP TABLE IF EXISTS factura_venta_detalle CASCADE;
DROP TABLE IF EXISTS factura_venta CASCADE;
DROP TABLE IF EXISTS lista_precio_articulo CASCADE;
DROP TABLE IF EXISTS articulo CASCADE;
DROP TABLE IF EXISTS lista_precio CASCADE;
DROP TABLE IF EXISTS cliente CASCADE;
DROP TABLE IF EXISTS contacto CASCADE;
DROP TABLE IF EXISTS domicilio CASCADE;
DROP TABLE IF EXISTS condicion_iva CASCADE;
DROP TABLE IF EXISTS punto_venta CASCADE;
DROP TABLE IF EXISTS tipo_moneda CASCADE;
DROP TABLE IF EXISTS marca CASCADE;
DROP TABLE IF EXISTS rubro CASCADE;
DROP TABLE IF EXISTS usuario CASCADE;

CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY,
    usuario VARCHAR(255) NOT NULL,
    clave VARCHAR(255) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL
);

CREATE TABLE rubro (
    id BIGSERIAL PRIMARY KEY,
    denominacion VARCHAR(255) NOT NULL,
    codigo INTEGER NOT NULL,
    fechaAlta DATE NOT NULL,
    fechaBaja DATE,
    fechaModificacion DATE NOT NULL,
    usuarioBaja_id BIGINT REFERENCES usuario(id),
    usuarioCarga_id BIGINT NOT NULL REFERENCES usuario(id),
    usuarioModificacion_id BIGINT NOT NULL REFERENCES usuario(id)
);

CREATE TABLE marca (
    id BIGSERIAL PRIMARY KEY,
    denominacion VARCHAR(255) NOT NULL,
    codigo INTEGER NOT NULL,
    fechaAlta DATE NOT NULL,
    fechaBaja DATE,
    fechaModificacion DATE NOT NULL,
    usuarioBaja_id BIGINT REFERENCES usuario(id),
    usuarioCarga_id BIGINT NOT NULL REFERENCES usuario(id),
    usuarioModificacion_id BIGINT NOT NULL REFERENCES usuario(id)
);

CREATE TABLE tipo_moneda (
    id BIGSERIAL PRIMARY KEY,
    codigoAfip VARCHAR(255) NOT NULL,
    denominacion VARCHAR(255) NOT NULL,
    simbolo VARCHAR(255) NOT NULL,
    fechaAlta DATE NOT NULL,
    fechaBaja DATE,
    fechaModificacion DATE NOT NULL,
    usuarioBaja_id BIGINT REFERENCES usuario(id),
    usuarioCarga_id BIGINT NOT NULL REFERENCES usuario(id),
    usuarioModificacion_id BIGINT NOT NULL REFERENCES usuario(id)
);

CREATE TABLE punto_venta (
    id BIGSERIAL PRIMARY KEY,
    numero INTEGER NOT NULL,
    descripcion VARCHAR(255),
    tipoEmision VARCHAR(255),
    domicilioComercial VARCHAR(255),
    fechaAlta DATE NOT NULL,
    fechaBaja DATE,
    fechaModificacion DATE NOT NULL,
    usuarioBaja_id BIGINT REFERENCES usuario(id),
    usuarioCarga_id BIGINT NOT NULL REFERENCES usuario(id),
    usuarioModificacion_id BIGINT NOT NULL REFERENCES usuario(id)
);

CREATE TABLE condicion_iva (
    id BIGSERIAL PRIMARY KEY,
    codigoAfip INTEGER NOT NULL,
    denominacion VARCHAR(255) NOT NULL,
    fechaAlta DATE NOT NULL,
    fechaBaja DATE,
    fechaModificacion DATE NOT NULL,
    usuarioBaja_id BIGINT REFERENCES usuario(id),
    usuarioCarga_id BIGINT NOT NULL REFERENCES usuario(id),
    usuarioModificacion_id BIGINT NOT NULL REFERENCES usuario(id)
);

CREATE TABLE articulo (
    id BIGSERIAL PRIMARY KEY,
    codigo VARCHAR(255) NOT NULL,
    denominacion VARCHAR(255) NOT NULL,
    rubro_id BIGINT REFERENCES rubro(id),
    marca_id BIGINT REFERENCES marca(id),
    fechaAlta DATE NOT NULL,
    fechaBaja DATE,
    fechaModificacion DATE NOT NULL,
    usuarioBaja_id BIGINT REFERENCES usuario(id),
    usuarioCarga_id BIGINT NOT NULL REFERENCES usuario(id),
    usuarioModificacion_id BIGINT NOT NULL REFERENCES usuario(id)
);

CREATE TABLE lista_precio (
    id BIGSERIAL PRIMARY KEY,
    codigo VARCHAR(255) NOT NULL,
    denominacion VARCHAR(255) NOT NULL,
    fechaAlta DATE NOT NULL,
    fechaBaja DATE,
    fechaModificacion DATE NOT NULL,
    usuarioBaja_id BIGINT REFERENCES usuario(id),
    usuarioCarga_id BIGINT NOT NULL REFERENCES usuario(id),
    usuarioModificacion_id BIGINT NOT NULL REFERENCES usuario(id)
);

CREATE TABLE lista_precio_articulo (
    id BIGSERIAL PRIMARY KEY,
    listaPrecio_id BIGINT NOT NULL REFERENCES lista_precio(id),
    precioVenta DOUBLE PRECISION NOT NULL,
    articulo_id BIGINT NOT NULL REFERENCES articulo(id),
    fechaAlta DATE NOT NULL,
    fechaBaja DATE,
    fechaModificacion DATE NOT NULL,
    usuarioBaja_id BIGINT REFERENCES usuario(id),
    usuarioCarga_id BIGINT NOT NULL REFERENCES usuario(id),
    usuarioModificacion_id BIGINT NOT NULL REFERENCES usuario(id)
);

CREATE TABLE contacto (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255),
    telefono VARCHAR(255),
    celular VARCHAR(255)
);

CREATE TABLE domicilio (
    id BIGSERIAL PRIMARY KEY,
    nombreCalle VARCHAR(255),
    numeroCalle VARCHAR(255)
);

CREATE TABLE cliente (
    id BIGSERIAL PRIMARY KEY,
    cuitCuil VARCHAR(255) NOT NULL,
    denominacion VARCHAR(255) NOT NULL,
    contacto_id BIGINT NOT NULL REFERENCES contacto(id),
    domicilio_id BIGINT NOT NULL REFERENCES domicilio(id),
    fechaAlta DATE NOT NULL,
    fechaBaja DATE,
    fechaModificacion DATE NOT NULL,
    usuarioBaja_id BIGINT REFERENCES usuario(id),
    usuarioCarga_id BIGINT NOT NULL REFERENCES usuario(id),
    usuarioModificacion_id BIGINT NOT NULL REFERENCES usuario(id)
);

CREATE TABLE factura_venta (
    id BIGSERIAL PRIMARY KEY,
    numero BIGINT,
    fechaEmision DATE NOT NULL,
    cliente_id BIGINT REFERENCES cliente(id),
    condicionIva_id BIGINT NOT NULL REFERENCES condicion_iva(id),
    tipoMoneda_id BIGINT NOT NULL REFERENCES tipo_moneda(id),
    puntoVenta_id BIGINT NOT NULL REFERENCES punto_venta(id),
    importeCobrado DOUBLE PRECISION,
    importeSaldo DOUBLE PRECISION,
    importeTotal DOUBLE PRECISION NOT NULL,
    cae VARCHAR(255),
    caeFechaVencimiento DATE,
    resultadoAfip VARCHAR(255),
    motivoRechazo VARCHAR(255),
    estado VARCHAR(255) NOT NULL,
    fechaAnulacion DATE,
    observaciones VARCHAR(255),
    fechaAlta DATE NOT NULL,
    fechaBaja DATE,
    fechaModificacion DATE NOT NULL,
    usuarioBaja_id BIGINT REFERENCES usuario(id),
    usuarioCarga_id BIGINT NOT NULL REFERENCES usuario(id),
    usuarioModificacion_id BIGINT NOT NULL REFERENCES usuario(id)
);

CREATE TABLE factura_venta_detalle (
    id BIGSERIAL PRIMARY KEY,
    factura_id BIGINT NOT NULL REFERENCES factura_venta(id) ON DELETE CASCADE,
    listaPrecioArticulo_id BIGINT NOT NULL REFERENCES lista_precio_articulo(id),
    descripcion VARCHAR(255),
    cantidad DOUBLE PRECISION NOT NULL,
    precioUnitario DOUBLE PRECISION NOT NULL,
    porcentajeBonificacion DOUBLE PRECISION,
    importeNeto DOUBLE PRECISION,
    importeIva DOUBLE PRECISION,
    importeSubtotal DOUBLE PRECISION NOT NULL
);
