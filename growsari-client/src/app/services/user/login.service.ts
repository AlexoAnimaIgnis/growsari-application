import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { map } from 'rxjs/operators'
import { GrowsariHttpInterfaceService } from '../growsari-http-interface.service';
import { AuthenticateResponseDTO } from '../../entities/dto/security/authenticate-response';

@Injectable()
export class LoginService extends GrowsariHttpInterfaceService{
  GROWSARI_USER = "growsariUserLogin";

  constructor(protected httpClient: HttpClient) {
    super(httpClient, null);
  }

  getGrowsariUser(){
    return JSON.parse(localStorage.getItem(this.GROWSARI_USER));
  }

  setGrowsariUser(login: string){
    localStorage.setItem(this.GROWSARI_USER, JSON.stringify(login));
  }

  login(username, password): Observable<AuthenticateResponseDTO> {
    return this.httpClient.get('/growsari-application/rest/user/login',{
      headers: this.createHeader(username, password)}).pipe(
        map(result => result as AuthenticateResponseDTO)
      );
  }
}
