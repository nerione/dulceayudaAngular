import { Injectable } from '@angular/core';
//import { CLIENTES } from './clientes.json';
import {Cliente} from './cliente';
import  { Observable, throwError} from 'rxjs';
import { HttpClient, HttpRequest, HttpEvent} from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
//import swal from 'sweetalert2';
import {Router} from '@angular/router'
import { URL_BACKEND } from '../config/config';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private urlEndPoint : string = URL_BACKEND+"/api/contacts";
  //Objeto HttpHeaders es inmutable, por lo que es necesario un metodo para agregar nuevos valores a los headers.
  //lO COMENTAMOS YA QUE LS HEADERS SON AGREGADOS AHORA POR EL TokenInterceptor
  //private httpHeaders = new HttpHeaders( {'Content-Type': 'application/json'} );

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
    return this.http.post<Cliente>(this.urlEndPoint, cliente /*, {headers : this.agregarAuthorization()}*/).pipe(
      catchError( e => {
        //Validamos que sea un usuario autorizado. Solo para usuarios con ROLE_ADMINISTRATOR
        //if(this.isNoAutorizado(e)){
          //return throwError(e);
        //}

        if(e.status == 400){
          return throwError(e);
        }

        if(e.error){
          console.error(e)
        }
        //swal.fire('Error al guardar', e , 'error')
        return throwError(e);
      })
    )
  }

  //Obtenemos un Contacto para actualizarlo
  getCliente(id : string) : Observable<Cliente>{
    return this.http.get<Cliente>(`${this.urlEndPoint}/${id}` /*, {headers : this.agregarAuthorization()}*/).pipe( catchError ( e => {
      console.log("TIRANDO PETICION GET CLIENTE POR ID");
      if(e.status != 401 && e.error){
        this.router.navigate(['/clientes']);
        console.log(e.error);
      }
      //swal.fire('Error al editar', e.error , 'error')
      if(e.error){
        console.error(e)
      }
      return throwError(e);

    })
    );
  }

  //aCTUALIZAMOS contacto
  updateContact(cliente : Cliente) : Observable<Cliente> {
    return this.http.put<Cliente>(`${this.urlEndPoint}/${cliente.id}`, cliente /*, {headers : this.agregarAuthorization()}*/).pipe(
      catchError (e => {

        //Validamos que sea un usuario autorizado
        //if(this.isNoAutorizado(e)){
          //return throwError(e);
        //}


        if(e.status == 400){
          throwError(e);
        }

        console.error("Error al editar el Contacto")
        //swal.fire('Error al actualizar', e , 'error')
        return throwError(e);
      })
    )
  }

  //Actualizamos foto de perfil del usuario
  subirFoto(archivo : File, id) : Observable<HttpEvent<{}>>{
    let formData = new FormData();
    formData.append("archivo",archivo);
    formData.append("id", id);
    //Se crea una nueva instancia del objeto HttpHeaders porque para este caso, el Content-Type no es application/json, si no formData
    //let token = this.authService.token;
    //let httpHeadersImagenes = new HttpHeaders();
    //if(token != null)
      //httpHeadersImagenes = httpHeadersImagenes.append('Authorization','Bearer '+token);

    const req = new HttpRequest("POST", `${this.urlEndPoint}/upload`, formData,{
      reportProgress : true
      //headers : httpHeadersImagenes
    });
    return this.http.request(req);
  }

  //eliminamos una entidAd Contacto
  deleteContact(id : string) : Observable<Cliente>{
    return this.http.delete<Cliente>(`${this.urlEndPoint}/${id}`/*, {headers : this.agregarAuthorization()}*/).pipe(
      catchError(e => {

        //Validamos que sea un usuario autorizado. Solo para usuarios con ROLE_ADMINISTRATOR
        //if(this.isNoAutorizado(e)){
          //return throwError(e);
        //}

        if(e.error){
          console.error("Error al intentar eliminar contacto " + e.error.mensaje)
        }
        //swal.fire('Error al borrar', e.error.mensaje , 'error')
        return throwError(e);
      })
    )
  }


  //Método para validar si el usuario tiene permiso de acceder al recurso o no.
  /*private isNoAutorizado(error) : boolean {
    //401 No autorizado //403 Prohibido

    //logica para usuarios no autenticados Error 401
    if(error.status==401){
      //validamos si el token expiro, quiere decir que el usuario esta autenticado, por lo que procedemos a cerrar la sesion
      if(this.authService.isAuthenticated()){
        console.log("SE CIERRA LA SESION POR QUE EL TOKEN EXPIRO");
        this.authService.logout();
      }

      this.router.navigate(['/login']);
      return true;
      //logica para usuarios no autorizados Error 403
    }else if(error.status==403){
      swal.fire("No permitido", "No tiene permiso para acceder al recurso","warning");
      this.router.navigate(['/clientes']);
      return true;
    }
    return false;
  }*/

  //Metodo para agregar headers al objeto HttpHeaders
  //LO DEJAMOS DESHABILITADO POR QUE FUE SUSTITUIDO POR EL TokenInterceptor
  /*private agregarAuthorization(){
    let token = this.authService.token;
    if(token != null){
      return this.httpHeaders.append("Authorization","Bearer "+token);
    }else{
      return this.httpHeaders;
    }
  }*/

}
