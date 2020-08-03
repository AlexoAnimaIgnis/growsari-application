import { Message } from "../../model/general/message";

export class MessageResponseDTO {
    result: Message[];
    totalRecords: number;
}