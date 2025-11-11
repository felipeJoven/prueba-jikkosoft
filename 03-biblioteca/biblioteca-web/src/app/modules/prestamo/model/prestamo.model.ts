export interface Prestamo {
    id: number;
    fechaCreacion: Date;
    fechaActualizacion: Date;
    fechaPrestamo: Date;
    fechaDevolucion: Date;
    devuelto: boolean;     
    libroId: number;
    libro: string;
    miembroId: number;
    miembro: string;
}