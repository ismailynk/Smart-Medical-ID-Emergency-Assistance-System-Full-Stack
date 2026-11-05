import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import {AppLayoutComponent} from "../../layout/app.layout.component";
import {AppFooterComponent} from "../../layout/app.footer.component";

@NgModule({
    imports: [RouterModule.forChild([
        { path: 'qrscreen',component: AppFooterComponent, data: {breadcrumb: 'QR Screen'},  loadChildren: () => import('./qrscreen/qrscreen.module').then(m => m.QrscreenModule) }
    ])],
    exports: [RouterModule]
})
export class QrscreenRoutingModule { }
