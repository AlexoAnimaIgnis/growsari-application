import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { GrowsariUserService } from "../../app/services/user/growsariuser.service";

/**
 * @author alexander.ballester
 */
@Injectable()
export class GrowsariHttpInterfaceService {
    constructor(protected httpClient: HttpClient, protected growsariUserService: GrowsariUserService){}

    /**
     * method to create HTTP Header
     * @param username 
     * @param password 
     */
    createHeader(username, password) {
        let headers = new HttpHeaders();
        headers = headers.append("Authorization", "Basic " + btoa(username + ':' + password));
        headers = headers.append("Content-type", "application/json");
        headers = headers.append("Accept","application/json");
        headers = headers.append("Accept-Language", "en-US");
        return headers;
    }


    /**
     * method to create HTTP Header for Export Log
     * @param username 
     * @param password 
     */
    createHeaderForExportLog(username, password) {
        let headers = new HttpHeaders();
        headers = headers.append("Authorization", "Basic " + btoa(username + ':' + password));
        headers = headers.append("Accept-Language", "en-US");
        return headers;
    }

    getUsername() {
        return this.growsariUserService.get().getName();
    }

    getPassword() {
        return this.growsariUserService.get().getPassword();
    }

    /**
     * uses HTTP REQUESTMETHOD.GET with no header requirement
     * @param url 
     */
    requestGetMethodNoHeader(url : string) {
        return this.httpClient.get(url);
    }

    /**
     * uses HTTP REQUESTMETHOD.GET
     * @param url 
     */
    requestGetMethod(url : string) {
        return this.httpClient.get(url , {headers: this.createHeader(this.getUsername(), this.getPassword())});
    }

    /**
     * find object using POST request
     * @param url 
     * @param body 
     */
    requestPostMethod(url : string, body: any, overrideRequestMethod: boolean) {
        if(overrideRequestMethod) {
            return this.httpClient.post(url, body, {
                headers: this.createHeader(this.getUsername(), this.getPassword()).append("X-HTTP-Method-Override","GET")
            });
        }
        return this.httpClient.post(url, body, {headers: this.createHeader(this.getUsername(), this.getPassword())});
    }

    /**
     * find object using PUT request
     * @param url 
     * @param body 
     */
    requestPutMethod(url : string, body: any) {
        return this.httpClient.put(url, body, {headers: this.createHeader(this.getUsername(), this.getPassword())});
    }

    /**
     * find object using DELETE request
     * @param url 
     * @param body 
     */
    requestDeleteMethod(url : string) {
        return this.httpClient.delete(url, {headers: this.createHeader(this.getUsername(), this.getPassword())});
    }


    /**
     * find object using GET request with query params
     * @param url 
     * @param body 
     */
    findObjectWithUrlQueryParams(url: string, body: any) {
        const option = body ? {
            headers: this.createHeader(this.getUsername(), this.getPassword()),
            params: { "dto" : JSON.stringify(body) }
        } : {
            headers: this.createHeader(this.getUsername(), this.getPassword())
        };
        return this.httpClient.get(url, option);
    }

    createQueryParams(body: any) : any {
        let params = new HttpParams();
        for(let key in body) {
            if(body.hasOwnProperty(key) && (typeof (body[key]) == 'object' || body[key] instanceof Array)){
                params = params.set(key, JSON.stringify(body[key]));
            } else if(body.hasOwnProperty(key)){
                params = params.set(key, body[key]);
            }
        }
        return JSON.stringify(params);
    }
}
