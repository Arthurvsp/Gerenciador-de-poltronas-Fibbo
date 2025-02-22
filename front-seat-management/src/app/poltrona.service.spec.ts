import { TestBed } from '@angular/core/testing';

import { PoltronaService } from './poltrona.service';

describe('PoltronaService', () => {
  let service: PoltronaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PoltronaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
