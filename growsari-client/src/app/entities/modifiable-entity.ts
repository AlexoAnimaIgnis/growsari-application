import { AbstractEntity } from "./abstract-entity";

export abstract class ModifiableEntity extends AbstractEntity {
    modificationId: string;
    createdBy: string;
    updatedBy: string;
    deletedAt: Date;

    constructor(){
        super();
    }

    public getModificationId() {
        return this.modificationId;
    }

    public setModificationId(id: string) {
        this.id = id;
    }

    public getCreatedBy() {
        return this.createdBy;
    }

    public setCreatedBy(createdBy : string) {
        this.createdBy = createdBy;
    }

    public getUpdatedBy() {
        return this.updatedBy;
    }

    public setUpdatedBy(updatedBy : string) {
        this.updatedBy = updatedBy;
    }

    public getDeletedAt() {
        return this.deletedAt;
    }

    public setDeletedAt(date: Date) {
        this.deletedAt = date;
    }

}