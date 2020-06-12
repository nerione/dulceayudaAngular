import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest
} from '@angular/common/http';

import { Observable , throwError} from 'rxjs';
import { Oauth2Service } from '../oauth2.service';
import swal from 'sweetalert2';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';

/** Este interceptor es para validar los Http status de error del response.  */
@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService : Oauth2Service, private router : Router){}

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {

    return next.handle(req).pipe(
      catchError(e => {

        if(e.status == 401){
          //validamos si el token expiro, quiere decir que el usuario esta autenticado, por lo que procedemos a cerrar la sesion
          if(this.authService.isAuthenticated()){
            this.authService.logout();
            }
          this.router.navigate(['/login']);

          }

          //logica para usuarios no autorizados Error 403
          if(e.status==403){
            swal.fire("No permitido", "No tiene permiso para acceder al recurso","warning");
            this.router.navigate(['/clientes']);
          }
          return throwError(e);
      })
    );
  }
}
