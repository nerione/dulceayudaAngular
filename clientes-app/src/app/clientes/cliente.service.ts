import { Injectable } from '@angular/core';
//import { CLIENTES } from './clientes.json';
import {Cliente} from './cliente';
import  { of , Observable, throwError} from 'rxjs';
import { HttpClient, HttpHeaders , HttpRequest, HttpEvent} from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import swal from 'sweetalert2';
import {Router} from '@angular/router'

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private urlEndPoint : string = "http://localhost:8080/api/contacts";
  private httpHeaders = new HttpHeaders(
    {'Content-Type': 'application/json'}
);

  constructor(private http : HttpClient, private router : Router) { }
  //Obtenemos la lista de todos los Contactos en BD
  getClientes(pageId : number) : Observable<any> {
    //return of (CLIENTES);
    return this.http.get(this.urlEndPoint+'/pagina/'+pageId).pipe(
      tap(( response : any) => {
        console.log("ClienteService : tap 1");
        (response.content as Cliente[]).forEach( cliente =>{
          console.log(cliente.firstName);
        })
      }),
      map( (response:any) => {
        (response.content as Cliente[]).map( cliente =>{
          cliente.firstName = cliente.firstName.toUpperCase();
          return cliente;
        });
        return response;
      }),

      tap(response => {
        console.log("ClienteService : tap 2");
        (response.content as Cliente[]).forEach( cliente =>{
          console.log(cliente.firstName);
        })
      }),

    );
  }

  //Guardamos un Contacto en BD
  create(cliente: Cliente) : Observable<Cliente>{
    return this.http.post<Cliente>(this.urlEndPoint, cliente, {headers : this.httpHeaders}).pipe(
      catchError( e => {
        if(e.status == 400){
          return throwError(e);
        }
        console.error(e)
        swal.fire('Error al guardar', e , 'error')
        return throwError(e);
      })
    )
  }

  //Obtenemos un Contacto para actualizarlo
  getCliente(id : string) : Observable<Cliente>{
    return this.http.get<Cliente>(`${this.urlEndPoint}/${id}`).pipe( catchError ( e => {
      this.router.navigate(['/clientes']);
      swal.fire('Error al editar', e.error , 'error')
      console.log(e.error);
      return throwError(e);

    })
    );
  }

  //aCTUALIZAMOS contacto
  updateContact(cliente : Cliente) : Observable<Cliente> {
    return this.http.put<Cliente>(`${this.urlEndPoint}/${cliente.id}`, cliente, {headers : this.httpHeaders}).pipe(
      catchError (e => {

        if(e.status == 400){
          throwError(e);
        }

        console.error("Error al editar el Contacto")
        swal.fire('Error al actualizar', e , 'error')
        return throwError(e);
      })
    )
  }

  //Actualizamos foto de perfil del usuario
  subirFoto(archivo : File, id) : Observable<HttpEvent<{}>>{
    let formData = new FormData();
    formData.append("archivo",archivo);
    formData.append("id", id);
    const req = new HttpRequest("POST", `${this.urlEndPoint}/upload`, formData,{
      reportProgress : true
    });
    return this.http.request(req);

  }

  //eliminamos una entidAd Contacto
  deleteContact(id : string) : Observable<Cliente>{
    return this.http.delete<Cliente>(`${this.urlEndPoint}/${id}`, {headers : this.httpHeaders}).pipe(
      catchError(e => {
        console.error("Error al intentar eliminar contacto " + e.error.mensaje)
        swal.fire('Error al borrar', e.error.mensaje , 'error')
        return throwError(e);
      })
    )
  }

}
