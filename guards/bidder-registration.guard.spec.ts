import { TestBed, async, inject } from '@angular/core/testing';

import { BidderRegistrationGuard } from './bidder-registration.guard';

describe('BidderRegistrationGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BidderRegistrationGuard]
    });
  });

  it('should ...', inject([BidderRegistrationGuard], (guard: BidderRegistrationGuard) => {
    expect(guard).toBeTruthy();
  }));
});
