import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServicesAndCategoriesComponent } from './services-and-categories.component';

describe('ServicesAndCategoriesComponent', () => {
  let component: ServicesAndCategoriesComponent;
  let fixture: ComponentFixture<ServicesAndCategoriesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServicesAndCategoriesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServicesAndCategoriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
