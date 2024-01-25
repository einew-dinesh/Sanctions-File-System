import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckerNavComponent } from './checker-nav.component';

describe('CheckerNavComponent', () => {
  let component: CheckerNavComponent;
  let fixture: ComponentFixture<CheckerNavComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CheckerNavComponent]
    });
    fixture = TestBed.createComponent(CheckerNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
