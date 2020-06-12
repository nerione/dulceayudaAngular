import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Oauth2Service } from '../oauth2.service';

@Injectable({
  providedIn: 'root'
})

//Los guards permiten, interceptar las peticiones y hacer validaciones preio a tirar la peticion al backend. Es una capa de seguridad adicional pero del lado del front
export class AuthGuard implements CanActivate {
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      if(this.authService.isAuthenticated()){
        //valimos la expiracion del token
        if(this.isTokenExpired()){
          this.authService.logout();
          this.router.navigate(['/login'])
          return false;
        }
        return true;
      }
      this.router.navigate(['/login']);
      return false;

  }

  constructor(private authService : Oauth2Service, private router : Router){}


isTokenExpired():boolean{
  let token = this.authService.token;
  let payload = this.authService.tokenToJson(token);
  //Hora actual del servidor en segundos
  let now = new Date().getTime() / 1000;

  if(payload.exp < now){
      //token expirado
      return true;
  }
  return false;
}



}
