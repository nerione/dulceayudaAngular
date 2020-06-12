import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import swal from 'sweetalert2';
import {Oauth2Service} from './oauth2.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  titulo : string = "Inciar sesion";
  usuario : Usuario;

  constructor(private oauth2Service : Oauth2Service, private router : Router) {
    this.usuario = new Usuario();
  }

  //validamos que el usuario no tenga una sesion activa
  ngOnInit(): void {
    console.log("entro al oninit del login.........................................")
    console.log("Sesion activa: " + this.oauth2Service.isAuthenticated());

    if(this.oauth2Service.isAuthenticated()){
        swal.fire("Sesion activa", `${this.oauth2Service.usuario.username} ya cuentas con una sesion activa`, "info")
        this.router.navigate(['/clientes'])
    }

  }

  //Método para iniciar sesion.
  login(): void {
    console.log(this.usuario);
    if(this.usuario.username == null || this.usuario.password == null || this.usuario.password =="" || this.usuario.username == ""){
      swal.fire('Error', 'Complete todos los campos', 'error');
      return ;
    }
    this.oauth2Service.login(this.usuario).subscribe(response => {
      console.log(response);
      //atob funcion que pasa una cadena b64 a un string.
      //let payload = JSON.parse(atob((response.access_token.split(".")[1])))
      //console.log("Payload : " + payload.toString())

      //this.oauth2Service.tokenToJson(response.access_token, 1);

      //METODO PARA GUARDAR USUARIO
      this.oauth2Service.guardarUsuario(response.access_token);
      //METODO PARA GUARDAR TOKEN
      this.oauth2Service.guardarToken(response.access_token);

      //si todo sale bien, redirigimos a la pagina de incio que es clientes
      this.router.navigate(['/clientes']);
      //swal.fire("Bienvenido", `Hola nuevamente ${response.usuario}`, 'success');

      swal.fire("Bienvenido", `Hola nuevamente ${this.oauth2Service.usuario.username}`, 'success');
    //Manejamos el error en caso de que las credenciales no sean validas ERROR 400
    }, err => {

      if(err.status == 400)
        swal.fire("Crendenciales incorrectas", "Sus credenciales no son válidas. Intente nuevamente" ,"error");

    });
  }

}
