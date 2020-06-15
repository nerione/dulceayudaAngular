import { Component } from '@angular/core';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service'
import {Router} from '@angular/router';
import swal from 'sweetalert2';
import {tap} from 'rxjs/operators';
import {ActivatedRoute} from '@angular/router';
import {ModalService} from './detalle/modal.service';
import { Oauth2Service } from '../usuarios/oauth2.service';
import { URL_BACKEND } from '../config/config';


@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html'
})
export class ClientesComponent{

  constructor(private clienteService : ClienteService,
              private router : Router,
              private activatedRoute: ActivatedRoute,
              //PARA EL CASO DE ESTOS 2 ATRIBUTOS ABAJO, PARA AMBIENTES PRODUCTIVOS DEBEN SER PUBLIC DADO QUE SE ACCEDEN DESDE FUERA DE LA ESTA CLASE. Los demas quedan private porque son de esta clase.
              public modalService : ModalService,
              public authService : Oauth2Service) { }

  cliente : Cliente;
  clientes : Cliente[];
  paginadorPadre: any;
  clienteSeleccionado : Cliente;
  urlBackend : string =URL_BACKEND;

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

    this.modalService.notificarUpload.subscribe(cliente => {
      this.clientes = this.clientes.map( clienteOriginal => {
        if(clienteOriginal.id === cliente.id){
          clienteOriginal.file = cliente.file;
        }
        return clienteOriginal;
      })
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


  //inyectamos un cliente al atributo de una clase hijo
  abrirModal(cliente : Cliente){
    console.log("ABRETE MIERAD")
    this.clienteSeleccionado = cliente;
    this.modalService.abrirModal();
  }

}
