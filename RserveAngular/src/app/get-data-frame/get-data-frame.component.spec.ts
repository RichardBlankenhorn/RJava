import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetDataFrameComponent } from './get-data-frame.component';

describe('GetDataFrameComponent', () => {
  let component: GetDataFrameComponent;
  let fixture: ComponentFixture<GetDataFrameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetDataFrameComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetDataFrameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
