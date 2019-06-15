import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccomodationPreviewComponent } from './accomodation-preview.component';

describe('AccomodationPreviewComponent', () => {
  let component: AccomodationPreviewComponent;
  let fixture: ComponentFixture<AccomodationPreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccomodationPreviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccomodationPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
