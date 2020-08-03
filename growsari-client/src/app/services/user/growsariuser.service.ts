import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { BehaviorSubject, Observable } from "rxjs";
import { GrowsariUser } from "../../entities/model/security/growsari-user";

/**
 * @author alexander.ballester
 * service related to growsari user
 */
@Injectable()
export class GrowsariUserService {
    USER_KEY = "growsari_user";

    constructor(){
    }

    save(user: GrowsariUser){
        localStorage.setItem(this.USER_KEY, JSON.stringify(user));
    }

    isAuthenticated(): boolean {
        return JSON.parse(localStorage.getItem(this.USER_KEY)) != null;
    }

    get(): GrowsariUser {
        return JSON.parse(localStorage.getItem(this.USER_KEY));
    }

    delete() {
        localStorage.removeItem(this.USER_KEY);
    }
}