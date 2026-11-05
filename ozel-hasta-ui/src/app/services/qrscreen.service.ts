import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { MessageService } from "primeng/api";
import { Observable } from "rxjs";
import { catchError } from "rxjs/operators";
import { throwError } from "rxjs/internal/observable/throwError";
import { HastalikBilgiResponse } from "../models/hastalik-bilgi-response.model";

@Injectable({providedIn: 'root'})
export class QrscreenService {

    baseUrl = 'http://localhost:8080/ozel-hasta/api/publics/';

    constructor(private http: HttpClient,
                private message: MessageService) {
    }

    hastahastalikBilgiGetir(id: string): Observable<HastalikBilgiResponse> {
        return this.http.get<HastalikBilgiResponse>(this.baseUrl + id)
            .pipe(catchError(err => this.handleError(err)));
    }

    private handleError(error: HttpErrorResponse) {
        if (error.status === 0) {
            console.error('QrscreenService:handleError', error);
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
