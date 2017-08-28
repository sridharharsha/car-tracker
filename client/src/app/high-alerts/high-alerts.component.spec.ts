import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HighAlertsComponent } from './high-alerts.component';

describe('HighAlertsComponent', () => {
  let component: HighAlertsComponent;
  let fixture: ComponentFixture<HighAlertsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HighAlertsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HighAlertsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
