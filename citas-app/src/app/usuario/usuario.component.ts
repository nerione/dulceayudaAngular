import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html'
})
export class UsuarioComponent implements OnInit {

  usuarios : Usuario[] =[
    {nombre:'neri', apellidoPaterno:'sasdfa', apellidoMaterno : 'asdasd', edad : 33, email : 'asfdaf', telefono : '418151515', estado : 'dasdas', municipio : 'ade'},
    {nombre:'neri2', apellidoPaterno:'sasdfa2', apellidoMaterno : 'asdasd2', edad : 332, email : 'asfdaf2', telefono : '4181515152', estado : 'dasdas2', municipio : 'ade2'}
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
