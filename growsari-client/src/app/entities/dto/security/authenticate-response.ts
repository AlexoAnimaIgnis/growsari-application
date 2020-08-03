export class AuthenticateResponseDTO {
    login: string;
    id: string;
    description: string;
    authorities: Array<string>;
    activities: Array<string>;

    constructor(login: string, description: string, authorities: Array<string>, activities: Array<string>, id: string){
        this.login = login;
        this.description = description;
        this.authorities = authorities;
        this.activities = activities;
        this.id = id;
    }

    public setLogin(login : string){
        this.login = login;
    }

    public setDescription(description : string){
        this.description = description;
    }

    public setAuthorities(authorities: Array<string>) {
        this.authorities = authorities;
    }

    public setActivities(activities: Array<string>) {
        this.activities = activities;
    }

    public setId(id : string){
        this.id = id;
    }

}
