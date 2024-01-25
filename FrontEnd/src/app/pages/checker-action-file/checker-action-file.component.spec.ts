import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckerActionFileComponent } from './checker-action-file.component';

describe('CheckerActionFileComponent', () => {
  let component: CheckerActionFileComponent;
  let fixture: ComponentFixture<CheckerActionFileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CheckerActionFileComponent]
    });
    fixture = TestBed.createComponent(CheckerActionFileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
