import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Userdelete } from './userdelete';

describe('Userdelete', () => {
  let component: Userdelete;
  let fixture: ComponentFixture<Userdelete>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Userdelete]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Userdelete);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
