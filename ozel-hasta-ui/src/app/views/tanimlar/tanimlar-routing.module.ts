import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";

@NgModule({
    imports: [RouterModule.forChild([
        { path: 'hastalik', data: {breadcrumb: 'Hastalık Tanımlama'},  loadChildren: () => import('./hastalik/hastalik.module').then(m => m.HastalikModule) }
    ])],
    exports: [RouterModule]
})
export class TanimlarRoutingModule { }
