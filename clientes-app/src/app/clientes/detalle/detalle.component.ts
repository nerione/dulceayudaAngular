import { Component, OnInit, Input } from '@angular/core';
import { Cliente } from '../cliente';
import {ClienteService} from '../cliente.service';
import swal from 'sweetalert2';
import { HttpEventType } from '@angular/common/http';
import {ModalService} from './modal.service';
import { Oauth2Service } from 'src/app/usuarios/oauth2.service';


@Component({
  selector: 'detalle-cliente',
  templateUrl: './detalle.component.html',
  styleUrls: ['./detalle.component.css']
})
export class DetalleComponent implements OnInit {

  @Input() cliente : Cliente;
  id : string;
  titulo : string = "Foto de Perfil";
  file : File;
  fotoSeleccionada : File ;
  progreso : number = 0;

  constructor(private clienteService : ClienteService,
              //Para ambiente Productivo cambia el modificar de acceso a Public.
              public modalService : ModalService,
              public authService : Oauth2Service) { }

  ngOnInit(){}

  subirFoto(){
    if(this.fotoSeleccionada){
      console.log("INTENTANDO SUBIR LA ESTUPIDA FOTO " + this.fotoSeleccionada.name)
      this.progreso = 0;
      this.clienteService.subirFoto(this.fotoSeleccionada, this.cliente.id)
      .subscribe(event => {
        if(event.type === HttpEventType.UploadProgress){
          this.progreso = Math.round((event.loaded/event.total)*100)
        }else if(event.type === HttpEventType.Response){
          let response : any = event.body;
          this.cliente = response.cliente as Cliente;

          this.modalService.notificarUpload.emit(this.cliente);


          swal.fire('Foto actualizada', `La foto se ha subido correctamente: ${this.cliente.file}`, 'success');
        }
        //this.cliente = cliente;

      });
    }else{
      swal.fire('Agregue una imagen', 'Elija una imagen antes de enviar', 'warning')
    }
  }

  seleccionarFoto(event){
    this.fotoSeleccionada = event.target.files[0];
    console.log("iaishdaf " + this.fotoSeleccionada)
    if(this.fotoSeleccionada.type.indexOf('image') < 0){
      swal.fire("Error de Tipo", "El archivo seleccionado no es válido ","error");
      this.fotoSeleccionada = null;
    }else{
      this.fotoSeleccionada = event.target.files[0];
      console.log("Foto seleccionada: " + this.fotoSeleccionada);
    }
  }

  cerrarModal(){
    this.modalService.cerraModal();
    this.fotoSeleccionada = null;
    this.progreso = 0;
  }


}
