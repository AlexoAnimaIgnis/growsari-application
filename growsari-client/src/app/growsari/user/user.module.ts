import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserCreateComponent } from './user-create/user-create.component';
import { UserRoutingModule } from './user-routing.module';
import { AngularMaterialComponentsModule } from "../../angular-material-components/angular-material-components.module";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [UserCreateComponent],
  imports: [
    AngularMaterialComponentsModule,
    FormsModule,
    ReactiveFormsModule,
    UserRoutingModule,
    CommonModule
  ]
})
export class UserModule { }
