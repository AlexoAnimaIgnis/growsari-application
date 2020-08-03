import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/user/login.service';
import { GrowsariUserService } from '../services/user/growsariuser.service';
import { GrowsariUser } from '../entities/model/security/growsari-user';
import { Router } from '@angular/router';
import { HttpResponse } from '@angular/common/http';

import { RoutingConstants } from '../constants/routing.constants';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [LoginService, GrowsariUserService ]
})
export class LoginComponent implements OnInit {
  growsariUser = new GrowsariUser();

  isLogging = false;
  isLoginFailedMessage = '';
  loginKey = 'login';

  constructor(private loginService: LoginService,
    private growsariUserService: GrowsariUserService,
    private router : Router) { }

  ngOnInit(): void {
  }

  login(){
    if(this.growsariUser.getName() === '' || this.growsariUser.getPassword() === ''){
      this.isLoginFailedMessage = 'Please fill username and password';
      return;
    }

    this.isLogging = true;
    this.isLoginFailedMessage = '';
    this.authenticateUser();
  }

  authenticateUser(){
    this.router.navigate([RoutingConstants.getRoute(RoutingConstants.ROUTE_DASHBOARD)]);
    this.loginService.login(this.growsariUser.getName(), this.growsariUser.getPassword()).subscribe(
      response => {
        this.isLogging = false;
        this.growsariUser.setId(response.id);
        this.growsariUserService.save(this.growsariUser);
        this.router.navigate([RoutingConstants.getRoute(RoutingConstants.ROUTE_DASHBOARD)]);
      },
      (err: HttpResponse<any> )=> {
        this.isLogging = false;
        this.isLoginFailedMessage = err.headers.get("User-Authentication-Failed");
      }
    );
  }
}
