export interface Usuario {
    id: number;
    fechaCreacion: Date;
    fechaActualizacion: Date;
    nombre: string;     
    apellido: string;     
    usuario: string;
    clave: string;
    rolId: string;
    rol: string;
}