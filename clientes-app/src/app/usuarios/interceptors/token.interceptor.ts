import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest
} from '@angular/common/http';

import { Observable } from 'rxjs';
import { Oauth2Service } from '../oauth2.service';

/** Pass untouched request through to the next request handler. */
@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private authService : Oauth2Service){}

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {

      let token = this.authService.token;
      //si existe token lo pasamos en las cabeceras
      if(token != null){
        const authReq = req.clone({
          headers:req.headers.set('Authorization', 'Bearer '+token)
        });
        //Esto da paso al siguiente interceptor(si existen mas)
        return next.handle(authReq);
      }

    return next.handle(req);

  }
}
