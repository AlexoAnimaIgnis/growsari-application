import { GrowsariHttpInterfaceService } from "../growsari-http-interface.service";

import { Injectable } from "@angular/core";
import { GrowsariUser } from 'src/app/entities/model/security/growsari-user';

/**
 * @author alexander.ballester
 */
@Injectable()
export class ServerUserHttpService extends GrowsariHttpInterfaceService {

    /**
     * save user to database
     */
    public saveUserToDatabase(growsariUser: GrowsariUser) {
        return this.requestPostMethod('/growsari-application/rest/register', growsariUser, false);
    }
}
