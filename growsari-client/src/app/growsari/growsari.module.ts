import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GrowsariRoutingModule } from "./growsari-routing.module";
import { GrowsariComponent } from "./growsari.component";
import { BoardComponent } from "./board/board.component";
import { AngularMaterialComponentsModule } from '../angular-material-components/angular-material-components.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserModule } from './user/user.module';
import { UserCreateComponent } from './user/user-create/user-create.component';
// import { UserComponent } from './user/user.component';


@NgModule({
  declarations: [
    GrowsariComponent,
    BoardComponent,
  ],
  imports: [
    AngularMaterialComponentsModule,
    FormsModule,
    ReactiveFormsModule,
    GrowsariRoutingModule,
    UserModule,
    CommonModule
  ]
})
export class GrowsariModule { }
