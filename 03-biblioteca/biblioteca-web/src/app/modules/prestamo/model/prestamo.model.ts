export interface Prestamo {
    id: number;
    fechaCreacion: Date;
    fechaActualizacion: Date;
    fechaPrestamo: Date;
    fechaDevolucion: Date;
    devuelto: boolean;     
    libro: string;
    libroId: number;
    miembro: string;
    miembroId: number;
}