import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { HastalikComponent } from "./hastalik.component";

@NgModule({
    imports: [RouterModule.forChild([
        {path: '', component: HastalikComponent}
    ])],
    exports: [RouterModule]
})
export class HastalikRoutingModule {
}
