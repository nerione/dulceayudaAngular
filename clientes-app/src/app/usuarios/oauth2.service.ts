import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders , HttpRequest, HttpEvent} from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import {Usuario} from './usuario';
import { URL_BACKEND } from '../config/config';

@Injectable({
  providedIn: 'root'
})
export class Oauth2Service {

  //Crendiales del front convertidas a B64
  private credenciales = btoa('angularApp'+':'+'123456789');
  private urlEndPoint : string = URL_BACKEND+"/oauth/token";
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/x-www-form-urlencoded',
                                          'Authorization' : 'Basic '+ this.credenciales });

  private _usuario : Usuario;
  private _token : string;

  constructor(private http : HttpClient, private router : Router) { }

  //Metodos getters para extraer la info del usuario
  public get usuario():Usuario{
    if(this._usuario != null){
      return this._usuario;
    }else if(sessionStorage.getItem('usuario') != null){
      this._usuario = JSON.parse(sessionStorage.getItem('usuario')) as Usuario;
      return this._usuario;
    }else{
      //En el caso de que el objeto no exista, regresamos una instancia de Usuario pero vacio
      return new Usuario();
    }
  }

  //Metodos getters para recuperar el token
  public get token():string{

    if(this._token != null){
      return this._token;
    }else if(sessionStorage.getItem('token') != null){
      this._token = sessionStorage.getItem('token');
      return this._token;
    }else{
      //En el caso de que el objeto no exista, regresamos una instancia de Usuario pero vacio
      return null;
    }

  }

  //Metodo para recuperrar un token.
  login(usuario : Usuario):Observable<any>{
    let params = new URLSearchParams();
        params.set('username',usuario.username);
        params.set('password',usuario.password);
        params.set('grant_type','password');
        console.log(params.toString());
    return this.http.post(this.urlEndPoint, params.toString(), {headers: this.httpHeaders});

  }

  //Metodo para almacenar usuario en sesion
  guardarUsuario(access_token : string) : void{
    let payload = this.tokenToJson(access_token);
    this._usuario = new Usuario();
    this._usuario.username = payload.user_name;
    this._usuario.roles = payload.authorities;
    sessionStorage.setItem("usuario", JSON.stringify(this._usuario));

  }

  //Metodo para salvar el token del usuario
  guardarToken(access_token : string) : void{
    this._token = access_token;
    sessionStorage.setItem("token" , this._token)
  }
  //Parametros token y posicion del token deseada
  tokenToJson(token : string) : any {
    if(token != null)
      return JSON.parse(atob(token.split(".")[1]))
    return null;
  }


  //METODO PARA SABER SI EL USUARIO YA TIENE UNA SESION ABIERTA PARA NO PERMITIRLE IR AL LOGIN.
  isAuthenticated(): boolean{
    let payload = this.tokenToJson(this.token);
    if(payload != null && payload.user_name && payload.user_name.length > 0){
        return true;
    }
    return false;
  }

  //Metodo para validaer los roles del usuario authenticado
  hasRole(role:string):boolean{
    if(this.usuario.roles.includes(role) ){
      return true;
    }
    return false;
  }

  //funcion que limpia los datos de la sesion para realizar el logout
  logout() :void{
    this._token = null;
    this._usuario = null;
    sessionStorage.clear();
    sessionStorage.removeItem('usuario');
    sessionStorage.removeItem('token');

  }

}
