import { Component, OnInit } from '@angular/core';
import { ClienteService } from './cliente.service';
import { Cliente } from './cliente';
import {Router, ActivatedRoute} from '@angular/router';
import swal from 'sweetalert2';


@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {

cliente : Cliente = new Cliente()
titulo : string = "Crear nuevo contacto";
errores : string[];

  constructor(private clienteService : ClienteService, private router : Router,
              private activatedRoute : ActivatedRoute) { }

  ngOnInit(){
    this.cargarCliente()
  }

  cargarCliente() : void {
    this.activatedRoute.params.subscribe(params =>{
      let id = params['id']
      console.log("ID recuperado  :" + id)
      if(id){
        this.clienteService.getCliente(id).subscribe( (cliente) => this.cliente = cliente )
      }
    })
  }

  create() : void{
    this.clienteService.create(this.cliente).subscribe(
      //manejando el response retornado desde el ClienteService
      cliente => {
      this.router.navigate(['/clientes'])
      swal.fire('Contacto ', `Contact ${cliente.firstName} creado con éxito !` , `success`)
    }, //manejando el error retornado desde el ClienteService
      err => {
        this.errores = err.error.errores as string[];
        console.error(err.error.errores);
        console.error("Codigo error response: " + err.status);
      }


    );
  }

  actualizarContacto() : void {
    this.clienteService.updateContact(this.cliente).subscribe( cliente => {
      this.router.navigate(['/clientes'])
      swal.fire('Contacto Actualizado', `El Contacto ${cliente.firstName} fue actualizado con éxito!`, `success`)
    })
  }


}
