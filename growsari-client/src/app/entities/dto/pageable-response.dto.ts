export class PageableResponseDTO {
    totalRecords: number;
    result: [];

    constructor(totalRecords: number, result: []){
        this.totalRecords = totalRecords;
        this.result = result;
    }
}