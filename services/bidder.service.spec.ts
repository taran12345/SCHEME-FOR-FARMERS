import { TestBed } from '@angular/core/testing';

import { BidderService } from './bidder.service';

describe('BidderService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BidderService = TestBed.get(BidderService);
    expect(service).toBeTruthy();
  });
});
