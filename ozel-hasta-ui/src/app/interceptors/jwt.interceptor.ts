import {Injectable} from '@angular/core';
import {
    HttpEvent,
    HttpInterceptor,
    HttpHandler,
    HttpRequest
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
    constructor() {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        const currentUser = JSON.parse(localStorage.getItem('ozel-hasta-currentUser'));

        if (!!currentUser) {

            req = req.clone({
                setHeaders: {
                    Authorization: `Bearer ${currentUser.token}`
                },
            });
        }

        return next.handle(req);
    }
}
