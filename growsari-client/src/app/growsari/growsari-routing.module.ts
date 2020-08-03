import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { CommonModule } from "@angular/common";
import { MainAuthGuardService } from "../services/main-auth-guard.service";
import { GrowsariComponent } from './growsari.component';
import { BoardComponent } from './board/board.component';
import { UserModule } from "./user/user.module";


const growsariRoutes: Routes = [
    {
        path: 'view',
        component: GrowsariComponent,
        canActivate:[MainAuthGuardService],
        children:[
            {path:'board', component:BoardComponent},
            {path:'usermgmt', loadChildren:'./user/user.module#UserModule'},
        ]        
    }
]
@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild(growsariRoutes)
    ],
    exports:[
        RouterModule
    ]
})
export class GrowsariRoutingModule{}