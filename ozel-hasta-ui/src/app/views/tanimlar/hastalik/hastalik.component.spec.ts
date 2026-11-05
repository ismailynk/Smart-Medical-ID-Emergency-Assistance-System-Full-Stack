import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HastalikComponent } from './hastalik.component';

describe('HastalikComponent', () => {
  let component: HastalikComponent;
  let fixture: ComponentFixture<HastalikComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HastalikComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HastalikComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
