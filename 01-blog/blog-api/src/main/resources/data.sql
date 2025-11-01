-- Roles
IF NOT EXISTS (SELECT 1 FROM rol WHERE rol = 'Administrador')
    INSERT INTO rol (rol, fecha_creacion, fecha_actualizacion) VALUES ('Administrador', GETDATE(), GETDATE());

IF NOT EXISTS (SELECT 1 FROM rol WHERE rol = 'Autor')
    INSERT INTO rol (rol, fecha_creacion, fecha_actualizacion) VALUES ('Autor', GETDATE(), GETDATE());

IF NOT EXISTS (SELECT 1 FROM rol WHERE rol = 'Lector')
    INSERT INTO rol (rol, fecha_creacion, fecha_actualizacion) VALUES ('Lector', GETDATE(), GETDATE());