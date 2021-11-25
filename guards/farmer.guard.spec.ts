import { TestBed, async, inject } from '@angular/core/testing';

import { FarmerGuard } from './farmer.guard';

describe('FarmerGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FarmerGuard]
    });
  });

  it('should ...', inject([FarmerGuard], (guard: FarmerGuard) => {
    expect(guard).toBeTruthy();
  }));
});
