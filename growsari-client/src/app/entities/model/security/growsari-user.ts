import { AbstractEntity } from "../../abstract-entity";

export class GrowsariUser extends AbstractEntity {
    private name: string;
    private password: string;
    private email: string;
    
    public getName(): string {
        return this.name;
    }
    public setName(value: string) {
        this.name = value;
    }

    public getPassword(): string {
        return this.password;
    }

    public setPassword(value: string) {
        this.password = value;
    }

    public getEmail(): string {
        return this.email;
    }
    
    public setEmail(value: string) {
        this.email = value;
    }
}