import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HastalikRoutingModule } from "./hastalik-routing.module";
import { HastalikComponent } from './hastalik.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { InputTextModule } from "primeng/inputtext";
import { ButtonModule } from "primeng/button";
import { TableModule } from "primeng/table";
import { TooltipModule } from "primeng/tooltip";
import { InputTextareaModule } from "primeng/inputtextarea";
import { ConfirmDialogModule } from "primeng/confirmdialog";


@NgModule({
    declarations: [
        HastalikComponent
    ],
    imports: [
        CommonModule,
        ReactiveFormsModule,
        FormsModule,
        HastalikRoutingModule,
        InputTextModule,
        ButtonModule,
        TableModule,
        TooltipModule,
        InputTextareaModule,
        ConfirmDialogModule
    ]
})
export class HastalikModule {
}
