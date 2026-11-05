import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { MessageService } from "primeng/api";
import { HastalikDTO } from "../models/hastalik.model";
import { HastaDTO } from "../models/hasta.model";
import { Observable } from "rxjs";
import { catchError } from "rxjs/operators";
import { throwError } from "rxjs/internal/observable/throwError";
import { HastaHastalikDTO } from "../models/hasta-hastalik.model";

@Injectable({providedIn: 'root'})
export class HastaHastalikService {

    baseUrl = 'http://localhost:8080/ozel-hasta/api/hasta-hastalik/';

    constructor(private http: HttpClient,
                private message: MessageService) {
    }

    kaydetHastaHastalik(hastaHastalikDTO: HastaHastalikDTO): Observable<{}> {
        return this.http.post(this.baseUrl, hastaHastalikDTO)
            .pipe(catchError(err => this.handleError(err)));

    }

    listeleHastaHastalik(hastaId: string): Observable<HastaHastalikDTO[]> {
        return this.http.get<HastaHastalikDTO[]>(this.baseUrl + 'listeleHastaHastalik/' + hastaId)
            .pipe(catchError(err => this.handleError(err)));

    }

    silHastaHastalik(hastaHastalikId: string): Observable<{}> {
        return this.http.delete(this.baseUrl + hastaHastalikId)
            .pipe(catchError(err => this.handleError(err)));

    }

    private handleError(error: HttpErrorResponse) {
        if (error.status === 0) {
            console.error('HastaService:handleError', error);
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
