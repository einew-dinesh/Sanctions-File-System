import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MakerHistoryFileComponent } from './maker-history-file.component';

describe('MakerHistoryFileComponent', () => {
  let component: MakerHistoryFileComponent;
  let fixture: ComponentFixture<MakerHistoryFileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MakerHistoryFileComponent]
    });
    fixture = TestBed.createComponent(MakerHistoryFileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
