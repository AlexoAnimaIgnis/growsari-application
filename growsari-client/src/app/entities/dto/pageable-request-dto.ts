export class PageableRequestDTO {
    page: number;
    size: number;
    // sortDirection: Direction;
    sortProperty: string;

    constructor()
    
    constructor(page?: number, size?: number){
        this.page = page;
        this.size = size;
    }

    public getPage() {
        return this.page;
    }

    public setPage(page : number) {
        this.page = page;
    }

    public getSize() {
        return this.size;
    }

    public setSize(size : number) {
        this.size = size;
    }

    public getSortProperty() {
        return this.sortProperty;
    }

    public setSortProperty(sortProperty : string) {
        this.sortProperty = sortProperty;
    }
}
