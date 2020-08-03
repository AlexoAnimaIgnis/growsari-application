import { ServerUserHttpService } from "./server-user.http.service";
import { Injectable } from "@angular/core";
import { GrowsariUser } from 'src/app/entities/model/security/growsari-user';

/**
 * @author alexander.ballester
 */
@Injectable()
export class ServerSeUserService {
    USER_KEY = "growsari_user";

    constructor(
        private serverUserHttpService:  ServerUserHttpService
    ){}


    /**
     * save user to database
     * @param name 
     * @param password 
     * @param email 
     */
    public saveUserToDatabase(name: string, password: string, email: string) {
        return this.serverUserHttpService.saveUserToDatabase(this.createSeUser(
            name, password, email
        ));
    }

    private createSeUser(name: string, password: string, email: string) : GrowsariUser {
            let growsariUser = new GrowsariUser();
            growsariUser.setName(name);
            growsariUser.setEmail(email);
            growsariUser.setPassword(password);
            return growsariUser;
    }
}