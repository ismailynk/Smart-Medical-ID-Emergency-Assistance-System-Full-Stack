import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { QrscreenComponent } from "./qrscreen.component";

@NgModule({
    imports: [RouterModule.forChild([
        {path: '', component: QrscreenComponent}
    ])],
    exports: [RouterModule]
})
export class QrscreenRoutingModule {
}
