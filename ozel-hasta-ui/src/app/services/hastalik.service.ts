import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { MessageService } from "primeng/api";
import { HastalikDTO } from "../models/hastalik.model";
import { Observable } from "rxjs";
import { catchError } from "rxjs/operators";
import { throwError } from "rxjs/internal/observable/throwError";

@Injectable({providedIn: 'root'})
export class HastalikService {

    baseUrl = 'http://localhost:8080/ozel-hasta/api/hastalik/';

    constructor(private http: HttpClient,
                private message: MessageService) {
    }

    kaydetHastalik(hastalikDTO: HastalikDTO): Observable<{}> {
        return this.http.post(this.baseUrl, hastalikDTO)
            .pipe(catchError(err => this.handleError(err)));

    }

    guncelleHastalik(hastalikDTO: HastalikDTO): Observable<{}> {
        return this.http.put(this.baseUrl, hastalikDTO)
            .pipe(catchError(err => this.handleError(err)));

    }

    silHastalik(hastalikId: string): Observable<{}> {
        return this.http.delete(this.baseUrl + hastalikId)
            .pipe(catchError(err => this.handleError(err)));
    }

    listeleHastalik(): Observable<HastalikDTO[]> {
        return this.http.get<HastalikDTO[]>(this.baseUrl + 'listeleHastalik')
            .pipe(catchError(err => this.handleError(err)));
    }

    private handleError(error: HttpErrorResponse) {
        if (error.status === 0) {
            console.error('HastalikService:handleError', error);
        }

        this.message.add({
            key: 'global',
            severity: 'error',
            summary: 'Hata Oluştu',
            detail: error.message
        })
        return throwError(() => new Error(error.message));
    }
}
