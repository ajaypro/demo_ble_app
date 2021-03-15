package com.technoidentity.vitalz.network;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001c\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/technoidentity/vitalz/network/Urls;", "", "()V", "BASE_URL_MPI_Production", "", "CLASSIFICATION", "COMPANYLIST", "CUSTOMERDETAILS", "CUSTOMERS", "FILTERCATEGORYLIST", "FILTERCOMPANYLIST", "GETALLCOMPANY", "GETALLOUTLET", "GETALLPRODUCTS", "GETCATEGORYLIST", "GETCOMPANYLIST", "GETPRICELIST", "GETPRODUCTFILTEREDWITHCOMPANY", "GETPRODUCTPRICELIST", "GETPUSHSALE", "INVENTORIESTOCKRECEIVED", "LOCATION", "LOGIN", "MARKVISIT", "ORDERSUMMARY", "ORDERSUMMARYDETAILS", "ORDERTYPE", "OUTLETDETAIL", "PLACEPREORDER", "PRODUCTDETAIL", "PRODUCTPREORDER", "WAREHOUSE", "app_debug"})
public final class Urls {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BASE_URL_MPI_Production = "http://13.126.215.238/api/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOGIN = "token";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOCATION = " users/{userId}/locations";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CUSTOMERS = "customers";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CUSTOMERDETAILS = "customers/{id}/details";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String WAREHOUSE = "warehouses/outlet/{siteNumber}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String OUTLETDETAIL = "customers/{customerId}/all";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PRODUCTPREORDER = "products/preorder";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PRODUCTDETAIL = "products/{productId}/details";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String COMPANYLIST = "products/all/companies";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GETCATEGORYLIST = "products/all/categories";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FILTERCOMPANYLIST = "products/preorder";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FILTERCATEGORYLIST = "products/preorder";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CLASSIFICATION = "products/preorder";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GETPRODUCTPRICELIST = "productprices";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GETCOMPANYLIST = "companies";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GETPUSHSALE = "offers";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PLACEPREORDER = "preorders";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String INVENTORIESTOCKRECEIVED = "inventories/stockreceiveds/{productId}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MARKVISIT = "user/visit/locations";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ORDERTYPE = "orders/details/{branchOrgId}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GETALLOUTLET = "warehouses/branch";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GETALLPRODUCTS = "products";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GETALLCOMPANY = "companies";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GETPRODUCTFILTEREDWITHCOMPANY = "products/items_principal/{id}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GETPRICELIST = "products/priceList";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ORDERSUMMARY = "preorders/salesAgent/{id}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ORDERSUMMARYDETAILS = "preorders/{id}/details";
    @org.jetbrains.annotations.NotNull()
    public static final com.technoidentity.vitalz.network.Urls INSTANCE = null;
    
    private Urls() {
        super();
    }
}