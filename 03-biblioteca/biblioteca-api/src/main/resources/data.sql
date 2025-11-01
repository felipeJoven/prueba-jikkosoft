-- Roles
IF NOT EXISTS (SELECT 1 FROM rol WHERE rol = 'Administrador')
    INSERT INTO rol (rol, fecha_creacion, fecha_actualizacion) VALUES ('Administrador', GETDATE(), GETDATE());

IF NOT EXISTS (SELECT 1 FROM rol WHERE rol = 'Usuario')
    INSERT INTO rol (rol, fecha_creacion, fecha_actualizacion) VALUES ('Usuario', GETDATE(), GETDATE());

--Tipo de identificación
IF NOT EXISTS (SELECT 1 FROM tipo_identificacion WHERE tipo_identificacion = 'Cédula de ciudadanía')
    INSERT INTO tipo_identificacion (tipo_identificacion) VALUES ('Cédula de ciudadanía');

IF NOT EXISTS (SELECT 1 FROM tipo_identificacion WHERE tipo_identificacion = 'Tarjeta de identidad')
    INSERT INTO tipo_identificacion (tipo_identificacion) VALUES ('Tarjeta de identidad');

IF NOT EXISTS (SELECT 1 FROM tipo_identificacion WHERE tipo_identificacion = 'NUIP')
    INSERT INTO tipo_identificacion (tipo_identificacion) VALUES ('NUIP');

IF NOT EXISTS (SELECT 1 FROM tipo_identificacion WHERE tipo_identificacion = 'Pasaporte')
    INSERT INTO tipo_identificacion (tipo_identificacion) VALUES ('Pasaporte');

