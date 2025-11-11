export interface Miembro {
    id: number;
    fechaCreacion: Date;
    fechaActualizacion: Date;
    nombre: string;     
    apellido: string;
    numeroIdentificacion: string;
    telefono: string;
    activo: boolean;
    tipoIdentificacionId: number;
    tipoIdentificacion: string;
    bibliotecaId: number;
    biblioteca: string;
}