import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-directiva',
  templateUrl: './directiva.component.html'
})
export class DirectivaComponent{

  listaProximasCitas : string[] = ['121 Octubre', '10 Noviembre','15 Diciembre'];
  habilitar : boolean = true;
  constructor() { }

  setHabilitar() : void{
    this.habilitar = (this.habilitar == true ? false : true);
  }

}
