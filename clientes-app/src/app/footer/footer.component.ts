import { Component } from '@angular/core';

@Component({
  selector : 'app-footer',
  templateUrl : './footer.component.html',
  styleUrls : ['./footer.component.css']
})
export class FooterComponent {

  author : any = {nombre: 'Dulce Ayuda™', lema: 'Todos los derechos reservados'};

}
