import { TestBed, async, inject } from '@angular/core/testing';

import { MarketplaceGuard } from './marketplace.guard';

describe('MarketplaceGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MarketplaceGuard]
    });
  });

  it('should ...', inject([MarketplaceGuard], (guard: MarketplaceGuard) => {
    expect(guard).toBeTruthy();
  }));
});
