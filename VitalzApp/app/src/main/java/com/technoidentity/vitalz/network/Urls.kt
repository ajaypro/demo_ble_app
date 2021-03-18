package com.technoidentity.vitalz.network

object Urls {

    //Base Url For All

    const val BASE_URL_MPI_Production = "http://13.126.215.238/api/"

    const val LOGIN = "token"

    const val LOCATION = " users/{userId}/locations"

    const val CUSTOMERS = "customers"

    const val CUSTOMERDETAILS = "customers/{id}/details"

    const val WAREHOUSE = "warehouses/outlet/{siteNumber}"


    const val OUTLETDETAIL = "customers/{customerId}/all"

    const val PRODUCTPREORDER = "products/preorder"

    const val PRODUCTDETAIL = "products/{productId}/details"

    const val COMPANYLIST = "products/all/companies"

    const val GETCATEGORYLIST = "products/all/categories"

    const val FILTERCOMPANYLIST = "products/preorder"

    const val FILTERCATEGORYLIST = "products/preorder"

    const val CLASSIFICATION = "products/preorder"

    const val GETPRODUCTPRICELIST = "productprices"

    const val GETCOMPANYLIST = "companies"

    const val GETPUSHSALE = "offers" // api used for OFFERS

    const val PLACEPREORDER = "preorders"

    // this api contain BATCH NUMBER
    const val INVENTORIESTOCKRECEIVED = "inventories/stockreceiveds/{productId}"

    //MarkVisit
    const val MARKVISIT = "user/visit/locations"

    //Order Type
    const val ORDERTYPE = "orders/details/{branchOrgId}"

    //Get All Outlet Related to BranchId
    const val GETALLOUTLET = "warehouses/branch"

    //getAll Products
    const val GETALLPRODUCTS = "products"

    //getAll Company
    const val GETALLCOMPANY = "companies"

    //getCompanyFilteredproducts
    const val GETPRODUCTFILTEREDWITHCOMPANY = "products/items_principal/{id}"

    //getPriceList
    const val GETPRICELIST = "products/priceList"

    //getOrderSummary
    const val ORDERSUMMARY = "preorders/salesAgent/{id}"

    //getOrderSummary
    const val ORDERSUMMARYDETAILS = "preorders/{id}/details"
}
