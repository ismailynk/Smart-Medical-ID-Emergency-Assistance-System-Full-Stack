import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from "../services/authentication.service";
import { Authority } from "../models/authority.constants";

@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {

    model: any[] = [];

    constructor(private authService: AuthenticationService) {
    }

    ngOnInit() {
        this.model = [];
        if (this.authService.hasAnyAuthority(Authority.ADMIN)) {
            this.model.push({
                label: 'Tanımlar',
                icon: 'pi pi-book',
                items: [
                    {
                        label: 'Hastalık Tanımlama',
                        icon: 'pi pi-fw pi-briefcase',
                        routerLink: ['/tanimlar/hastalik']
                    }
                ]
            });
        }
        if (this.authService.hasAnyAuthority(Authority.USER)) {
            this.model.push({
                label: 'İşlemler',
                icon: 'pi pi-user-edit',
                items: [
                    {
                        label: 'Hasta İşlemleri',
                        icon: 'pi pi-fw pi-user-edit',
                        routerLink: ['/islemler/hasta']
                    }
                ]
            });
        }
    }
}
