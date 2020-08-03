import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { RoutingConstants } from './constants/routing.constants';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { MainAuthGuardService } from './services/main-auth-guard.service';
import { GrowsariUserService } from './services/user/growsariuser.service';

const appRoutes: Routes = [
  { path: '', redirectTo: RoutingConstants.ROUTE_LOGIN, pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
]
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(appRoutes, {
      useHash: true
    })
  ],
  exports: [
    RouterModule
  ],
  providers: [
    MainAuthGuardService,
    GrowsariUserService
  ],
  declarations: []
})
export class AppRoutingModule { }
