import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { InputTextModule } from "primeng/inputtext";
import { ButtonModule } from "primeng/button";
import { TableModule } from "primeng/table";
import { TooltipModule } from "primeng/tooltip";
import { InputTextareaModule } from "primeng/inputtextarea";
import { ConfirmDialogModule } from "primeng/confirmdialog";
import {HastaIslemleriComponent} from "./hasta-islemleri.component";
import {HastaIslemleriRoutingModule} from "./hasta-islemleri-routing.module";
import { ToastModule } from "primeng/toast";
import {RadioButtonModule} from "primeng/radiobutton";
import { DropdownModule } from 'primeng/dropdown';
import {SelectButtonModule} from "primeng/selectbutton";

import { QRCodeModule } from 'angularx-qrcode';


@NgModule({
    declarations: [
        HastaIslemleriComponent
    ],
    imports: [
        CommonModule,
        ReactiveFormsModule,
        FormsModule,
        HastaIslemleriRoutingModule,
        InputTextModule,
        ButtonModule,
        TableModule,
        TooltipModule,
        InputTextareaModule,
        ConfirmDialogModule,
        ToastModule,
        RadioButtonModule,
        DropdownModule,
        SelectButtonModule,
        QRCodeModule
    ]
})
export class HastaIslemleriModule {
}
