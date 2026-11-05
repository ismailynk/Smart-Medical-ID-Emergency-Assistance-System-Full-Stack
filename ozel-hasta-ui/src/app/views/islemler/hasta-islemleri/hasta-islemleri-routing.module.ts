import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import {HastaIslemleriComponent} from "./hasta-islemleri.component";

@NgModule({
    imports: [RouterModule.forChild([
        {path: '', component: HastaIslemleriComponent}
    ])],
    exports: [RouterModule]
})
export class HastaIslemleriRoutingModule {
}
