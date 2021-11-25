import { TestBed, async, inject } from '@angular/core/testing';

import { FarmerRegistrationGuardGuard } from './farmer-registration-guard.guard';

describe('FarmerRegistrationGuardGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FarmerRegistrationGuardGuard]
    });
  });

  it('should ...', inject([FarmerRegistrationGuardGuard], (guard: FarmerRegistrationGuardGuard) => {
    expect(guard).toBeTruthy();
  }));
});
