import { Component, OnInit } from '@angular/core';
import { FormBuilder } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { AuthenticationService } from "../../../services/authentication.service";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styles: [`
        i {
            opacity: 0.6;
            transition-duration: .12s;
            color: #2196F3;

            &:hover {
                opacity: 1;
            }
        }
    `]
})
export class LoginComponent implements OnInit {

    constructor(private router: Router,
                public formBuilder: FormBuilder,
                private activatedRoute: ActivatedRoute,
                private authService: AuthenticationService,
    ) {
    }

    ngOnInit(): void {
        if (localStorage.getItem('currentUserInfo')) {
            this.router.navigateByUrl('/');
        }
    }

    login(userName: any, passWord: any) {
        const credentials = {
            username: userName,
            password: passWord
        };
        this.authService.login(credentials).subscribe(user => {
            if (user && user.token) {
                localStorage.setItem('ozel-hasta-currentUser', JSON.stringify(user));
                this.authService.getUserInfo()
                    .subscribe(userInfo => {
                        localStorage.setItem('ozel-hasta-currentUserInfo', JSON.stringify(userInfo));
                        this.router.navigate(['/']);
                        return user;
                    })
            }
        })
    }
}
