
import { ModifiableEntityRequestDTO } from "../modifiable-entity-request-dto";

export class FindMessageRequestDTO extends ModifiableEntityRequestDTO {
    private topicId : string;
    private message: string;

    constructor(page?: number, size?: number){
        super();
        this.page = page;
        this.size = size;
    }

    public getTopicId(): string {
        return this.topicId;
    }

    public getMessage(): string {
        return this.message;
    }
}