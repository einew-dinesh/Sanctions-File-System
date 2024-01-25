import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MakerUploadComponent } from './maker-upload.component';

describe('MakerUploadComponent', () => {
  let component: MakerUploadComponent;
  let fixture: ComponentFixture<MakerUploadComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MakerUploadComponent]
    });
    fixture = TestBed.createComponent(MakerUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
