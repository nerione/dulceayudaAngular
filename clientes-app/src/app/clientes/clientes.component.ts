import { Component } from '@angular/core';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service'
import {Router} from '@angular/router';
import swal from 'sweetalert2';
import {tap} from 'rxjs/operators';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html'
})
export class ClientesComponent{

  constructor(private clienteService : ClienteService,private router : Router) { }

  cliente : Cliente;
  clientes : Cliente[];

  ngOnInit(){
    this.clienteService.getClientes().pipe(
      tap(clientes => {
        this.clientes = clientes;
        console.log("ClientesComponent : tap 3")
        clientes.forEach(cliente => {
          console.log(cliente.firstName);
        })
      })
    ).subscribe();
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
