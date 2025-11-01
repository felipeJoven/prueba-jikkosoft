export interface Usuario {
    id: number;
    fechaCreacion: Date;
    fechaActualizacion: Date;
    nombre: string;     
    usuario: string;
    clave: string;
    rol: string;
    rolId: string;
}