import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Table } from "primeng/table";
import { ConfirmationService, MessageService } from "primeng/api";
import {ActivatedRoute} from "@angular/router";
import {QrscreenService} from "../../../services/qrscreen.service";
import {HastalikBilgiResponse} from "../../../models/hastalik-bilgi-response.model";
import {SafeUrl} from "@angular/platform-browser";


@Component({
    selector: 'app-hastalik',
    templateUrl: './qrscreen.component.html',
    styleUrls: ['./qrscreen.component.scss']
})

export class QrscreenComponent implements OnInit {

    hastaUUID:string;
    public url: string = "";
    public qrCodeDownloadLink: SafeUrl = "";

    hastalikBilgiResponse = new HastalikBilgiResponse();


    constructor(private messageService: MessageService,
                private confirmationService: ConfirmationService,
                private route: ActivatedRoute,
                private qrscreenService:QrscreenService) {
    }


    ngOnInit(): void {
        this.route.queryParams.subscribe(params=>{
            if(params['key']){
                this.hastaUUID=params['key'];
                this.kayitDoldur();
            }
        })
    }

    onGlobalFilter(table: Table, event: Event) {
        table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
    }

    clear(table: Table) {
        table.clear();
    }



    kayitDoldur(): void {

        this.qrscreenService.hastahastalikBilgiGetir(this.hastaUUID).subscribe((rs: HastalikBilgiResponse) => {
            if (rs) {
                this.hastalikBilgiResponse = rs;
                this.url = `http://localhost:4200/#/hasta-bilgi?key=${this.hastaUUID}`;
            }
        });

    }
    onChangeURL(url: SafeUrl) {
        this.qrCodeDownloadLink = url;
    }
}
