import { Component } from '@angular/core';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service'
import {Router} from '@angular/router';
import swal from 'sweetalert2';
import {tap} from 'rxjs/operators';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html'
})
export class ClientesComponent{

  constructor(private clienteService : ClienteService,private router : Router, private activatedRoute: ActivatedRoute) { }

  cliente : Cliente;
  clientes : Cliente[];
  paginadorPadre: any;

  ngOnInit(){
    this.activatedRoute.paramMap.subscribe(params =>{
      let pageDefaut:number = +params.get('pageId');
      if(!pageDefaut){
          pageDefaut=0;
      }
      this.clienteService.getClientes(pageDefaut).pipe(
        tap(response => {
          console.log("ClientesComponent :  tap 3");
          (response.content as Cliente[]).forEach(cliente => {
            console.log(cliente.firstName);
          })
        })
      ).subscribe(response => {
        this.clientes = (response.content as Cliente[]);
        this.paginadorPadre = response;
      });
    });
  }

  deleteContact(cliente: Cliente) : void {
    swal.fire({
      title: 'Desea Continuar?',
      text: "Este cambio no se puede deshacer !",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, Eliminarlo.'
    }).then((result) => {
      if (result.value) {
        this.clienteService.deleteContact(cliente.id).subscribe( response => {
          this.clientes = this.clientes.filter(contact => contact != cliente)
          swal.fire('Contacto eliminado', `El Contacto ${cliente.firstName} fue eliminado correctamente`, `success`)
        })
      }
    })


  }


}
