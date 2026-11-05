import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { QrscreenRoutingModule } from "./qrscreen-routing.module";
import { QrscreenComponent } from './qrscreen.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { InputTextModule } from "primeng/inputtext";
import { ButtonModule } from "primeng/button";
import { TableModule } from "primeng/table";
import { TooltipModule } from "primeng/tooltip";
import { InputTextareaModule } from "primeng/inputtextarea";
import { ConfirmDialogModule } from "primeng/confirmdialog";
import {AccordionModule} from "primeng/accordion";
import { CardModule } from 'primeng/card';


@NgModule({
    declarations: [
        QrscreenComponent
    ],
    imports: [
        CommonModule,
        ReactiveFormsModule,
        FormsModule,
        QrscreenRoutingModule,
        InputTextModule,
        ButtonModule,
        TableModule,
        TooltipModule,
        InputTextareaModule,
        ConfirmDialogModule,
        AccordionModule,
        CardModule


    ]
})
export class QrscreenModule {
}
