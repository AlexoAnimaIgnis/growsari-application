export abstract class AbstractEntity {
    id: string;
    createdAt: Date;
    updatedAt: Date;

    constructor() {}

    public getId() {
        return this.id;
    }

    public setId(id: string) {
        this.id = id;
    }

    public getCreatedAt() {
        return this.createdAt;
    }

    public setCreatedAt(date: Date) {
        this.createdAt = date;
    }

    
    public getUpdatedAt() {
        return this.updatedAt;
    }

    public setUpdatedAt(date: Date) {
        this.updatedAt = date;
    }
}