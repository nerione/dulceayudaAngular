import {Component} from '@angular/core';
import { Oauth2Service } from '../usuarios/oauth2.service';
import {Router} from '@angular/router'
import swal from 'sweetalert2';

@Component({
  selector : 'app-header-menu',
  templateUrl: './header.component.html',
})
export class HeaderComponent{
  title : string = 'Dulce Ayuda';

  //Este objeto se usara para validar si el usuario se encuentra en sesion y cambiar el boton de inicio de sesion por el de Cerrar Sesion
  //Para produccion, el modificador de acceso de authService cambia a Public
  constructor(public authService : Oauth2Service,
              private router : Router){}

  logout():void{
    let usuarioOff : string = this.authService.usuario.username;
    this.authService.logout();
    swal.fire('Logout', `${usuarioOff} ha cerrado sesion correctamente`, 'success');
    this.router.navigate(['/login']);
  }

}
