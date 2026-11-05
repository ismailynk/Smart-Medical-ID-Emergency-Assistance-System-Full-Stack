import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Registration } from '../views/auth/register/register.model';
import { catchError } from "rxjs/operators";
import { MessageService } from "primeng/api";
import { throwError } from "rxjs/internal/observable/throwError";

@Injectable({providedIn: 'root'})
export class RegisterService {
    baseUrl = 'http://localhost:8080/ozel-hasta/api/register';

    constructor(private http: HttpClient,
                private message: MessageService) {
    }

    save(registration: Registration): Observable<{}> {
        return this.http.post(this.baseUrl, registration);
    }
}
