import { NgModule } from '@angular/core';
import { LoginComponent } from './login.component';
import { RouterModule, Routes } from "@angular/router";

import { RoutingConstants } from "../constants/routing.constants";


const loginRoutes: Routes = [
  {path: RoutingConstants.ROUTE_LOGIN, component: LoginComponent}
];
@NgModule({
  imports:[
      RouterModule.forChild(loginRoutes)
  ],
  exports:[
      RouterModule
  ],
  providers:[]
})
export class LoginRoutingModule { }
