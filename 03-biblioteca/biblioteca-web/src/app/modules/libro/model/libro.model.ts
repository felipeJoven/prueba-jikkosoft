export interface Libro {
    id: number;
    fechaCreacion: Date;
    fechaActualizacion: Date;
    titulo: string;     
    isbn: string;
    activo: boolean;
    autorId: number;
    nombreAutor: string;
}