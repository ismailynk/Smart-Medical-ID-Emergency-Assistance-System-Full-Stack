import { NgModule } from '@angular/core';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppLayoutModule } from './layout/app.layout.module';
import { HTTP_INTERCEPTORS } from "@angular/common/http";
import { JwtInterceptor } from "./interceptors/jwt.interceptor";
import { ErrorInterceptor } from "./interceptors/error.interceptor";
import { XhrInterceptor } from "./interceptors/xhr.interceptor";
import { LoadingScreenInterceptor } from "./interceptors/loading-screen.interceptor";
import { AuthGuard } from "./interceptors/authguard.interceptor";
import { ConfirmationService, MessageService } from "primeng/api";


@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        AppRoutingModule,
        AppLayoutModule
    ],
    providers: [
        {provide: LocationStrategy, useClass: HashLocationStrategy},
        {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
        {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true},
        {provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true},
        {provide: HTTP_INTERCEPTORS, useClass: LoadingScreenInterceptor, multi: true},
        AuthGuard,
        MessageService,
        ConfirmationService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
