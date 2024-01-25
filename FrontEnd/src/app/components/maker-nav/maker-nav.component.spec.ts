import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MakerNavComponent } from './maker-nav.component';

describe('MakerNavComponent', () => {
  let component: MakerNavComponent;
  let fixture: ComponentFixture<MakerNavComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MakerNavComponent]
    });
    fixture = TestBed.createComponent(MakerNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
