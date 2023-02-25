package com.friendly.framework.constants

class TableScript {

    companion object{
        val createInvoiceTable =  """
                                            CREATE TABLE IF NOT EXISTS `Sale` (
                                            `_id` tinytext,
                                            `UserID` tinytext,
                                            `BusinessID` tinytext NOT NULL,
                                            `IsDeleted` BOOLEAN,
                                            `CustomerName` tinytext,
                                            `CustomerMobile` tinytext,
                                            `VehicleNumber` tinytext,
                                            `ProductName` tinytext,
                                            `ProductID` tinytext,
                                            `PaymentMode` tinytext,
                                            `Quantity` int,
                                            `Price` float,
                                            `CostPrice` float,
                                            `Discount` float,
                                            `SGST` float,
                                            `CGST` float,
                                            `IGST` float,
                                            `CESS` float,
                                            `VAT` float,
                                            `Tax` float,
                                            `FinalPrice` float,
                                            `SaleDate` tinytext,
                                            `SaleTime` bigint,
                                            `__v` int,
                                            `CreatedAt` tinytext,
                                            `UpdatedAt` tinytext,
                                             PRIMARY KEY (_id)
                                            )
                                            """
        val createCustomerTable =  """
                                            CREATE TABLE IF NOT EXISTS `Sale` (
                                            `_id` tinytext,
                                            `UserID` tinytext,
                                            `BusinessID` tinytext NOT NULL,
                                            `IsDeleted` BOOLEAN,
                                            `CustomerName` tinytext,
                                            `CustomerMobile` tinytext,
                                            `VehicleNumber` tinytext,
                                            `ProductName` tinytext,
                                            `ProductID` tinytext,
                                            `PaymentMode` tinytext,
                                            `Quantity` int,
                                            `Price` float,
                                            `CostPrice` float,
                                            `Discount` float,
                                            `SGST` float,
                                            `CGST` float,
                                            `IGST` float,
                                            `CESS` float,
                                            `VAT` float,
                                            `Tax` float,
                                            `FinalPrice` float,
                                            `SaleDate` tinytext,
                                            `SaleTime` bigint,
                                            `__v` int,
                                            `CreatedAt` tinytext,
                                            `UpdatedAt` tinytext,
                                             PRIMARY KEY (_id)
                                            )
                                            """
        val createSaleTable =  """
                                            CREATE TABLE IF NOT EXISTS `Sale` (
                                            `_id` tinytext,
                                            `UserID` tinytext,
                                            `BusinessID` tinytext NOT NULL,
                                            `IsDeleted` BOOLEAN,
                                            `CustomerName` tinytext,
                                            `CustomerMobile` tinytext,
                                            `VehicleNumber` tinytext,
                                            `ProductName` tinytext,
                                            `ProductID` tinytext,
                                            `PaymentMode` tinytext,
                                            `Quantity` int,
                                            `Price` float,
                                            `CostPrice` float,
                                            `Discount` float,
                                            `SGST` float,
                                            `CGST` float,
                                            `IGST` float,
                                            `CESS` float,
                                            `VAT` float,
                                            `Tax` float,
                                            `FinalPrice` float,
                                            `SaleDate` tinytext,
                                            `SaleTime` bigint,
                                            `__v` int,
                                            `CreatedAt` tinytext,
                                            `UpdatedAt` tinytext,
                                             PRIMARY KEY (_id)
                                            )
                                            """
        val createProductStockTable =  """
                                            CREATE TABLE IF NOT EXISTS `${TableNames.productStock}` (
                                            `_id` tinytext,
                                            `UserID` tinytext,
                                            `BusinessID` tinytext NOT NULL,
                                            `ProductID`tinytext,
                                            `ActionID` tinytext,
                                            `IncreaseQuantity` int,
                                            `DecreaseQuantity` int,
                                            `TotalQuantity` int,
                                            `Comment` tinytext,
                                            `__v` int,
                                            `CreatedAt` tinytext,
                                            `UpdatedAt` tinytext,
                                             PRIMARY KEY (_id)
                                            )
                                            """
    }

}