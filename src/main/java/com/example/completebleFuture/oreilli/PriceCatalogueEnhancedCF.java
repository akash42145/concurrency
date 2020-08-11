package com.example.completebleFuture.oreilli;

import java.util.concurrent.CompletableFuture;

public class PriceCatalogueEnhancedCF {

	private final Catalogue catalogue = new Catalogue();
    private final PriceFinder priceFinder = new PriceFinder();
    private final ExchangeService exchangeService = new ExchangeService();

    public static void main(String[] args)
    {
        new PriceCatalogueEnhancedCF().findLocalDiscountedPrice(Currency.CHF, "Nexus7");
    }

    private void findLocalDiscountedPrice(final Currency localCurrency, final String productName)
    {
        long time = System.currentTimeMillis();
         
		lookupProduct(productName)
				.thenCompose(this::findBestPrice)
				.thenCombine(lookupExchangeRate(localCurrency), (price, exchangeRate) -> exchange(price, exchangeRate))
				.thenAccept(localPrice -> {
					
					System.out.printf("A %s will cost us %f %s\n", productName, localPrice, localCurrency);
			        System.out.printf("It took us %d ms to calculate this\n", System.currentTimeMillis() - time);
				}).join();    
		
		//thenApply Example
		//lookupProduct(productName).thenApply(p -> p.getName()).join();
    }

    private double exchange(Price price, double exchangeRate)
    {
        return Utils.round(price.getAmount() * exchangeRate);
    }
    
    private CompletableFuture<Product> lookupProduct(String productName){
    	
    	return  CompletableFuture.supplyAsync(() -> catalogue.productByName(productName));
    }
    
    private CompletableFuture<Price> findBestPrice(Product product){
    	
    	return  CompletableFuture.supplyAsync(() ->  priceFinder.findBestPrice(product));
    }
    
    private CompletableFuture<Double> lookupExchangeRate(Currency localCurrency){
    	
    	return  CompletableFuture.supplyAsync(() -> exchangeService.lookupExchangeRate(Currency.USD, localCurrency)); 
    }


}
