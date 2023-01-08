//
//  Product.swift
//  Solute
//
//  Created by Vivek Singh on 06/01/23.
//

import Foundation
struct Product : Codable,Identifiable {
    var id = UUID()
    let _id : String?
    let productID : Int?
    let userID : String?
    let categoryID : String?
    let subCategoryID : String?
    let businessID : String?
    let isDeleted : Bool?
    let name : String?
    let description : String?
    let manageInventory : Bool?
    let taxIncluded : Bool?
    let sGST : Int?
    let cGST : Int?
    let iGST : Int?
    let cESS : Int?
    let vAT : Int?
    let discount : Int?
    let mRP : Int?
    let price : Int?
    let costPrice : Int?
    let finalPrice : Int?
    let tax : Int?
    let createdAt : String?
    let updatedAt : String?
    let __v : Int?

    enum CodingKeys: String, CodingKey {
        case _id = "_id"
        case productID = "ProductID"
        case userID = "UserID"
        case categoryID = "CategoryID"
        case subCategoryID = "SubCategoryID"
        case businessID = "BusinessID"
        case isDeleted = "IsDeleted"
        case name = "Name"
        case description = "Description"
        case manageInventory = "ManageInventory"
        case taxIncluded = "TaxIncluded"
        case sGST = "SGST"
        case cGST = "CGST"
        case iGST = "IGST"
        case cESS = "CESS"
        case vAT = "VAT"
        case discount = "Discount"
        case mRP = "MRP"
        case price = "Price"
        case costPrice = "CostPrice"
        case finalPrice = "FinalPrice"
        case tax = "Tax"
        case createdAt = "CreatedAt"
        case updatedAt = "UpdatedAt"
        case __v = "__v"
    }

    init(from decoder: Decoder) throws {
        let values = try decoder.container(keyedBy: CodingKeys.self)
        _id = try values.decodeIfPresent(String.self, forKey: ._id)
        productID = try values.decodeIfPresent(Int.self, forKey: .productID)
        userID = try values.decodeIfPresent(String.self, forKey: .userID)
        categoryID = try values.decodeIfPresent(String.self, forKey: .categoryID)
        subCategoryID = try values.decodeIfPresent(String.self, forKey: .subCategoryID)
        businessID = try values.decodeIfPresent(String.self, forKey: .businessID)
        isDeleted = try values.decodeIfPresent(Bool.self, forKey: .isDeleted)
        name = try values.decodeIfPresent(String.self, forKey: .name)
        description = try values.decodeIfPresent(String.self, forKey: .description)
        manageInventory = try values.decodeIfPresent(Bool.self, forKey: .manageInventory)
        taxIncluded = try values.decodeIfPresent(Bool.self, forKey: .taxIncluded)
        sGST = try values.decodeIfPresent(Int.self, forKey: .sGST)
        cGST = try values.decodeIfPresent(Int.self, forKey: .cGST)
        iGST = try values.decodeIfPresent(Int.self, forKey: .iGST)
        cESS = try values.decodeIfPresent(Int.self, forKey: .cESS)
        vAT = try values.decodeIfPresent(Int.self, forKey: .vAT)
        discount = try values.decodeIfPresent(Int.self, forKey: .discount)
        mRP = try values.decodeIfPresent(Int.self, forKey: .mRP)
        price = try values.decodeIfPresent(Int.self, forKey: .price)
        costPrice = try values.decodeIfPresent(Int.self, forKey: .costPrice)
        finalPrice = try values.decodeIfPresent(Int.self, forKey: .finalPrice)
        tax = try values.decodeIfPresent(Int.self, forKey: .tax)
        createdAt = try values.decodeIfPresent(String.self, forKey: .createdAt)
        updatedAt = try values.decodeIfPresent(String.self, forKey: .updatedAt)
        __v = try values.decodeIfPresent(Int.self, forKey: .__v)
    }

}
