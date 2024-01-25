import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckerActionComponent } from './checker-action.component';

describe('CheckerActionComponent', () => {
  let component: CheckerActionComponent;
  let fixture: ComponentFixture<CheckerActionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CheckerActionComponent]
    });
    fixture = TestBed.createComponent(CheckerActionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
