import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';

import { LoginComponent } from './login.component';
import { LoginRoutingModule } from './login-routing.module';
import { LoginService } from '../services/user/login.service';

import { AngularMaterialComponentsModule } from "src/app/angular-material-components/angular-material-components.module";

@NgModule({
  declarations: [LoginComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    CommonModule,
    FormsModule,
    AngularMaterialComponentsModule,
    LoginRoutingModule
  ],
  exports: [LoginComponent],
  providers: [LoginService]
})
export class LoginModule { }
