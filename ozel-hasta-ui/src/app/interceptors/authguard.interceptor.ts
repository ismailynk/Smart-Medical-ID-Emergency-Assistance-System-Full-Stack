import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { AuthenticationService } from "../services/authentication.service";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";

interface CanActivate {
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree
}

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private authService: AuthenticationService,
                private router: Router) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const kullaniciRole = route.data.authorities;
        if (localStorage.getItem('ozel-hasta-currentUser')) {
            return this.checkLogin(kullaniciRole);
        }

        // not logged in so redirect to login page with the return url and return false
        this.router.navigate(['auth/login']);
        return false;
    }

    private checkLogin(authorities: string[] | null) {
        return this.authService.getUserInfo().pipe(
            map(account => {
                if (account) {
                    if (!authorities || authorities.length === 0 || this.authService.hasAnyAuthority(authorities)) {
                        return true;
                    }
                    this.router.navigate(['access']);
                    return false;
                }
                this.router.navigate(['auth/login']);
                return false;
            })
        );
    }
}
