import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AmarreComponent } from './amarre.component';

describe('AmarreComponent', () => {
  let component: AmarreComponent;
  let fixture: ComponentFixture<AmarreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AmarreComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AmarreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
