import { TestBed, async, inject } from '@angular/core/testing';

import { BidderGuard } from './bidder.guard';

describe('BidderGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BidderGuard]
    });
  });

  it('should ...', inject([BidderGuard], (guard: BidderGuard) => {
    expect(guard).toBeTruthy();
  }));
});
