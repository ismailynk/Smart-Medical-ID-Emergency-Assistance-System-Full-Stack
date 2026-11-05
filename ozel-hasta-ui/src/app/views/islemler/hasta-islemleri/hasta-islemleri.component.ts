import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Table } from "primeng/table";
import { HastalikService } from "../../../services/hastalik.service";
import { HastaService } from "../../../services/hasta.service";
import { FormBuilder } from "@angular/forms";
import { HastalikDTO } from "../../../models/hastalik.model";
import { HastaDTO } from "../../../models/hasta.model";
import { ConfirmationService, MessageService } from "primeng/api";
import { AuthenticationService } from "../../../services/authentication.service";
import { HastaHastalikService } from "../../../services/hasta-hastalik.service";
import { HastaHastalikDTO } from "../../../models/hasta-hastalik.model";
import {SafeUrl} from "@angular/platform-browser";
import { SelectButtonModule } from 'primeng/selectbutton';
import { RadioButtonModule } from 'primeng/radiobutton';


@Component({
    selector: 'app-hastalik',
    templateUrl: './hasta-islemleri.component.html',
    styleUrls: ['./hasta-islemleri.component.scss']
})
export class HastaIslemleriComponent implements OnInit {

    //cinsiyetler: any[] = [];
    hastaForm = this.fb.group({
        id: [''],
        tckimlik: [''],
        adi: [''],
        tel: [''],
        yakinTel1: [''],
        yakinTel2: [''],
        dogumTarihi: [''],
        cinsiyet: ['']
    });
    hastalikList = new Array<HastalikDTO>();
    hastaHastalikList = new Array<HastaHastalikDTO>();
    public url: string = "";
    public qrCodeDownloadLink: SafeUrl = "";
    @ViewChild('filter') filter!: ElementRef;

    constructor(private hastaService: HastaService,
                private hastalikService: HastalikService,
                private fb: FormBuilder,
                private messageService: MessageService,
                private authenticationService: AuthenticationService,
                private hastaHastalikService: HastaHastalikService,
                private confirmationService: ConfirmationService) {
    }


    ngOnInit(): void {
        this.listeleHastalik();
        this.kayitDoldur();


    }

    onGlobalFilter(table: Table, event: Event) {
        table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
    }

    clear(table: Table) {
        table.clear();
        this.filter.nativeElement.value = '';
    }

    listeleHastalik(): void {
        this.hastalikService.listeleHastalik().subscribe((rs: HastalikDTO[]) => {
            this.hastalikList = rs;
        })
    }

    temizle(): void {
        this.hastaForm.reset();
    }

    kayitDoldur(): void {
        this.temizle();
        this.hastaService.hastaBilgiGetir(this.authenticationService.getKullaniciKodu()).subscribe((rs: HastaDTO) => {
            if (rs) {
                this.hastaForm.get('id').setValue(rs.id);
                this.hastaForm.get('tckimlik').setValue(rs.tckimlik);
                this.hastaForm.get('adi').setValue(rs.adi);
                this.hastaForm.get('tel').setValue(rs.tel);
                this.hastaForm.get('yakinTel1').setValue(rs.yakinTel1);
                this.hastaForm.get('yakinTel2').setValue(rs.yakinTel2);
                this.hastaForm.get('dogumTarihi').setValue(rs.dogumTarihi);
                this.hastaForm.get('cinsiyet').setValue(rs.cinsiyet);
                this.url = `http://localhost:4200/#/hasta-bilgi?key=${rs.id}`;
                this.listeleHastaHastalik();
            }
        });

    }
    onChangeURL(url: SafeUrl) {
        this.qrCodeDownloadLink = url;
    }

    kaydet(): void {
        const hastaDTO = new HastaDTO();
        hastaDTO.id = this.hastaForm.get('id').value;
        hastaDTO.tckimlik = this.hastaForm.get('tckimlik').value;
        hastaDTO.adi = this.hastaForm.get('adi').value;
        hastaDTO.tel = this.hastaForm.get('tel').value;
        hastaDTO.yakinTel1 = this.hastaForm.get('yakinTel1').value;
        hastaDTO.yakinTel2 = this.hastaForm.get('yakinTel2').value;
        hastaDTO.dogumTarihi = this.hastaForm.get('dogumTarihi').value;
        hastaDTO.cinsiyet = this.hastaForm.get('cinsiyet').value;

        this.hastaService.guncelleHasta(hastaDTO).subscribe(() => {
            this.messageService.add({
                key: 'global',
                severity: 'success',
                summary: 'Kayıt Güncelleme',
                detail: 'Güncelleme İşlemi Başarılı Bir Şekilde Tamamlanmıştır',
            });
        });
    }

    hastalikEkle(hastalikId: string): void {
        if (this.hastaForm.get('id').value) {
            this.confirmationService.confirm({
                message: 'Hastalık Kaydı Eklenecek Emin misiniz?',
                header: 'Hastalık Kayıt Ekle...!!!',
                icon: 'pi pi-info-circle',
                rejectLabel: 'Hayır',
                acceptLabel: 'Evet',
                accept: () => {
                    const hastaHastalikDTO = new HastaHastalikDTO();
                    const hastaDTO = new HastaDTO();
                    const hastalikDTO = new HastalikDTO();
                    hastaDTO.id = this.hastaForm.get('id').value;
                    hastalikDTO.id = hastalikId;

                    hastaHastalikDTO.hasta = hastaDTO;
                    hastaHastalikDTO.hastalik = hastalikDTO;

                    this.hastaHastalikService.kaydetHastaHastalik(hastaHastalikDTO).subscribe(() => {
                        this.messageService.add({
                            key: 'global',
                            severity: 'success',
                            summary: 'Hastalık Ekleme',
                            detail: 'Kayıt Başarılı Bir Şekilde Eklenmiştir.',
                        });
                        this.listeleHastaHastalik();
                    });
                },
            });
        }
    }

    listeleHastaHastalik() {
        this.hastaHastalikService.listeleHastaHastalik(this.hastaForm.get('id').value).subscribe((rs1: HastaHastalikDTO[]) => {
            this.hastaHastalikList = rs1;
        })
    }

    sil(hastaHastalikId: string): void {
        this.confirmationService.confirm({
            message: 'Hastalık Kaydı Silinecek Emin misiniz?',
            header: 'Hastalık Kayıt Silme...!!!',
            icon: 'pi pi-info-circle',
            rejectLabel: 'Hayır',
            acceptLabel: 'Evet',
            accept: () => {
                this.hastaHastalikService.silHastaHastalik(hastaHastalikId).subscribe(() => {
                    this.messageService.add({
                        severity: 'success',
                        key: "global",
                        summary: 'Silme İşlemi',
                        detail: 'kayıt Başarılı Bir Şekilde Silinmiştir.',
                    });
                    this.listeleHastaHastalik();
                });
            },
        });
    }
}
