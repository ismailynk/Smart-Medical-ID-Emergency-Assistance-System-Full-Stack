import { Component } from '@angular/core';
import { RegisterService } from "../../../services/register.service";
import { Registration } from "./register.model";
import { Router } from "@angular/router";
import { MessageService } from "primeng/api";

@Component({
    templateUrl: './register.component.html'
})
export class RegisterComponent {

    confirmed: boolean = false;
    registration = new Registration();

    constructor(private registerService: RegisterService,
                private router: Router,
                private message: MessageService) {
    }

    register(): void {
        this.registerService
            .save(this.registration)
            .subscribe({
                next: () => {
                    this.router.navigateByUrl('/auth/login');
                }, error: err => {
                    this.message.add({
                        severity: 'error',
                        summary: 'Hata Oluştu',
                        detail: err.message
                    })
                }
            });
    }
}
