import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";

@NgModule({
    imports: [RouterModule.forChild([
        {
            path: 'hasta',
            data: {breadcrumb: 'Hasta İşlemleri'},
            loadChildren: () => import('./hasta-islemleri/hasta-islemleri.module').then(m => m.HastaIslemleriModule)
        }
    ])],
    exports: [RouterModule]
})
export class IslemlerRoutingModule {
}
