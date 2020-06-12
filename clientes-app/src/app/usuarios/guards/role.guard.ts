import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Oauth2Service } from '../oauth2.service';
import swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      //Primero validamos si el usuario esta authenticado.
      if(!this.authService.isAuthenticated()){
        this.router.navigate(['/login']);
        return false;
      }

    let role = next.data['role'] as string;
    console.log("GuardRol para rol: "+role);

    if(this.authService.hasRole(role)){
      return true;
    }

    swal.fire("Sin permisos","No cuenta con los permisos a este recurso","warning");
    this.router.navigate(['/clientes']);
    return false;

  }

  constructor(private authService : Oauth2Service, private router : Router){}

}
