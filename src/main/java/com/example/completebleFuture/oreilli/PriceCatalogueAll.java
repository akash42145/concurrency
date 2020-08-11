package com.example.completebleFuture.oreilli;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static java.util.stream.Collectors.toList;

public class PriceCatalogueAll {

    private final PriceFinder priceFinder = new PriceFinder();
    private final ExchangeService exchangeService = new ExchangeService();
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args)
    {

        new PriceCatalogueAll().findAllDiscountedPrice(Currency.CHF, Catalogue.products);
    }

    private void findAllDiscountedPrice(final Currency localCurrency, List<Product> products)
    {
        long time = System.currentTimeMillis();

        CompletableFuture<Double> exchangeRateFuture
                = CompletableFuture.supplyAsync(() -> exchangeService.lookupExchangeRate(Currency.USD, localCurrency), executorService);

        List<CompletableFuture<Price>> priceFutureList = products.stream()
                .map(product -> CompletableFuture.supplyAsync(() -> priceFinder.findBestPrice(product), executorService))
                .collect(toList());

        CompletableFuture<List<Price>> priceListFuture
                = sequence(priceFutureList);

        priceListFuture.thenCombine(exchangeRateFuture, (list, exchangeRate)
                -> list.stream().mapToDouble(price -> exchange(price, exchangeRate)).peek(localPrice -> {
                	System.out.printf("A product will cost us %f %s\n",  localPrice, localCurrency);
                } ).sum())
                .thenAccept(totalPrice -> System.out.printf("The total price is %f %s\n", totalPrice, localCurrency)).join();

        System.out.printf("It took us %d ms to calculate this\n", System.currentTimeMillis() - time);
        
        executorService.shutdownNow();
    }

    private double exchange(Price price, double exchangeRate)
    {
        return Utils.round(price.getAmount() * exchangeRate);
    }

    private <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
        CompletableFuture<Void> allFuturesDone =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allFuturesDone.thenApply(v ->
                futures.stream()
                        .map(CompletableFuture::join)
                        .collect(toList()));
    }
}
