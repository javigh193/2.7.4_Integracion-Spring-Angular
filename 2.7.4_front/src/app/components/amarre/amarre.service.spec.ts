import { TestBed } from '@angular/core/testing';

import { AmarreService } from './amarre.service';

describe('AmarreService', () => {
  let service: AmarreService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AmarreService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
