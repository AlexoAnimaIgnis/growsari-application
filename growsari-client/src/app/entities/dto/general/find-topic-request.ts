
import { ModifiableEntityRequestDTO } from "../modifiable-entity-request-dto";

export class FindTopicRequestDTO extends ModifiableEntityRequestDTO {
    private subject: string;
    private description: string;

    constructor(page?: number, size?: number){
        super();
        this.page = page;
        this.size = size;
    }

    public getSubject(): string {
        return this.subject;
    }

    public getDescription(): string {
        return this.description;
    }
}