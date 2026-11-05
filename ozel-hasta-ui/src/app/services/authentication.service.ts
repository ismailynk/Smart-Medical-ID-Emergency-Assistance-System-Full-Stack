import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from "rxjs";
import { KullaniciRoleDTO } from "../models/kullanici-role.model";

@Injectable({providedIn: 'root'})
export class AuthenticationService {

    baseUrl = 'http://localhost:8080/ozel-hasta';

    constructor(private http: HttpClient, private router: Router) {
    }

    login(credentials) {
        return this.http.post<any>(this.baseUrl + '/api/authenticate', credentials);
    }

    getKullaniciKodu() {
        const userInfo = localStorage.getItem('ozel-hasta-currentUserInfo');
        if (userInfo) {
            return JSON.parse(userInfo).kullaniciKodu;
        }
        return '';
    }

    getKullaniciAdi() {
        const userInfo = localStorage.getItem('ozel-hasta-currentUserInfo');
        if (userInfo) {
            return JSON.parse(userInfo).kullaniciAdi;
        }
        return '';
    }

    getKullaniciRol(): KullaniciRoleDTO[] {
        const userInfo = localStorage.getItem('ozel-hasta-currentUserInfo');
        if (userInfo) {
            return JSON.parse(userInfo).authorities;
        }
        return null;
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('ozel-hasta-currentUser');
        localStorage.removeItem('ozel-hasta-currentUserInfo');
        this.router.navigateByUrl('/auth/login');
    }

    getUserInfo(): Observable<any> {
        return this.http.get(this.baseUrl + '/api/account');
    }

    hasAnyAuthority(authorities: string[] | string): boolean {
        if (!this.getKullaniciKodu()) {
            return false;
        }
        if (!Array.isArray(authorities)) {
            authorities = [authorities];
        }
        return this.getKullaniciRol().some((authority: KullaniciRoleDTO) => authorities.includes(authority.role));
    }
}
