import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Table } from "primeng/table";
import { HastalikService } from "../../../services/hastalik.service";
import { FormBuilder } from "@angular/forms";
import { HastalikDTO } from "../../../models/hastalik.model";
import { ConfirmationService, MessageService } from "primeng/api";

@Component({
    selector: 'app-hastalik',
    templateUrl: './hastalik.component.html',
    styleUrls: ['./hastalik.component.scss']
})
export class HastalikComponent implements OnInit {

    hastalikForm = this.fb.group({
        id: [''],
        adi: [''],
        tanim: [''],
        belirtiler: [''],
        yapilmasiGerekenler: [''],
        yapilmamasiGerekenler: ['']
    });
    buttonLabel = 'Kaydet';
    hastalikList = new Array<HastalikDTO>();

    @ViewChild('filter') filter!: ElementRef;

    constructor(private hastaliService: HastalikService,
                private fb: FormBuilder,
                private messageService: MessageService,
                private confirmationService: ConfirmationService) {
    }

    ngOnInit(): void {
        this.listeleHastalik();
    }

    onGlobalFilter(table: Table, event: Event) {
        table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
    }

    clear(table: Table) {
        table.clear();
        this.filter.nativeElement.value = '';
    }

    listeleHastalik(): void {
        this.hastaliService.listeleHastalik().subscribe((rs: HastalikDTO[]) => {
            this.hastalikList = rs;
        })
    }

    temizle(): void {
        this.hastalikForm.reset();
        this.buttonLabel = 'Kaydet';
    }

    kayitDoldur(event: any): void {
        this.hastalikForm.get('id').setValue(event.id);
        this.hastalikForm.get('adi').setValue(event.adi);
        this.hastalikForm.get('tanim').setValue(event.tanim);
        this.hastalikForm.get('belirtiler').setValue(event.belirtiler);
        this.hastalikForm.get('yapilmasiGerekenler').setValue(event.yapilmasiGerekenler);
        this.hastalikForm.get('yapilmamasiGerekenler').setValue(event.yapilmamasiGerekenler);
        this.buttonLabel = 'Güncelle';
    }

    kaydet(): void {
        const hastalikDTO = new HastalikDTO();
        hastalikDTO.id = this.hastalikForm.get('id').value;
        hastalikDTO.adi = this.hastalikForm.get('adi').value;
        hastalikDTO.tanim = this.hastalikForm.get('tanim').value;
        hastalikDTO.belirtiler = this.hastalikForm.get('belirtiler').value;
        hastalikDTO.yapilmasiGerekenler = this.hastalikForm.get('yapilmasiGerekenler').value;
        hastalikDTO.yapilmamasiGerekenler = this.hastalikForm.get('yapilmamasiGerekenler').value;
        if (hastalikDTO.id) {
            this.hastaliService.guncelleHastalik(hastalikDTO).subscribe(() => {
                this.temizle();
                this.listeleHastalik();
                this.messageService.add({
                    severity: 'success',
                    key: 'global',
                    summary: 'Güncelleme İşlemi Başarılı Bir Şekilde Tamamlanmıştır',
                });
            })
        } else {
            this.hastaliService.kaydetHastalik(hastalikDTO).subscribe(() => {
                this.temizle();
                this.listeleHastalik();
                this.messageService.add({
                    severity: 'success',
                    key: 'global',
                    summary: 'Kaydetme İşlemi Başarılı Bir Şekilde Tamamlanmıştır',
                });
            })
        }
    }

    sil(hastalikId: string): void {
        this.confirmationService.confirm({
            message: 'Hastalık Kaydı Silinecek Emin misiniz?',
            header: 'Hastalık Kayıt Silme...!!!',
            icon: 'pi pi-info-circle',
            rejectLabel: 'Hayır',
            acceptLabel: 'Evet',
            accept: () => {
                this.hastaliService.silHastalik(hastalikId).subscribe(() => {
                    this.messageService.add({
                        severity: 'success',
                        key: "global",
                        summary: 'Silme İşlemi',
                        detail: 'kayıt Başarılı Bir Şekilde Silinmiştir.',
                    });
                    this.temizle();
                    this.listeleHastalik();
                });
            },
        });
    }
}
