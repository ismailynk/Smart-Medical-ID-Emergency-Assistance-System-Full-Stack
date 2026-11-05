import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QrscreenComponent } from './qrscreen.component';

describe('HastalikComponent', () => {
  let component: QrscreenComponent;
  let fixture: ComponentFixture<QrscreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QrscreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QrscreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
