package com.example.completebleFuture.oreilli;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PriceCatalogueFuture {

	private final Catalogue catalogue = new Catalogue();
    private final PriceFinder priceFinder = new PriceFinder();
    private final ExchangeService exchangeService = new ExchangeService();
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        new PriceCatalogueFuture().findLocalDiscountedPrice(Currency.CHF, "Nexus7");
    }

    private void findLocalDiscountedPrice(final Currency localCurrency, final String productName) throws InterruptedException, ExecutionException
    {
        long time = System.currentTimeMillis();
        
        Future<Product> productFuture= executorService.submit(() -> catalogue.productByName(productName));
        
        Future<Price> priceFuture = executorService.submit(() -> priceFinder.findBestPrice(productFuture.get()));
 
        Future<Double> exchangeRateFuture = executorService.submit(() -> exchangeService.lookupExchangeRate(Currency.USD, localCurrency));

        double localPrice = exchange(priceFuture.get(), exchangeRateFuture.get());
        
        executorService.shutdownNow();

        System.out.printf("A %s will cost us %f %s\n", productName, localPrice, localCurrency);
        System.out.printf("It took us %d ms to calculate this\n", System.currentTimeMillis() - time);
    }

    private double exchange(Price price, double exchangeRate)
    {
        return Utils.round(price.getAmount() * exchangeRate);
    }


}
