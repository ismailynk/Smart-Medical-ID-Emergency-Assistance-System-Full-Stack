import { NgModule } from '@angular/core';
import { ExtraOptions, RouterModule, Routes } from '@angular/router';
import { AppLayoutComponent } from './layout/app.layout.component';
import { AuthGuard } from "./interceptors/authguard.interceptor";
import { Authority } from "./models/authority.constants";

const routerOptions: ExtraOptions = {
    anchorScrolling: 'enabled'
};

const routes: Routes = [
    {
        path: '', component: AppLayoutComponent,
        canActivate: [AuthGuard],
        children: [
            {
                path: 'tanimlar',
                data: {authorities: [Authority.ADMIN]},
                canActivate: [AuthGuard],
                loadChildren: () => import('./views/tanimlar/tanimlar.module').then(m => m.TanimlarModule)
            },
            {
                path: 'islemler',
                data: {authorities: [Authority.USER]},
                canActivate: [AuthGuard],
                loadChildren: () => import('./views/islemler/islemler.module').then(m => m.IslemlerModule)
            },
        ]
    },
    {
        path: 'auth',
        data: {breadcrumb: 'Auth'},
        loadChildren: () => import('./views/auth/auth.module').then(m => m.AuthModule)
    },
    {
        path: 'landing',
        loadChildren: () => import('./demo/components/landing/landing.module').then(m => m.LandingModule)
    },
    {
        path: 'notfound',
        loadChildren: () => import('./demo/components/notfound/notfound.module').then(m => m.NotfoundModule)
    },
    {
        path: 'access',
        loadChildren: () => import('./views/auth/accessdenied/accessdenied.module').then(m => m.AccessdeniedModule)
    },
    {
        path: 'hasta-bilgi',
        loadChildren: () => import('./views/qrscreen/qrscreen/qrscreen.module').then(m => m.QrscreenModule)
    },
    {path: '**', redirectTo: '/notfound'}
];

@NgModule({
    imports: [RouterModule.forRoot(routes, routerOptions)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
