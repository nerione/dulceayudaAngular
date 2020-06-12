import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { DirectivaComponent } from './directiva/directiva.component';
import { ClientesComponent } from './clientes/clientes.component';
import { ClienteService } from './clientes/cliente.service';
import {RouterModule, Routes } from "@angular/router";
import { FormComponent } from './clientes/form.component';
import { FormsModule } from '@angular/forms';
import { PaginatorComponent } from './paginator/paginator.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {MatDatepickerModule} from '@angular/material/datepicker';
import { DetalleComponent } from './clientes/detalle/detalle.component';
import { LoginComponent } from './usuarios/login.component';
import { AuthGuard } from './usuarios/guards/auth.guard';
import { RoleGuard } from './usuarios/guards/role.guard';
import { TokenInterceptor } from './usuarios/interceptors/token.interceptor';
import { AuthInterceptor } from './usuarios/interceptors/auth.interceptor';


const routes : Routes = [
  {path:'', redirectTo:'/clientes', pathMatch : 'full'},
  {path:'directivas', component: DirectivaComponent},
  {path:'clientes', component:ClientesComponent},
  {path:'clientes/pagina/:pageId', component:ClientesComponent},
  //Se agrega el interceptor GUARD al formulario para validar que exista una sesion. El otro es para validar por roles. El data es un parametro que se le pasa al guard
  {path:'clientes/form', component:FormComponent, canActivate:[AuthGuard, RoleGuard], data: {role:"ROLE_ADMIN"}},
  {path:'clientes/form/:id', component:FormComponent , canActivate:[AuthGuard, RoleGuard], data: {role:"ROLE_ADMIN"}},
  {path:'login', component:LoginComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    DirectivaComponent,
    ClientesComponent,
    FormComponent,
    PaginatorComponent,
    DetalleComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes),
    BrowserAnimationsModule,
    MatDatepickerModule
  ],
  //En esta seccion se registran los interceptores
  providers: [
    ClienteService,
    {provide: HTTP_INTERCEPTORS, useClass : TokenInterceptor, multi : true},
    {provide: HTTP_INTERCEPTORS, useClass : AuthInterceptor, multi : true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
