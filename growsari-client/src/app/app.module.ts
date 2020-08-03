import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { GrowsariModule } from 'src/app/growsari/growsari.module'
import { LoginModule } from 'src/app/login/login.module'
import { AppRoutingModule } from "src/app/app-routing.module";


import { MainAuthGuardService } from "src/app/services/main-auth-guard.service";
import { ServerUserHttpService } from "src/app/services/user/server-user.http.service";
import { ServerSeUserService } from "src/app/services/user/server-seuser.service";
import { GrowsariUserService } from "src/app/services/user/growsariuser.service";

import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    GrowsariModule,
    LoginModule,
    AppRoutingModule,
  ],
  providers: [MainAuthGuardService, ServerUserHttpService, ServerSeUserService, GrowsariUserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
