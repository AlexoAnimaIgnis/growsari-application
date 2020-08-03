import { ModifiableEntity } from "../../modifiable-entity";
import { Topic } from "./topic";

export class Message extends ModifiableEntity {
    private topic : Topic;
    private message: string;

    public getTopic() {
        return this.topic;
    }

    public setTopic(topic: Topic) {
        this.topic = topic;
    }

    public getMessage() {
        return this.message;
    }

    public setMessage(message: string) {
        this.message = message;
    }
}