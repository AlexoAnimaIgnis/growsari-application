import { ModifiableEntity } from "../../modifiable-entity";

export class Topic extends ModifiableEntity{
    private subject: string;
    private description: string;

    public getSubject() {
        return this.modificationId;
    }

    public setSubject(subject: string) {
        this.subject = subject;
    }

    public getDescription() {
        return this.description;
    }

    public setDescription(description: string) {
        this.description = description;
    }
}