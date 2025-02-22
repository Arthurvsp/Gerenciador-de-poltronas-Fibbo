import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoltronasComponent } from './poltronas.component';

describe('PoltronasComponent', () => {
  let component: PoltronasComponent;
  let fixture: ComponentFixture<PoltronasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PoltronasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PoltronasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
