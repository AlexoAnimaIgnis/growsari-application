import { PageableRequestDTO } from "./pageable-request-dto";

export class ModifiableEntityRequestDTO extends PageableRequestDTO {
    userName: String;
    password: String;

    constructor()
    constructor(page?: number, size?: number){
        super();
        this.page = page;
        this.size = size;
    }
   
    public getUsername(): String{
        return this.userName;
    }

    public setUsername(userName: String){
        this.userName = userName;
    }

       
    public getPassword(): String{
        return this.password;
    }

    public setPassword(password: String){
        this.password = password;
    }
}
