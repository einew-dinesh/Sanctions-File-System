import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MakerHistoryComponent } from './maker-history.component';

describe('MakerHistoryComponent', () => {
  let component: MakerHistoryComponent;
  let fixture: ComponentFixture<MakerHistoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MakerHistoryComponent]
    });
    fixture = TestBed.createComponent(MakerHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
