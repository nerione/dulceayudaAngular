import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {HeaderComponent} from './header/header.component';
import {FooterComponent} from './footer/footer.component';
import { DirectivaComponent } from './directiva/directiva.component';
import { UsuarioComponent } from './usuario/usuario.component';
import { RegistroComponent } from './registro/registro.component';
import { FunnelComponent } from './funnel/funnel.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    DirectivaComponent,
    UsuarioComponent,
    RegistroComponent,
    FunnelComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
