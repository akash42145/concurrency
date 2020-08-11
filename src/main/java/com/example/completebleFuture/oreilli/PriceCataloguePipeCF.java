package com.example.completebleFuture.oreilli;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PriceCataloguePipeCF {

	private final Catalogue catalogue = new Catalogue();
    private final PriceFinder priceFinder = new PriceFinder();
    private final ExchangeService exchangeService = new ExchangeService();
    private final ExecutorService executorService =  Executors.newCachedThreadPool();

    public static void main(String[] args)
    {
        new PriceCataloguePipeCF().findLocalDiscountedPrice(Currency.CHF, "Nexus7");
    }

    private void findLocalDiscountedPrice(final Currency localCurrency, final String productName)
    {
        long time = System.currentTimeMillis();
         
		lookupProduct(productName)
				.thenComposeAsync(this::findBestPrice, executorService)
				.thenCombineAsync(lookupExchangeRate(localCurrency), (price, exchangeRate) -> exchange(price, exchangeRate), executorService)
				.thenAcceptAsync(localPrice -> {
					
					System.out.printf("A %s will cost us %f %s\n", productName, localPrice, localCurrency);
			        System.out.printf("It took us %d ms to calculate this\n", System.currentTimeMillis() - time);
				}, executorService).join();    
		
		//thenApply Example
		//lookupProduct(productName).thenApply(p -> p.getName()).join();
		
		executorService.shutdownNow();
    }

    private double exchange(Price price, double exchangeRate)
    {
        return Utils.round(price.getAmount() * exchangeRate);
    }
    
    private CompletableFuture<Product> lookupProduct(String productName){
    	
    	return  CompletableFuture.supplyAsync(() -> catalogue.productByName(productName), executorService);
    }
    
    private CompletableFuture<Price> findBestPrice(Product product){
    	
    	return  CompletableFuture.supplyAsync(() ->  priceFinder.findBestPrice(product));
    }
    
    private CompletableFuture<Double> lookupExchangeRate(Currency localCurrency){
    	
    	return  CompletableFuture.supplyAsync(() -> exchangeService.lookupExchangeRate(Currency.USD, localCurrency), executorService); 
    }


}
